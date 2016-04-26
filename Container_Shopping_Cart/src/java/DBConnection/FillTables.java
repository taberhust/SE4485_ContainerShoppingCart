/*
 * This is so we can fill all of our tables and all have the same data to work from.
 * This also allows us to test our data.
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
 * Fill the database with test data
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

    /**
     * Initialize the csv files to variables
     */
    private void initialize(){
	accountFile = new File("test/csvData/accounts.csv");
	containerFile = new File("test/csvData/container.csv");
        configCartFile = new File("test/csvData/configCart.csv");
	componentFile = new File("test/csvData/component.csv");
	configurationFile = new File("test/csvData/configuration.csv");
    }	

    /**
     * Main function handling all calls
     * 
     * @param args 
     */
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

            connection.commit();
            System.out.println("Finished Filling the tables");
	}
	catch(Exception ex){
            ex.printStackTrace();
            System.out.print("Failed to fill the tables.");
        }
    }
    
    //-----------------build Methods------------------------
    /**
     * Builds a map of accounts
     * 
     * @return Map with ID and Account objects
     * @throws Exception 
     */
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

    /**
     * Build a list of carts and return them
     * 
     * @param accounts Collection of accounts to add to the database
     * @param containers Array of containers to add to the database
     * @return A list of cart objects
     * @throws Exception 
     */
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
    
    /**
     * Build up a cart object and return it
     * 
     * @param userID ID of the user tied to the cart
     * @param cartContainerID ID of the container in the cart
     * @return Cart object with the specified data
     */
    private Cart buildCart(Long userID, Long cartContainerID){
	Cart cart = new Cart();
	cart.setUserID(userID);
	cart.setCartContainerID(cartContainerID);
	return cart;
    }
    
    /**
     * Build a map containing an ID and container object
     * 
     * @return Map with an ID and container object
     * @throws Exception 
     */
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

    /**
     * Build a map containing an ID and component object
     * 
     * @return Map containing ID and component object
     * @throws Exception 
     */
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

    /**
     * Build a map containing an ID and configuration object
     * 
     * @return Map containing an ID and configuration object
     * @throws Exception 
     */
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

    /**
     * Build a List of components to add to the database
     * 
     * @param containers Collection of containers to add into the database
     * @param components Array of components to add to the database
     * @return List of components
     * @throws Exception 
     */
    private List<Components> buildComponents(Collection<Container> containers, Component[] components) throws Exception{
        List<Components> componList = new ArrayList<>();
        for(Container container: containers){
            Component component = components[rnGen.nextInt(components.length)];
            Components componToAdd = buildComponents(container.getContainerID(), component.getComponentID());
            componList.add(componToAdd);
        }
        return componList;
    }

    /**
     * Build a components object to add to the database
     * 
     * @param containerID ID of the container
     * @param componentID ID of the component
     * @return Components object with the specified data
     * @throws Exception 
     */
    private Components buildComponents(Long containerID, Long componentID) throws Exception{
	Components components = new Components();
	components.setContainerID(containerID);
	components.setComponentID(componentID);
        return components;
    }

    /**
     * Build a list of configurations to add to the database
     * 
     * @param containers Collection of containers to add
     * @param configurations Array of configurations to add
     * @return List of configurations added to the database
     * @throws Exception 
     */
    private List<Configurations> buildConfigurations(Collection<Container> containers, Configuration[] configurations) throws Exception{
        List<Configurations> configList = new ArrayList<>();
        for(Container container : containers){
            Configuration configuration = configurations[rnGen.nextInt(configurations.length)];
            Configurations configsToAdd = buildConfigurations(container.getContainerID(), configuration.getConfigurationID());
            configList.add(configsToAdd);
        }
        return configList;
    }

    /**
     * Build a configurations object to add to the database
     * 
     * @param containerID ID of the container
     * @param configurationID ID of the configuration
     * @return Configurations object that was built
     * @throws Exception 
     */
    private Configurations buildConfigurations(Long containerID, Long configurationID) throws Exception{
        Configurations configurations = new Configurations();
        configurations.setContainerID(containerID);
        configurations.setConfigurationID(configurationID);
        return configurations;
    }

    /**
     * Build a list of items to add to the database
     * 
     * @param purchases List of purhcases to add to the database
     * @param containers Array of containers to add to the database
     * @return List of items added to the database
     * @throws Exception 
     */
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

    /**
     * Build an items object to add to the database
     * 
     * @param purchaseID ID of the purchase
     * @param containerID ID of the container
     * @return Items object that was built
     * @throws Exception 
     */
    private Items buildItems(Long purchaseID, Long containerID) throws Exception{
	Items items = new Items();
	items.setPurchaseID(purchaseID);
	items.setContainerID(containerID);
	return items;
    }
    
    /**
     * Build a list of configurations to add to the cart in the database
     * 
     * @param cartItems Array of cart items
     * @return List of configurations added to the configuration cart
     * @throws Exception 
     */
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
            configcart.setDisplayName(((ConfigCart)item[1]).getDisplayName());
            configcart.setUserType(((ConfigCart)item[1]).getUserType());
            configcart.setUserArg1(((ConfigCart)item[1]).getUserArg1());
            configcart.setUserArg2(((ConfigCart)item[1]).getUserArg2());
            configCartList.add(configcart);
        }
        return configCartList;
    }
    
    /**
     * Build a list of purchase items to add to the database
     * 
     * @param accounts Array of accounts to add to the table
     * @return List of purchase objects to add to the database
     * @throws Exception 
     */
    private List<Purchase> buildPurchase(Account[] accounts) throws Exception{
	List<Purchase> purchaseList = new ArrayList<>();
	for(int i=1; i<101; i++){
            Account account = accounts[rnGen.nextInt(accounts.length)];
            Purchase purchase = buildPurchase(Long.valueOf(i), account.getUserID());
            purchaseList.add(purchase);
        }
        return purchaseList;
    }

    /**
     * Build a purchase object to add to the database
     * 
     * @param purchaseID ID of the purchase
     * @param userID ID of the user
     * @return Purchase item that was built
     */
    private Purchase buildPurchase(Long purchaseID, Long userID){
	Purchase purchase = new Purchase();
	purchase.setPurchaseID(purchaseID);
	purchase.setUserID(userID);

	Calendar cal = GregorianCalendar.getInstance();
        int dateOffset = rnGen.nextInt(10*365);
	cal.add(Calendar.DAY_OF_YEAR, dateOffset);
	purchase.setTimeOfPurchase(new java.sql.Date(cal.getTimeInMillis()));
        return purchase;
    }

    //-----------------insert Methods------------------------------
    /**
     * Insert account objects into the database
     * 
     * @param connection Connection to be used
     * @param accountMap Map containing ID and account object
     * @throws Exception 
     */
    private void insertAccount(Connection connection, Map<Long, Account> accountMap) throws Exception{
        AccountDAO accountDAO = new AccountDaoImpl();
        for(Account account: accountMap.values()){
            accountDAO.createAccount(connection, account);
        }
    }
        
    /**
     * Insert cart objects into the database
     * 
     * @param connection Connection to be used
     * @param cartList List of cart objects to insert
     * @throws Exception 
     */
    private void insertCart(Connection connection, List<Cart> cartList) throws Exception{
        CartDAO cartDAO = new CartDaoImpl();
        for(Cart cart : cartList){
            cartDAO.createCart(connection, cart);
        }
    }
    
    /**
     * Insert component objects into the database
     * 
     * @param connection Connection to be used
     * @param componentMap Map containing ID and component objects
     * @throws Exception 
     */
    private void insertComponent(Connection connection, Map<Long, Component> componentMap) throws Exception{
        ComponentDAO componentDAO = new ComponentDaoImpl();
        for(Component component: componentMap.values()){
            componentDAO.addComponent(connection, component);
        }
    }
    
    /**
     * Insert components objects into the database
     * 
     * @param connection Connection to be used
     * @param componentsList List of components objects to insert
     * @throws Exception 
     */
    private void insertComponents(Connection connection, List<Components> componentsList) throws Exception{
        ComponentsDAO componDAO = new ComponentsDaoImpl();
        for(Components components : componentsList){
            componDAO.addComponents(connection, components);
        }
    }
    
    /**
     * Insert configCart objects into the database
     * 
     * @param connection Connection to be used
     * @param configcartlist List of configCart objects to insert
     * @throws Exception 
     */
    private void insertConfigCart(Connection connection, List<ConfigCart> configcartlist) throws Exception{
        ConfigCartDAO configcartDAO = new ConfigCartDaoImpl();
        for(ConfigCart configcart : configcartlist){
            configcartDAO.createConfigCart(connection, configcart);
        }
    }
       
    /**
     * Insert configuration objects into the database
     * 
     * @param connection Connection to be used
     * @param configurationMap Map containing ID and configuration object
     * @throws Exception 
     */
    private void insertConfiguration(Connection connection, Map<Long, Configuration> configurationMap) throws Exception{
        ConfigurationDAO configurationDAO = new ConfigurationDaoImpl();
        for(Configuration configuration: configurationMap.values()){
            configurationDAO.createConfiguration(connection, configuration);
        }
    }
      
    /**
     * Insert configurations objects into the database
     * 
     * @param connection Connection to be used
     * @param configurationsList List of configurations objects to be inserted
     * @throws Exception 
     */
    private void insertConfigurations(Connection connection, List<Configurations> configurationsList) throws Exception{
        ConfigurationsDAO configDAO = new ConfigurationsDaoImpl();
        for(Configurations configurations : configurationsList){
            configDAO.createConfigurations(connection, configurations);
        }
    }
        
    /**
     * Insert container objects into the database
     * 
     * @param connection Connection to be used
     * @param containerMap Map containing ID and container object
     * @throws Exception 
     */
    private void insertContainer(Connection connection, Map<Long, Container> containerMap) throws Exception{
        ContainerDAO containerDAO = new ContainerDaoImpl();
        for(Container container: containerMap.values()){
            containerDAO.addContainer(connection, container);
        }
    }
    
    /**
     * Insert items objects into the database
     * 
     * @param connection Connection to be used
     * @param itemsList List of items to insert into the database
     * @throws Exception 
     */
    private void insertItems(Connection connection, List<Items> itemsList) throws Exception{
        ItemsDAO itemsDAO = new ItemsDaoImpl();
        for(Items items : itemsList){
            itemsDAO.createItems(connection, items);
        }
    }
    
    /**
     * Insert purchase objects into the database
     * 
     * @param connection Connection to be used
     * @param purchases List of purchases to add into the database
     * @throws Exception 
     */
    private void insertPurchase(Connection connection, List<Purchase> purchases) throws Exception{
        PurchaseDAO purchaseDAO = new PurchaseDaoImpl();
        for (Purchase purchase : purchases){
            purchaseDAO.createPurchase(connection, purchase);
        }
    }

    //-----------------parse Methods-----------------------------
    /**
     * Parse account
     * 
     * @param line Line to parse
     * @return Array of ID and account object
     */
    private Object[] parseAccount(String line){
        StringTokenizer st = new StringTokenizer(line, ",");
        Account account = new Account();
        Long id = Long.parseLong(st.nextToken());
        account.setUserName(st.nextToken());
        account.setFirstName(st.nextToken());
        account.setLastName(st.nextToken());
        account.setPrivilege(st.nextToken());
        account.setPassword(st.nextToken());
	Object[] result = {id, account};
        return result;
    }
    
    /**
     * Parse component
     * 
     * @param line Line to parse
     * @return Array of ID and component object
     */
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

    /**
     * Parse configCart
     * 
     * @param line Line to parse
     * @return Array of ID and configCart object
     */
    private Object[] parseConfigCart(String line){
        StringTokenizer st = new StringTokenizer(line, ",");
        ConfigCart configcart = new ConfigCart();
        Long id = Long.parseLong(st.nextToken());
        configcart.setDisplayName(st.nextToken());
        configcart.setUserType(st.nextToken());
        configcart.setUserArg1(st.nextToken());
        configcart.setUserArg2(st.nextToken());
        Object[] result = {id, configcart}; 
        return result;
    }
    
    /**
     * Parse configuration
     * 
     * @param line Line to parse
     * @return Array of ID and configuration object
     */
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

    /**
     * Parse container
     * 
     * @param line Line to parse
     * @return Array of ID and container object
     */
    private Object[] parseContainer(String line){
	StringTokenizer st = new StringTokenizer(line, ",");
	Container container = new Container();
	Long id = Long.parseLong(st.nextToken());
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

}//Closes FillTables