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
import DAO.ItemsDAO;

/**
 *
 * @author matt
 */
public class PopulateTables{

    private File accountFile;
    private File containerFile;
    private File configCartFile;
    private File componentFile;
    private File configurationFile;
    private File configurationsFile;
    
    Random rnGen = new Random();

    private void initialize(){
	accountFile = new File("/home/matt/SE4485_ContainerShoppingCart/Container_Shopping_Cart/test/csvData/accounts.csv");
	containerFile = new File("/home/matt/SE4485_ContainerShoppingCart/Container_Shopping_Cart/test/csvData/container.csv");
        configCartFile = new File("/home/matt/SE4485_ContainerShoppingCart/Container_Shopping_Cart/test/csvData/configCart.csv");
	componentFile = new File("/home/matt/SE4485_ContainerShoppingCart/Container_Shopping_Cart/test/csvData/component.csv");
	configurationFile = new File("/home/matt/SE4485_ContainerShoppingCart/Container_Shopping_Cart/test/csvData/configuration.csv");
        configurationsFile = new File("/home/matt/SE4485_ContainerShoppingCart/Container_Shopping_Cart/test/csvData/configurations.csv");
    }	

    public static void main(String args[]){
	try{
            DataSource dataSource = DBConnection.getDataSource();
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            
            PopulateTables fillTables = new PopulateTables();
            fillTables.initialize();

            Map<Long, Account> accountMap = fillTables.buildAccount();
            fillTables.insertAccount(connection, accountMap);
                    
            Map<Long, Container> containerMap = fillTables.buildContainer();
            //fillTables.addComponents(containerMap);
            fillTables.addConfigurations(containerMap);
            fillTables.insertContainer(connection, containerMap);
            
            List<Cart> cartItems = fillTables.buildCart(accountMap.values(), containerMap.values().toArray(new Container[0]));
            fillTables.insertCart(connection, cartItems); 
            
            //This is the old ConfigCart
//            List<ConfigCart> configCart = fillTables.buildConfigCart(cartItems.toArray(new Cart[0]));
//            fillTables.insertConfigCart(connection, configCart);
            
            List<Purchase> purchases = fillTables.buildPurchase(accountMap.values().toArray(new Account[0]));
            fillTables.insertPurchase(connection, purchases);
            
            Map<Long, Component> componentMap = fillTables.buildComponent();
            fillTables.insertComponent(connection, componentMap);

            List<Components> components = fillTables.buildComponents(containerMap.values(), componentMap.values().toArray(new Component[0]));
            fillTables.insertComponents(connection, components);
                        
            List<Items> items = fillTables.buildItems(purchases, containerMap.values().toArray(new Container[0]));
            fillTables.insertItems(connection, items);

            //-----------Working on this-----------------
            List<ConfigCart> cartConfig = fillTables.buildConfigCart(cartItems.toArray(new Cart[0]), configurationsMap.values());
            fillTables.insertConfigCart(connection, cartConfig);
            //--------------------------------------------
            
            connection.commit();
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

//    private void addComponents(Map<Long, Container> containerMap, ArrayList<Components> components) throws Exception{
//        Object items[] = (Object[]) new Object();
//        Container contain = containerMap.get(items[0]);
//        contain.setComponents(items[1]);
//    }
    
    private Components buildComponents(Long containerID, Long componentID) throws Exception{
	Components components = new Components();
	components.setContainerID(containerID);
	components.setComponentID(componentID);
        return components;
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
    
    
    //---------------Working on------------------------
    //Have to get a match between cartContainerID to it configurations
    private List<ConfigCart> buildConfigCart(Cart[] cartList, Collection<Configurations> configsNeedAdded) throws Exception{
        List<ConfigCart> configCartList = new ArrayList<>();
        for(Cart cart : cartList){
            List<Configurations> configToAdd = buildConfigsForCart(cart.getCartContainerID(), configsNeedAdded);
            for (Configurations configurations : configToAdd){
                Configuration configurationToAdd = buildConfigForCart(configurations.getConfigurationID());
                ConfigCart configCart = new ConfigCart();
                configCart.setUserID(cart.getUserID());
                configCart.setCartContainerID(cart.getCartContainerID());
                configCart.setUserType(configurationToAdd.getDefaultType(cart.getCartContainerID()));
                configCart.setUserArg1(configurationToAdd.getDefaultArg1(cart.getCartContainerID()));
                configCart.setUserArg2(configurationToAdd.getDefaultArg2(cart.getCartContainerID()));
                configCartList.add(configCart);
            }
        }
        return configCartList;
    }
    
    private List<Configurations> buildConfigsForCart(Long containerIDToAdd, Collection<Configurations> possConfigs){
        List<Configurations> listOfCartConfigs = new ArrayList<>();
        for(Configurations configurations : possConfigs){
            if(configurations.getContainerID().equals(containerIDToAdd)){
                Configurations cartConfigs = new Configurations();
                cartConfigs.setContainerID(configurations.getContainerID());
                cartConfigs.setConfigurationID(configurations.getConfigurationID());
                listOfCartConfigs.add(cartConfigs);
            }
        }    
        return listOfCartConfigs;
    }
    
    private Configuration buildConfigForCart(Long configurationsID){
        //Configurations configsWillBeAdded = new Configurations();
        Configuration configToAddToCart = new Configuration();
       
        configToAddToCart.setConfigurationID(configurationsID);
        configToAddToCart.setDefaultType(configToAddToCart.getDefaultType(configurationsID));
        configToAddToCart.setDefaultArg1(configToAddToCart.getDefaultArg1(configurationsID));
        configToAddToCart.setDefaultArg2(configToAddToCart.getDefaultArg2(configurationsID));
        return configToAddToCart;
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

    //-----------------add Methods------------------------------
    private void addConfigurations(Map<Long, Container> containerMap) throws Exception{
        //Map<Long, Configurations> containerMap = new HashMap<>();
	FileReader fileReader = new FileReader(configurationsFile);
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	String line = null;
	List<Configurations> configList = new ArrayList<>();
        
        while ((line = bufferedReader.readLine()) != null) {
            Object items[] = parseConfigurations(line);
            Container container = containerMap.get(items[0]);
            container.setConfigurations(configList);
	}
        bufferedReader.close();
    }    
    
    //List<Configurations> configList = new ArrayList<>();
    //container.add(
    
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
            componentDAO.addComponentFT(connection, component);
        }
    }
    
    private void insertComponents(Connection connection, List<Components> componentsList) throws Exception{
        //For some reason in the example cart was outside of the for loop
        //ComponentsDAO componDAO = new ComponentsDaoImpl();
        for(Components components : componentsList){
            ComponentsDAO componDAO = new ComponentsDaoImpl();
            componDAO.addComponents(connection, components);
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
      
    //For old fill
//    private void insertConfigurations(Connection connection, List<Configurations> configurationsList) throws Exception{
//        //For some reason in the example cart was outside of the for loop
//        //ConfigurationsDAO configDAO = new ConfigurationsDaoImpl();
//        for(Configurations configurations : configurationsList){
//            ConfigurationsDAO configDAO = new ConfigurationsDaoImpl();
//            configDAO.createConfigurations(connection, configurations);
//        }
//    }
    
    //For new fill
    private void insertConfigurations(Connection connection, Map<Long, Configurations> configurationsMap) throws Exception{
        //For some reason in the example cart was outside of the for loop
        //ConfigurationsDAO configDAO = new ConfigurationsDaoImpl();
        for(Configurations configurations : configurationsMap.values()){
            ConfigurationsDAO configDAO = new ConfigurationsDaoImpl();
            configDAO.createConfigurations(connection, configurations);
        }
    }
        
    private void insertContainer(Connection connection, Map<Long, Container> containerMap) throws Exception{
        for(Container container: containerMap.values()){
            ContainerDAO containerDAO = new ContainerDaoImpl();
            containerDAO.addContainerFT(connection, container);
            
            ComponentsDAO compDAO = new ComponentsDaoImpl();
            ArrayList<Component> comp = container.getComponents();
            compDAO.createComponents(connection, comp, container.getContainerID());
            
            ConfigurationsDAO confDAO = new ConfigurationsDaoImpl();
            ArrayList<Configuration> conf = container.getConfigurations();
            confDAO.createConfiguration(connection, conf, container.getContainerID());
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
    
//    private Object[] parseConfigurations(String line){
//	StringTokenizer st = new StringTokenizer(line, ",");
//	Configurations configurations = new Configurations();
//	Long id = Long.parseLong(st.nextToken());
//        configurations.setConfigurationID(id);
//        configurations.setContainerID(Long.valueOf(st.nextToken()));
//	configurations.setDisplayName(st.nextToken());
//        configurations.setDefaultType(st.nextToken());
//	configurations.setDefaultArg1(st.nextToken());
//	configurations.setDefaultArg2(st.nextToken());
//	Object[] result = {id, configurations};
//	return result;
//    }

    private Object[] parseConfigurations(String line){
	StringTokenizer st = new StringTokenizer(line, ",");
        int m = 0;
        int n = 1;
	List<Configurations> configurationsList = new ArrayList<>();
        Configurations configurations = new Configurations();
	Long id = Long.parseLong(st.nextToken());
        configurations.setConfigurationID(id);
        configurations.setContainerID(Long.valueOf(st.nextToken()));
	configurations.setDisplayName(st.nextToken());
        configurations.setDefaultType(st.nextToken());
	configurations.setDefaultArg1(st.nextToken());
	configurations.setDefaultArg2(st.nextToken());
        configurationsList.add(configurations);
        
        if(configurationsList[m].getContainerID().equals(configurations[n].getContainerID())){
            
        }

        
	Object[] result = {id, configurations};
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
	container.setProductFamily(st.nextToken());
        container.setVersion(st.nextToken());
	Object[] result = {id, container};
	return result;
    }

}//Closes PopulateTables