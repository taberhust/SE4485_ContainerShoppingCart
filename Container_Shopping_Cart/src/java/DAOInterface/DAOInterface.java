/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DaoImpl.CartDaoImpl;
import DaoImpl.ComponentsDaoImpl;
import DaoImpl.ContainerDaoImpl;
import DaoImpl.ComponentDaoImpl;
import DaoImpl.ConfigCartDaoImpl;
import DaoImpl.ConfigurationDaoImpl;
import DaoImpl.ConfigurationsDaoImpl;
import Entity.Account;
import Entity.Cart;
import Entity.Component;
import Entity.Components;
import Entity.Configuration;
import Entity.Configurations;
import Entity.Container;
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
        ArrayList<Component> componentList = new ArrayList<Component>();
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
    
    // Containers
    ArrayList<Container> retrieveAllContainers(Connection connection) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveAllContainers(connection);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
            containerList.set(i, addConfigurationsToContainer(connection, container));
        }
        
        return containerList;
    }
    
    ArrayList<Container> retrieveContainersByCategory(Connection connection, String category) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveContainersByCategory(connection, category);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
            containerList.set(i, addConfigurationsToContainer(connection, container));
        }
        
        return containerList;
    }
    
    ArrayList<Container> retrieveContainersByName(Connection connection, String name) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveContainersByName(connection, name);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
            containerList.set(i, addConfigurationsToContainer(connection, container));
        }
        
        return containerList;
    }
    
    ArrayList<Container> retrieveContainersByProductFamily(Connection connection, String family) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveContainersByProductFamily(connection, family);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
            containerList.set(i, addConfigurationsToContainer(connection, container));
        }
        
        return containerList;
    }
    
    public Container addContainerToCart(Connection connection, Container container, Long userID) throws SQLException {
        CartDaoImpl cartItem = new CartDaoImpl();
        ConfigCartDaoImpl configCartItem = new ConfigCartDaoImpl();
        cartItem.createCart(connection, userID, container);
        configCartItem.createConfigCart(connection, userID, container);
        return container;
    }
    
    Container addContainer(Connection connection, Container container) throws SQLException {
        //TODO implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    Container retrieveContainer(Connection connection, Long containerID) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        return containerInstance.retrieveContainer(connection, containerID);
    }
    
    boolean editContainer(Connection connection, Long id, Container container) throws SQLException {
        //TODO implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    boolean deleteContainer(Connection connection, Long id) throws SQLException {
        //TODO implement
        throw new UnsupportedOperationException("Not supported yet.");
    }
}