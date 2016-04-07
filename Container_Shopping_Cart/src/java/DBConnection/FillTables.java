/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.sql.DataSource;

//import DBConnection.DBConnection;

import Entity.Account;
import Entity.Cart;
import Entity.Component;
import Entity.Components;
import Entity.ConfigCart;
import Entity.Configuration;
import Entity.Configurations;
import Entity.Container;
import Entity.Items;
import Entity.Purchase;
import DAO.AccountDAO;
import DAO.CartDAO;
import DAO.ComponentDAO;
import DAO.ComponentsDAO;
import DAO.ConfigCartDAO;
import DAO.ConfigurationDAO;
import DAO.ConfigurationsDAO;
import DAO.ContainerDAO;
import DAO.ItemsDAO;
import DAO.PurchaseDAO;
import DaoImpl.AccountDaoImpl;
import DaoImpl.CartDaoImpl;
import DaoImpl.ComponentDaoImpl;
import DaoImpl.ComponentsDaoImpl;
import DaoImpl.ConfigCartDaoImpl;
import DaoImpl.ConfigurationDaoImpl;
import DaoImpl.ConfigurationsDaoImpl;
import DaoImpl.ContainerDaoImpl;
import DaoImpl.ItemsDaoImpl;
import DaoImpl.PurchaseDaoImpl;

/**
 *
 * @author matt & kevin
 */
public class FillTables{

    private File accountFile;
    private File containerFile;
    private File configCartFile;
    private File componentFile;
    private File configurationFile;
    
    Random rnGen = new Random();

    private void initialize(){
	accountFile = new File("test/csvData/accounts.csv");
	containerFile = new File("test/csvData/container.csv");
        configCartFile = new File("test/csvData/configCart.csv");
	componentFile = new File("test/csvData/component.csv");
	configurationFile = new File("test/csvData/configuration.csv");
    }	

    public static void main(String args[]){
	try{
            DataSource dataSource = DBConnection.getDataSource();
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            
            FillTables fillTables = new FillTables();
            fillTables.initialize();

            Map<Long, Account> accountMap = fillTables.buildAccount();
            fillTables.insertAccount(connection, accountMap);
                    
            Map<Long, Container> containerMap = fillTables.buildContainer();
            fillTables.insertContainer(connection, containerMap);
            
            List<Cart> cartItems = fillTables.buildCart(accountMap.values(), containerMap.values().toArray(new Container[0]));
            fillTables.insertCart(connection, cartItems); 
            
            List<ConfigCart> configCart = fillTables.buildConfigCart(cartItems.toArray(new Cart[0]));
            fillTables.insertConfigCart(connection, configCart);
            
            List<Purchase> purchases = fillTables.buildPurchase(accountMap.values().toArray(new Account[0]));
            fillTables.insertPurchase(connection, purchases);
            
            Map<Long, Component> componentMap = fillTables.buildComponent();
            fillTables.insertComponent(connection, componentMap);

            List<Components> components = fillTables.buildComponents(containerMap.values(), componentMap.values().toArray(new Component[0]));
            fillTables.insertComponents(connection, components);
            
            Map<Long, Configuration> configurationMap = fillTables.buildConfiguration();
            fillTables.insertConfiguration(connection, configurationMap);

            List<Configurations> configurations = fillTables.buildConfigurations(containerMap.values(), configurationMap.values().toArray(new Configuration[0]));
            fillTables.insertConfigurations(connection, configurations);

            List<Items> items = fillTables.buildItems(purchases, containerMap.values().toArray(new Container[0]));
            fillTables.insertItems(connection, items);

            //connection.commit();
            System.out.println("Finished Filling the tables");
	}
	catch(Exception ex){
            ex.printStackTrace();
            System.out.print("Failed to fill the tables.");
        }
    }
    
    //-----------------build Methods------------------------
    private Map<Long, Account> buildAccount() throws Exception{
        Map<Long, Account> accountMap = new HashMap<>();
        FileReader fileReader = new FileReader(accountFile);
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	String line = null;
	while((line = bufferedReader.readLine()) != null){
            Object item[] = parseAccount(line);
            accountMap.put((Long) item[0], (Account) item[1]);
	}
	bufferedReader.close();
	return accountMap;
    }

