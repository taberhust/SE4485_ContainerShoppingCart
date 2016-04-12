/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.CartDAO;
import Entity.Cart;
import Entity.Container;

/**
 *
 * @author matt & kevin
 */
public class CartDaoImpl implements CartDAO{
    
    @Override
    public boolean createCart(Connection connection, Long userID, Container container) throws SQLException{
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Cart (userID, cartContainerID) VALUES (?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, userID.toString());
            ps.setString(2, container.getContainerID().toString());
            
            ps.executeUpdate();
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Exception in CartDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return false;
        
    }

    @Override
    public Cart createCart(Connection connection, Cart cart) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Cart (userID, cartContainerID) VALUES (?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, cart.getUserID().toString());
            ps.setString(2, cart.getCartContainerID().toString());
            
            ps.executeUpdate();
            
            return cart;
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Exception in CartDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return cart;    
    }

    @Override
    public Cart createCart(Connection connection, Cart cart, Long userID) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Cart (userID, cartContainerID) VALUES (?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, userID.toString());
            ps.setString(2, cart.getCartContainerID().toString());
            
            ps.executeUpdate();
            
            return cart;
        }
        catch(Exception ex){
            System.out.println("Exception in CartDaoImpl.create(3 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return cart;
    }
    
}
