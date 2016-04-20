/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DaoImpl.AccountDaoImpl;
import DaoImpl.CartDaoImpl;
import DaoImpl.ComponentsDaoImpl;
import DaoImpl.ContainerDaoImpl;
import DaoImpl.ComponentDaoImpl;
import DaoImpl.ConfigCartDaoImpl;
import DaoImpl.ConfigurationDaoImpl;
import DaoImpl.ConfigurationsDaoImpl;
import DaoImpl.ItemsDaoImpl;
import DaoImpl.PurchaseDaoImpl;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class DAOInterface {
    // Intermediate functions
    private Container addComponentsToContainer(Connection connection, Container container) throws SQLException{
        ComponentsDaoImpl componentsInstance = new ComponentsDaoImpl();
        ComponentDaoImpl componentInstance = new ComponentDaoImpl();
        
        ArrayList<Components> components = componentsInstance.getComponents(connection, container.getContainerID());
        ArrayList<Component> componentList = new ArrayList<>();
        for(int j = 0; j < components.size(); j++) {
            Long componentID = components.get(j).getComponentID();
            componentList.add(componentInstance.getComponent(connection, componentID));
        }
        
        container.setComponents(componentList);
        return container;
    }
    
    private Container addConfigurationsToContainer(Connection connection, Container container) throws SQLException{
        ConfigurationsDaoImpl configsDaoImpl = new ConfigurationsDaoImpl();
        ConfigurationDaoImpl configDaoImpl = new ConfigurationDaoImpl();
        
        ArrayList<Configurations> configsList = configsDaoImpl.getConfigurations(connection, container.getContainerID());
        ArrayList<Configuration> configList = new ArrayList<>();
        for(int i = 0; i < configsList.size(); i++){
            Long configID = configsList.get(i).getConfigurationID();
            configList.add(configDaoImpl.getConfiguration(connection, configID));
        }
        
        container.setConfigurations(configList);
        return container;
    }
    
    private Container addConfigsToCartContainer(Connection connection, Container container) throws SQLException{
        //ConfigurationsDaoImpl configsDaoImpl = new ConfigurationsDaoImpl();
        ConfigCartDaoImpl configCartDaoImpl = new ConfigCartDaoImpl();
        
        ArrayList<ConfigCart> configsList = configCartDaoImpl.getConfigCart(connection, container.getContainerID());
        //ArrayList<ConfigCart> configCartList = configCartDaoImpl.getConfigCarts(connection, configsList);
        ArrayList<Configuration> configList = new ArrayList<>();
        //for(int i = 0; i < configsList.size(); i++){
        for(ConfigCart configCart : configsList){
            Long configID = configCart.getCartContainerID();
            configList.add(configCartDaoImpl.getCartConfigs(connection, configID));
            //configList.add(configDaoImpl.getConfiguration(connection, configID));
        }
        
        container.setConfigurations(configList);
        return container;
    }
    
    private Purchase addContainersToPurchase(Connection connection, Purchase purchase) throws SQLException{
        ItemsDaoImpl itemsInstance = new ItemsDaoImpl();
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        ArrayList<Items> itemsList = new ArrayList<>();
        
        itemsList = itemsInstance.getItems(connection, purchase.getPurchaseID());
        ArrayList<Container> containerList = new ArrayList<>();
        for(int i = 0; i < itemsList.size(); i++) {
            Long containerID = itemsList.get(i).getContainerID();
            Container container = containerInstance.retrieveContainer(connection, containerID);
            container = addComponentsToContainer(connection, container);
            container = addConfigurationsToContainer(connection, container);
            containerList.add(container);
        }
        purchase.setItems(containerList);
        return purchase;
    }
    
    // User functions
    public ArrayList<Container> retrieveAllContainers(Connection connection) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveAllContainers(connection);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
            containerList.set(i, addConfigurationsToContainer(connection, container));
        }
        
        return containerList;
    }
    
    public ArrayList<Container> retrieveContainersByCategory(Connection connection, String category) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        ArrayList<Container> containerList = containerInstance.retrieveContainersByCategory(connection, category);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
            containerList.set(i, addConfigurationsToContainer(connection, container));
        }
        return containerList;
    }
    
    public ArrayList<Container> retrieveContainersByName(Connection connection, String name) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveContainersByName(connection, name);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
            containerList.set(i, addConfigurationsToContainer(connection, container));
        }
        
        return containerList;
    }
    
    public ArrayList<Container> retrieveContainersByProductFamily(Connection connection, String family) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveContainersByProductFamily(connection, family);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
            containerList.set(i, addConfigurationsToContainer(connection, container));
        }
        
        return containerList;
    }
        
    public ArrayList<Container> retrieveCart(Connection connection, Long userID) throws SQLException{
        CartDaoImpl cartDaoImpl = new CartDaoImpl();
        ArrayList<Container> containerList = cartDaoImpl.retrieveCart(connection, userID);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
            
            //This worked but it only returns the default configurations
            //containerList.set(i, addConfigurationsToContainer(connection, container));

            //This should return the configurations from config cart whether the user 
            //changed the defaults or not
            containerList.set(i, addConfigsToCartContainer(connection, container));
        }
        return containerList;
    }
    
    public void addContainerToCart(Connection connection, Container container, Long userID) throws SQLException {
        CartDaoImpl cartDaoImpl = new CartDaoImpl();
        cartDaoImpl.createCart(connection, userID, container);
        ConfigCartDaoImpl configCartItem = new ConfigCartDaoImpl();
        configCartItem.createConfigCart(connection, userID, container);
    }
        
    public ConfigCart addToCart(Connection connectionn, Long userID, Container container) throws SQLException{
        ConfigCartDaoImpl addThisContainer = new ConfigCartDaoImpl();
        ConfigCart configCart = new ConfigCart();      
        return configCart;
    }
    
    public Container retrieveContainer(Connection connection, Long containerID) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        Container container = containerInstance.retrieveContainer(connection, containerID);
        container = addComponentsToContainer(connection, container);
        container = addConfigurationsToContainer(connection, container);
       return container;
    }
    
    public ArrayList<Purchase> retrievePurchases(Connection connection, Long userID) throws SQLException {
        PurchaseDaoImpl purchaseInstance = new PurchaseDaoImpl();
        ArrayList<Purchase> purchaseList = purchaseInstance.retrievePurchases(connection, userID);
        
        for(int i = 0; i < purchaseList.size(); i++) {
            Purchase purchase = purchaseList.get(i);
            purchase = addContainersToPurchase(connection, purchase);
            purchaseList.set(i, purchase);
        }
        
        return purchaseList;
    }
    
    public ArrayList<Items> retrieveItems(Connection connection, Long purchaseID) throws SQLException {
        ItemsDaoImpl itemsInstance = new ItemsDaoImpl();
        ArrayList<Items> itemsList = new ArrayList<Items>();
        itemsList = itemsInstance.getItems(connection, purchaseID);
        return itemsList;
    }
    
    public Account retrieveAccount(Connection connection, String username) throws SQLException {
        AccountDaoImpl accountInstance = new AccountDaoImpl();
        return accountInstance.retrieveAccount(connection, username);
    }
    
    // Administrator functions
    Container addContainer(Connection connection, Container container) throws SQLException {
        //TODO implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    boolean editContainer(Connection connection, Long id, Container container) throws SQLException {
        //TODO implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    boolean deleteContainer(Connection connection, Long id) throws SQLException {
        //TODO implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    Purchase addPurchase(Connection connection, Purchase purchase) throws SQLException {
        //TODO implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean checkString (String someString, ArrayList<Container> category)
    {
        for(Container c: category)
        {
            if(someString.equals(c.getContainerName()))
                return false;
        }
        return true;
    }
}