    private List<Cart> buildCart(Collection<Account> accounts, Container[] containers) throws Exception{
	List<Cart> cartList = new ArrayList<>();
            int numPur;
            for(Account account : accounts){
		numPur = rnGen.nextInt(3)+1;
		for(int i=0; i<numPur; i++){
                    Container container = containers[rnGen.nextInt(containers.length)];
                    Cart containerToAdd = buildCart(account.getUserID(), container.getContainerID());
                    cartList.add(containerToAdd);
                }
            }
            return cartList;
    }
    
    private Cart buildCart(Long userID, Long cartContainerID){
	Cart cart = new Cart();
	cart.setUserID(userID);
	cart.setCartContainerID(cartContainerID);
	return cart;
    }
    
    private Map<Long, Container> buildContainer() throws Exception{
	Map<Long, Container> containerMap = new HashMap<>();
	FileReader fileReader = new FileReader(containerFile);
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	String line = null;
	while ((line = bufferedReader.readLine()) != null) {
            Object item[] = parseContainer(line);
            containerMap.put((Long) item[0], (Container) item[1]);
	}
	bufferedReader.close();
	return containerMap;
    }

    private Map<Long, Component> buildComponent() throws Exception{
	Map<Long, Component> componentMap = new HashMap<>();
	FileReader fileReader = new FileReader(componentFile);
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	String line = null;
	while ((line = bufferedReader.readLine()) != null) {
            Object item[] = parseComponent(line);
            componentMap.put((Long) item[0], (Component) item[1]);
	}
	bufferedReader.close();
	return componentMap;
    }

    private Map<Long, Configuration> buildConfiguration() throws Exception{
	Map<Long, Configuration> configurationMap = new HashMap<>();
	FileReader fileReader = new FileReader(configurationFile);
	BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
	while ((line = bufferedReader.readLine()) != null) {
            Object item[] = parseConfiguration(line);
            configurationMap.put((Long) item[0], (Configuration) item[1]);
	}
	bufferedReader.close();
	return configurationMap;
    }

    private List<Components> buildComponents(Collection<Container> containers, Component[] components) throws Exception{
        List<Components> componList = new ArrayList<>();
        for(Container container: containers){
            Component component = components[rnGen.nextInt(components.length)];
            Components componToAdd = buildComponents(container.getContainerID(), component.getComponentID());
            componList.add(componToAdd);
        }
        return componList;
    }

    private Components buildComponents(Long containerID, Long componentID) throws Exception{
	Components components = new Components();
	components.setContainerID(containerID);
	components.setComponentID(componentID);
        return components;
    }

    private List<Configurations> buildConfigurations(Collection<Container> containers, Configuration[] configurations) throws Exception{
        List<Configurations> configList = new ArrayList<>();
        for(Container container : containers){
            Configuration configuration = configurations[rnGen.nextInt(configurations.length)];
            Configurations configsToAdd = buildConfigurations(container.getContainerID(), configuration.getConfigurationID());
            configList.add(configsToAdd);
        }
        return configList;
    }

    private Configurations buildConfigurations(Long containerID, Long configurationID) throws Exception{
        Configurations configurations = new Configurations();
        configurations.setContainerID(containerID);
        configurations.setConfigurationID(configurationID);
        return configurations;
    }

    private List<Items> buildItems(List<Purchase> purchases, Container[] containers) throws Exception{
	List<Items> itemList = new ArrayList<>();
            int numPur;
            for(Purchase purchase : purchases){
		numPur = rnGen.nextInt(5)+1;
		for(int i=1; i<numPur; i++){
                    Container container = containers[rnGen.nextInt(containers.length)];
                    Items itemToAdd = buildItems(purchase.getPurchaseID(), container.getContainerID());
                    itemList.add(itemToAdd);
                }
            }
            return itemList;
    }

    private Items buildItems(Long purchaseID, Long containerID) throws Exception{
	Items items = new Items();
	items.setPurchaseID(purchaseID);
	items.setContainerID(containerID);
	return items;
    }
    
