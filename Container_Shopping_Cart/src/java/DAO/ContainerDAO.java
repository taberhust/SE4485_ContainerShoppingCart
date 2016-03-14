/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Container;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author matt
 */
public interface ContainerDAO {
    
    ArrayList<Container> retrieveContainer(Connection connection) throws SQLException;
    Container retrieveContainer(Connection connection, String containerid) throws SQLException;
    Container addContainer(Connection connection, File image) throws SQLException;
    Container editContainer(Connection connection,String id, File image) throws SQLException;
    void deleteContainer(Connection connection, String id) throws SQLException;
    
}
