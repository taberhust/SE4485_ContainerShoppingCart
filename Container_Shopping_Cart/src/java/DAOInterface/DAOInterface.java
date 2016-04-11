/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DaoImpl.ComponentsDaoImpl;
import DaoImpl.ContainerDaoImpl;
import DaoImpl.ComponentDaoImpl;
import Entity.Account;
import Entity.Component;
import Entity.Components;
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
    Container addComponentsToContainer(Connection connection, Container container) throws SQLException{
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
    
    // Containers
    ArrayList<Container> retrieveAllContainers(Connection connection) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveAllContainers(connection);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
        }
        
        return containerList;
    }
    
    ArrayList<Container> retrieveContainersByCategory(Connection connection, String category) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveContainersByCategory(connection, category);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
        }
        
        return containerList;
    }
    
    ArrayList<Container> retrieveContainersByName(Connection connection, String name) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveContainersByName(connection, name);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
        }
        
        return containerList;
    }
    
    ArrayList<Container> retrieveContainersByProductFamily(Connection connection, String family) throws SQLException {
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        
        ArrayList<Container> containerList = containerInstance.retrieveContainersByProductFamily(connection, family);
        for(int i = 0; i < containerList.size() - 1; i++) {
            Container container = containerList.get(i);               
            containerList.set(i, addComponentsToContainer(connection, container));
        }
        
        return containerList;
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