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

import DAO.ItemsDAO;
import Entity.Items;

/**
 *
 * @author matt & kevin
 */
public class ItemsDaoImpl implements ItemsDAO{

    @Override
    public Items createItems(Connection connection, Items items) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO ITEMS (purchaseID, containerID) VALUES (?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, items.getPurchaseID().toString());
            ps.setString(2, items.getContainerID().toString());

            ps.executeUpdate();
            
            return items;
        }
        catch(Exception ex){
            System.out.println("Exception in ItemsDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return items; 
    }
    
}
