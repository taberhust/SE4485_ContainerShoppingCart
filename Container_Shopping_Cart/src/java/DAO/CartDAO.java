/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Cart;
import Entity.Container;

/**
 * CartDAO interface
 * @author matt & kevin
 */
public interface CartDAO {
    
    void createCart(Connection connection, Long userID, Container container) throws SQLException;
    // TESTING ENVIRONMENT FUNCTION ONLY
    Cart createCart(Connection connection, Cart cart) throws SQLException;
    Cart createCart(Connection connection, Cart cart, Long userID) throws SQLException;
    
}