    private List<ConfigCart> buildConfigCart(Cart[] cartItems) throws Exception{
        List<ConfigCart> configCartList = new ArrayList<>();
        FileReader fileReader = new FileReader(configCartFile);
	BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        int addToThisUser;
	while ((line = bufferedReader.readLine()) != null) {
            addToThisUser = rnGen.nextInt(cartItems.length);
            ConfigCart configcart = new ConfigCart();
            configcart.setUserID(cartItems[addToThisUser].getUserID());
            configcart.setCartContainerID(cartItems[addToThisUser].getCartContainerID());
            Object item[] = parseConfigCart(line);
            configcart.setUserType(((ConfigCart)item[1]).getUserType());
            configcart.setUserArg1(((ConfigCart)item[1]).getUserArg1());
            configcart.setUserArg2(((ConfigCart)item[1]).getUserArg2());
            configCartList.add(configcart);
        }
        return configCartList;
    }
    
    private List<Purchase> buildPurchase(Account[] accounts) throws Exception{
	List<Purchase> purchaseList = new ArrayList<>();
	for(int i=1; i<101; i++){
            Account account = accounts[rnGen.nextInt(accounts.length)];
            //Purchase purchase = new Purchase();
            //purchase.setPurchaseID(Long.valueOf(i));
            //buildPurchase(purchase.getPurchaseID(), account.getUserID());
            Purchase purchase = buildPurchase(Long.valueOf(i), account.getUserID());
            purchaseList.add(purchase);
        }
        return purchaseList;
    }

    private Purchase buildPurchase(Long purchaseID, Long userID){
	Purchase purchase = new Purchase();
	purchase.setPurchaseID(purchaseID);
	purchase.setUserID(userID);
        //Maybe can add this field
        //purchase.setUserName(userID);//Method to setUserName based off of UserID

	Calendar cal = GregorianCalendar.getInstance();
        int dateOffset = rnGen.nextInt(10*365);
	cal.add(Calendar.DAY_OF_YEAR, dateOffset);
	purchase.setTimeOfPurchase(new java.sql.Date(cal.getTimeInMillis()));
        return purchase;
    }

    //-----------------insert Methods------------------------------
    private void insertAccount(Connection connection, Map<Long, Account> accountMap) throws Exception{
        for(Account account: accountMap.values()){
            AccountDAO accountDAO = new AccountDaoImpl();
            accountDAO.createAccount(connection, account);
        }
    }
        
    private void insertCart(Connection connection, List<Cart> cartList) throws Exception{
        //For some reason in the example cart was outside of the for loop
        //CartDAO cartDAO = new CartDaoImpl();
        for(Cart cart : cartList){
            CartDAO cartDAO = new CartDaoImpl();
            cartDAO.createCart(connection, cart);
        }
    }
    
    private void insertComponent(Connection connection, Map<Long, Component> componentMap) throws Exception{
        for(Component component: componentMap.values()){
            ComponentDAO componentDAO = new ComponentDaoImpl();
            componentDAO.createComponent(connection, component);
        }
    }
    
    private void insertComponents(Connection connection, List<Components> componentsList) throws Exception{
        //For some reason in the example cart was outside of the for loop
        //ComponentsDAO componDAO = new ComponentsDaoImpl();
        for(Components components : componentsList){
            ComponentsDAO componDAO = new ComponentsDaoImpl();
            componDAO.createComponents(connection, components);
        }
    }
    
    private void insertConfigCart(Connection connection, List<ConfigCart> configcartlist) throws Exception{
        //For some reason in the example cart was outside of the for loop
        //ConfigCartDAO configcartDAO = new ConfigCartDaoImpl();
        for(ConfigCart configcart : configcartlist){
            ConfigCartDAO configcartDAO = new ConfigCartDaoImpl();
            configcartDAO.createConfigCart(connection, configcart);
        }
    }
        
