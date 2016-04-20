/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.ConfigCart;
import Entity.Container;
import java.util.ArrayList;

/**
 *
 * @author matt & kevin
 */
public interface ConfigCartDAO {
    
    public void addToConfigCart(Connection connection, Long userID, ArrayList<ConfigCart> configsToAdd) throws SQLException;
    
    public boolean addToConfigCart(Connection connection, Long userID, Container container) throws SQLException;
    
    boolean createConfigCart(Connection connection, Long userID, Container container) throws SQLException;
    
    ConfigCart createConfigCart(Connection connection, ConfigCart configCart) throws SQLException;
    
}
