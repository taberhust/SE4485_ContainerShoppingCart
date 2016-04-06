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

/**
 *
 * @author matt & kevin
 */
public class PurchaseDaoImpl implements PurchaseDAO{

    @Override
    public Purchase createPurchase(Connection connection, Purchase purchase) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Purchase (purchaseID, userID, timeOfPurchase) VALUES (?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, purchase.getPurchaseID().toString());
            ps.setString(2, purchase.getUserID().toString());
            ps.setString(2, purchase.getTimeOfPurchase().toString());
            
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
        }
        return purchase;     
    }
    
}
