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

import Entity.Items;
import DAO.ItemsDAO;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author matt & kevin
 */
public class ItemsDaoImpl implements ItemsDAO{

    @Override
    public Items createItems(Connection connection, Items items) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Items (purchaseID, containerID) VALUES (?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, items.getPurchaseID().toString());
            ps.setString(2, items.getContainerID().toString());

            ps.executeUpdate();
            
            return items;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ItemsDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return null;
        }
    }


    @Override
    public ArrayList<Items> getItems(Connection connection, Long purchaseID) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "SELECT * FROM Items WHERE purchaseID = ?;";
            ps = connection.prepareStatement(insertSQL);
            ps.setLong(1, purchaseID);

            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            // Increment through results and build list of containers
            ArrayList<Items> itemList = new ArrayList<>();
            while(rs.next()) {
                Items item = new Items();
                item.setContainerID(rs.getLong("containerID"));
                item.setPurchaseID(rs.getLong("purchaseID")); 
                itemList.add(item);
            }
            
            return itemList;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ItemsDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return null;
        }
    }
}
