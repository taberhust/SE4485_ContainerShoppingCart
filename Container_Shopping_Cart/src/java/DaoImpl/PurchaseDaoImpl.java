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

import DAO.PurchaseDAO;
import Entity.Purchase;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Implementation of the PurchaseDAO interface
 * 
 * @author matt & kevin
 */
public class PurchaseDaoImpl implements PurchaseDAO{

    /**
     * TESTING ENVIRONMENT FUNCTION ONLY
     * Create the purchase item in the table
     * 
     * @param connection Connection to be used
     * @param purchase Purchase object to add to the table
     * @return Purchase item that was passed in
     * @throws SQLException 
     */
    @Override
    public Purchase createPurchase(Connection connection, Purchase purchase) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Purchase (purchaseID, userID, timeOfPurchase) VALUES (?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, purchase.getPurchaseID().toString());
            ps.setString(2, purchase.getUserID().toString());
            ps.setString(3, purchase.getTimeOfPurchase().toString());
            
            ps.executeUpdate();
            
            return purchase;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in PurchaseDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return null;
        }
    }

    /**
     * Add a purchase to the purchase history
     * 
     * @param connection Connection to be used
     * @param purchase Purchase object to add to the table
     * @return Purchase with the generated ID
     * @throws SQLException 
     */
    @Override
    public Purchase addPurchase(Connection connection, Purchase purchase) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Purchase (userID, timeOfPurchase) VALUES (?, ?);";
            ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, purchase.getUserID().toString());
            ps.setString(2, purchase.getTimeOfPurchase().toString());
            
            ps.executeUpdate();
            
            // Return generated ID
            ResultSet keyRS = ps.getGeneratedKeys();
            keyRS.next();
            int lastKey = keyRS.getInt(1);
            purchase.setPurchaseID((long) lastKey);
            
            return purchase;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in PurchaseDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return null;
        }
    }

    /**
     * Retrieve the purchase history for the specified user
     * 
     * @param connection Connection to be used
     * @param userID ID of the user to whom the purchase belongs
     * @return ArrayList of purchases for the user
     * @throws SQLException 
     */
    @Override
    public ArrayList<Purchase> retrievePurchases(Connection connection, Long userID) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String retrieveSQL = "SELECT * FROM Purchase WHERE userID = ?;";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setLong(1, userID);
            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            // Increment through results and build list of purchases
            ArrayList<Purchase> purchaseList = new ArrayList<>();
            while(rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setUserID(rs.getLong("userID"));
                purchase.setPurchaseID(rs.getLong("purchaseID"));
                purchase.setTimeOfPurchase(rs.getDate("timeOfPurchase"));
                purchase.setItems(null);
                purchaseList.add(purchase);
            }
            return purchaseList;
        }
        
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ContainerDaoImpl.retrieveContainer()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return null;
        }
    }

    /**
     * Administrative function to edit the details of a purchase
     * 
     * @param connection Connection to be used
     * @param purchase Updated purchase object to replace the existing
     * @return True if the purchase was updated properly
     * @throws SQLException 
     */
    @Override
    public boolean editPurchase(Connection connection, Purchase purchase) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String editSQL = "UPDATE Purchase SET purchaseID = ?, userID = ?,"
                    + " timeOfPurchase = ? "
                    + "WHERE purchaseID = ?;";
            ps = connection.prepareStatement(editSQL);
            ps.setLong(1, purchase.getPurchaseID());
            ps.setLong(2, purchase.getUserID());
            ps.setDate(3, purchase.getTimeOfPurchase());
            ps.setLong(4, purchase.getPurchaseID());
            ps.executeUpdate();
            
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ContainerDaoImpl.retrieveContainer()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return false;
        }
    }

    /**
     * Administrative function to delete a purchase from the user's account
     * 
     * @param connection Connection to be used
     * @param purchaseID ID of the purchase
     * @return True if the purchase was deleted properly
     * @throws SQLException 
     */
    @Override
    public boolean deletePurchase(Connection connection, Long purchaseID) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String deleteSQL = "DELETE FROM Purchase WHERE purchaseID = ?;";
            ps = connection.prepareStatement(deleteSQL);
            ps.setLong(1, purchaseID);
            ps.executeUpdate();
            
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ContainerDaoImpl.retrieveContainer()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return false;
        }
    }
}
