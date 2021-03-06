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
import java.util.ArrayList;

/**
 * Implementation of CartDAO interface
 * 
 * @author matt & kevin
 */
public class CartDaoImpl implements CartDAO{
    
    /**
     * Insert container into user's cart
     * 
     * @param connection Connection to be used
     * @param userID ID of the user whose cart is being modified
     * @param container Container to add to the cart
     * @throws SQLException 
     */
    @Override
    public void createCart(Connection connection, Long userID, Container container) throws SQLException{
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Cart (userID, cartContainerID) VALUES (?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, userID.toString());
            ps.setString(2, container.getContainerID().toString());
            
            ps.executeUpdate();
            
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
    }

    /**
     * TESTING ENVIRONMENT FUNCTION ONLY
     * Add the cart object into the cart database
     * 
     * @param connection Connection to be used
     * @param cart Cart object to be added to the database
     * @return The cart object
     * @throws SQLException 
     */
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

    /**
     * TESTING ENVIRONMENT FUNCTION ONLY
     * Add cart object to user's account
     * 
     * @param connection Connection to be used
     * @param cart Cart object to be added to the database
     * @param userID ID of the user associated with the cart
     * @return The cart object inserted
     * @throws SQLException 
     */
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
    
    /**
     * Retrieve the containers in the user's cart
     * 
     * @param connection Connection to be used
     * @param userID ID of the user associated with the cart
     * @return ArrayList of containers in the user's cart
     * @throws SQLException 
     */
    public ArrayList<Container> retrieveCart(Connection connection, Long userID) throws SQLException{
        PreparedStatement ps = null;
        try{
            String retrieveSQL = "SELECT * FROM Cart WHERE userID = ?;";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setLong(1, userID);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            ArrayList<Container> containerList = new ArrayList<>();
            while(rs.next()){
                ContainerDaoImpl containerDaoImpl = new ContainerDaoImpl();
                
                //This is to get a container with the fields we want inside.
                Container container = containerDaoImpl.retrieveContainer(connection, rs.getLong("cartContainerID"));
                ConfigCartDaoImpl cCDI = new ConfigCartDaoImpl();
                container.setConfigurations(cCDI.getConfigCart(connection, container.getContainerID(), userID));
               
                containerList.add(container);
            }
            return containerList;
        }
        catch(Exception ex){
            System.out.println("Exception in CartDaoImpl.retrieveCart(3 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return null;
    }
}
