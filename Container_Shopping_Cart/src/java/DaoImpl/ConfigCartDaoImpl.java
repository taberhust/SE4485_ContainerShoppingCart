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

import DAO.ConfigCartDAO;
import Entity.ConfigCart;

/**
 *
 * @author matt & kevin
 */
public class ConfigCartDaoImpl implements ConfigCartDAO {

    @Override
    public ConfigCart createConfigCart(Connection connection, ConfigCart configCart) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO ConfigCart (userID, cartContainerID, userType, "
                    + "userArg1, userArg2) VALUES (?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, configCart.getUserID().toString());
            ps.setString(2, configCart.getCartContainerID().toString());
            ps.setString(3, configCart.getUserType());
            ps.setString(4, configCart.getUserArg1());
            ps.setString(5, configCart.getUserArg2());
            
            ps.executeUpdate();
            
            return configCart;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ConfigCartDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return configCart;         
    }
    
}
