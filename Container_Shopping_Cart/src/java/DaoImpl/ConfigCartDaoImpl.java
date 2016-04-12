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
import Entity.Configuration;
import Entity.Container;
import java.util.ArrayList;

/**
 *
 * @author matt & kevin
 */
public class ConfigCartDaoImpl implements ConfigCartDAO {
    
    @Override
    public boolean createConfigCart(Connection connection, Long userID, Container container) throws SQLException {
        PreparedStatement ps = null;
        try{
            ArrayList<Configuration> cartContainerConfigs = container.getConfigurations();
            for(Configuration configuration: cartContainerConfigs){
                String insertSQL = "INSERT INTO ConfigCart (userID, cartContainerID, userType, "
                    + "userArg1, userArg2) VALUES (?, ?, ?, ?, ?);";
                ps = connection.prepareStatement(insertSQL);
                ps.setString(1, userID.toString());
                ps.setString(2, container.getContainerID().toString());
                ps.setString(3, configuration.getDefaultType());
                ps.setString(4, configuration.getDefaultArg1());
                ps.setString(5, configuration.getDefaultArg2());
            
                ps.executeUpdate();
            }
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Exception in ConfigCartDaoImpl.createConfigCart(3 arg)");
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
