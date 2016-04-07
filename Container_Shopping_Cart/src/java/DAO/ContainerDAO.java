/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Container;
import java.util.ArrayList;

/**
 *
 * @author matt & kevin
 */
public interface ContainerDAO {
    
    ArrayList<Container> retrieveAllContainers(Connection connection) throws SQLException;
    ArrayList<Container> retrieveContainersByCategory(Connection connection, String category) throws SQLException;
    ArrayList<Container> retrieveContainersByName(Connection connection, String name) throws SQLException;
    ArrayList<Container> retrieveContainersByProductFamily(Connection connection, String name) throws SQLException;
    Container createContainer(Connection connection, Container container) throws SQLException;
    Container retrieveContainer(Connection connection, String containerID) throws SQLException;
    boolean addContainer(Connection connection, Container container) throws SQLException;
    boolean editContainer(Connection connection, String id, Container container) throws SQLException;
    boolean deleteContainer(Connection connection, String id) throws SQLException;
    
}