    private void insertConfiguration(Connection connection, Map<Long, Configuration> configurationMap) throws Exception{
        for(Configuration configuration: configurationMap.values()){
            ConfigurationDAO configurationDAO = new ConfigurationDaoImpl();
            configurationDAO.createConfiguration(connection, configuration);
        }
    }
        
    private void insertConfigurations(Connection connection, List<Configurations> configurationsList) throws Exception{
        //For some reason in the example cart was outside of the for loop
        //ConfigurationsDAO configDAO = new ConfigurationsDaoImpl();
        for(Configurations configurations : configurationsList){
            ConfigurationsDAO configDAO = new ConfigurationsDaoImpl();
            configDAO.createConfigurations(connection, configurations);
        }
    }
        
    private void insertContainer(Connection connection, Map<Long, Container> containerMap) throws Exception{
        for(Container container: containerMap.values()){
            ContainerDAO containerDAO = new ContainerDaoImpl();
            containerDAO.createContainer(connection, container);
        }
    }
    
    private void insertItems(Connection connection, List<Items> itemsList) throws Exception{
        //For some reason in the example cart was outside of the for loop
        //ItemsDAO itemsDAO = new ItemsDaoImpl();
        for(Items items : itemsList){
            ItemsDAO itemsDAO = new ItemsDaoImpl();
            itemsDAO.createItems(connection, items);
        }
    }
    
    private void insertPurchase(Connection connection, List<Purchase> purchases) throws Exception{
        //For some reason in the example cart was outside of the for loop
        PurchaseDAO purchaseDAO = new PurchaseDaoImpl();
        for (Purchase purchase : purchases){
            //PurchaseDAO purchaseDAO = new PurchaseDaoImpl();
            purchaseDAO.createPurchase(connection, purchase);
        }
    }

    //-----------------parse Methods-----------------------------
    private Object[] parseAccount(String line){
        StringTokenizer st = new StringTokenizer(line, ",");
        Account account = new Account();
        Long id = Long.parseLong(st.nextToken());
        account.setUserID(id);
        account.setUserName(st.nextToken());
        account.setFirstName(st.nextToken());
        account.setLastName(st.nextToken());
        account.setPrivilege(st.nextToken());
	Object[] result = {id, account};
        return result;
    }
    
    private Object[] parseComponent(String line){
	StringTokenizer st = new StringTokenizer(line, ",");
	Component component = new Component();
	Long id = Long.parseLong(st.nextToken());
        component.setComponentID(id);
	component.setImageID(st.nextToken());
	component.setComponentName(st.nextToken());
	component.setComponentType(st.nextToken());
	component.setVersion(st.nextToken());
	Object[] result = {id, component};
	return result;
    }

    private Object[] parseConfigCart(String line){
        StringTokenizer st = new StringTokenizer(line, ",");
        ConfigCart configcart = new ConfigCart();
        Long id = Long.parseLong(st.nextToken());
        configcart.setUserType(st.nextToken());
        configcart.setUserArg1(st.nextToken());
        configcart.setUserArg2(st.nextToken());
        Object[] result = {id, configcart}; 
        return result;
    }
    
    private Object[] parseConfiguration(String line){
	StringTokenizer st = new StringTokenizer(line, ",");
	Configuration configuration = new Configuration();
	Long id = Long.parseLong(st.nextToken());
        configuration.setConfigurationID(id);
	configuration.setDisplayName(st.nextToken());
        configuration.setDefaultType(st.nextToken());
	configuration.setDefaultArg1(st.nextToken());
	configuration.setDefaultArg2(st.nextToken());
	Object[] result = {id, configuration};
	return result;
    }

    private Object[] parseContainer(String line){
	StringTokenizer st = new StringTokenizer(line, ",");
	Container container = new Container();
	Long id = Long.parseLong(st.nextToken());
        container.setContainerID(id);
	container.setDockerID(st.nextToken());
	container.setDockerName(st.nextToken());
	container.setContainerName(st.nextToken());
	container.setPathToIcon(st.nextToken());
        container.setCategory(st.nextToken());
	container.setProductName(st.nextToken());
        container.setVersion(st.nextToken());
	Object[] result = {id, container};
	return result;
    }

}//Closes FillTables