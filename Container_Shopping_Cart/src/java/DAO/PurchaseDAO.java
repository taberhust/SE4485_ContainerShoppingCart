/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Purchase;
import java.util.ArrayList;

/**
 * PurchaseDAO interface
 * 
 * @author matt & kevin
 */
public interface PurchaseDAO {
    
    // TESTING ENVIRONMENT FUNCTION ONLY
    Purchase createPurchase(Connection connection, Purchase purchase) throws SQLException;
    Purchase addPurchase(Connection connection, Purchase purchase) throws SQLException;
    ArrayList<Purchase> retrievePurchases(Connection connection, Long userID) throws SQLException;
    boolean editPurchase(Connection connection, Purchase purchase) throws SQLException;
    boolean deletePurchase(Connection connection, Long purchaseID) throws SQLException;
}
