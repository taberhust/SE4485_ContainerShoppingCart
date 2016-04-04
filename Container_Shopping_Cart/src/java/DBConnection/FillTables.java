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

import DBConnection.DBConnection;

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
 * @author matt
 */
public class FillTables{

    private File accountFile;
//    private File purchaseFile;
    private File cartFile;
    private File containerFile;
//    private File componentsFile;
    private File configCartFile;
//    private File configurationsFile;
    private File componentFile;
    private File configurationFile;
    
    Random rnGen = new Random();

    private void initialize(){
	accountFile = new File("cvsData/account.csv");
//	purchaseFile = new File("cvsData/purchase.csv");
	cartFile = new File("cvsData/cart.csv");
	containerFile = new File("cvsData/container.csv");
//	componentsFile = new File("cvsData/components.csv");
        configCartFile = new File("cvsData.csv");
//	configurationsFile = new File("cvsData/configurations.csv");
	componentFile = new File("cvsData/component.csv");
	configurationFile = new File("cvsData/configuration.csv");
    }	

    public static void main(String args[]){
	try{
            DataSource dataSource = DBConnection.getDataSource();
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            
            FillTables fillTables = new FillTables();
            fillTables.initialize();

            Map<Long, Account> accountMap = fillTables.buildAccount();
            //fillTables.addCart(accountMap);//Delete if I can get new one working
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

            connection.commit();
            System.out.println("Finished Filling the tables");
	}
	catch(Exception ex){
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
		numPur = rnGen.nextInt(4)+1;
		for(int i=0; i<numPur; i++){
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
            configcart.setUserType(item[0].toString());
            configcart.setUserArg1(item[1].toString());
            configcart.setUserArg2(item[2].toString());
            configCartList.add(configcart);
        }
        return configCartList;
    }
    
    private List<Purchase> buildPurchase(Account[] accounts) throws Exception{
	List<Purchase> purchaseList = new ArrayList<>();
	for(int i=0; i<100; i++){
            Account account = accounts[rnGen.nextInt(accounts.length)];
            Purchase purchase = new Purchase();
            purchase.setPurchaseID(Long.valueOf(i));
            buildPurchase(purchase.getPurchaseID(), account.getUserID());
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
        CartDAO cartDAO = new CartDaoImpl();
        for(Cart cart : cartList){
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
        ComponentsDAO componDAO = new ComponentsDaoImpl();
        for(Components components : componentsList){
            componDAO.createComponents(connection, components);
        }
    }
    
    private void insertConfigCart(Connection connection, List<ConfigCart> configcartlist) throws Exception{
        ConfigCartDAO configcartDAO = new ConfigCartDaoImpl();
        for(ConfigCart configcart : configcartlist){
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
        ConfigurationsDAO configDAO = new ConfigurationsDaoImpl();
        for(Configurations configurations : configurationsList){
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
        ItemsDAO itemsDAO = new ItemsDaoImpl();
        for(Items items : itemsList){
                itemsDAO.createItems(connection, items);
        }
    }
    
    private void insertPurchase(Connection connection, List<Purchase> purchases) throws Exception{
        PurchaseDAO purchaseDAO = new PurchaseDaoImpl();
        for (Purchase purchase : purchases){
            purchaseDAO.createPurchase(connection, purchase);
        }
    }

    //-----------------parse Methods-----------------------------
    private Object[] parseAccount(String line){
        StringTokenizer st = new StringTokenizer(line, ",");
        Account account = new Account();
        Long id = Long.parseLong(st.nextToken());
        account.setFirstName(st.nextToken());
        account.setLastName(st.nextToken());
        account.setPrivilege(st.nextToken());
	Object[] result = {id, account};
        return result;
    }
    
    private Object[] parseCart(String line){
	StringTokenizer st = new StringTokenizer(line, ",");
	Cart cart = new Cart();
	Long id = Long.parseLong(st.nextToken());
	cart.setUserID(Long.parseLong(st.nextToken()));
	cart.setCartContainerID(Long.parseLong(st.nextToken()));
	cart.setCartContainerConfig(st.nextToken());
	Object[] result = {id, cart};
	return result;
    }

    private Object[] parseComponent(String line){
	StringTokenizer st = new StringTokenizer(line, ",");
	Component component = new Component();
	Long id = Long.parseLong(st.nextToken());
	component.setImageID(st.nextToken());
	component.setComponentName(st.nextToken());
	component.setComponentType(st.nextToken());
	component.setVersion(st.nextToken());
	Object[] result = {id, component};
	return result;
    }

//	//Don't think this is right.
//	private Object[] parseComponents(String line){
//		StringTokenizer st = new StringTokenizer(line, ",");
//		Components components = new Components();
//		Long id = Long.parseLong(st.nextToken());
//		components.setContainerID(Long.parseLong(st.nextToken()));
//		components.setComponentID(Long.parseLong(st.nextToken()));
//		Object[] result = {id, components};
//		return result;
//	}

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
	configuration.setDisplayName(st.nextToken());
        configuration.setDefaultType(st.nextToken());
	configuration.setDefaultArg1(st.nextToken());
	configuration.setDefaultArg2(st.nextToken());
	Object[] result = {id, configuration};
	return result;
    }

//	//Don't think this is right.
//	private Object[] parseConfigurations(String line){
//		StringTokenizer st = new StringTokenizer(line, ",");
//		Configurations configuration = new Configurations();
//		Long id = Long.parseLong(st.nextToken());
//		configuration.setContainerID(Long.parseLong(st.nextToken()));
//		configuration.setConfigurationID(Long.parseLong(st.nextToken()));
//		Object[] result = {id, configuration};
//		return result;
//	}

    private Object[] parseContainer(String line){
	StringTokenizer st = new StringTokenizer(line, ",");
	Container container = new Container();
	Long id = Long.parseLong(st.nextToken());
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

//	private Object[] parsePurchase(String line){
//		StringTokenizer st = new StringTokenizer(line, ",");
//		Purchase purchase = new Purchase();
//		Long id = Long.parseLong(st.nextToken());
//		purchase.setPurchaseID(Long.parseLong(st.nextToken()));
//		purchase.setTimeOfPurchase(Date.valueOf(st.nextToken()));
//		Object[] result = {id, purchase};
//		return result;
//	}

}//Closes FillTables