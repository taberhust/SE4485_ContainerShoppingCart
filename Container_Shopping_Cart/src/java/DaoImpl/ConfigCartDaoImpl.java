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
import Entity.Configurations;
import Entity.Container;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author matt & kevin
 */
public class ConfigCartDaoImpl implements ConfigCartDAO {
    
    @Override
    public void addToConfigCart(Connection connection, Long userID, ArrayList<ConfigCart> configsToAdd) throws SQLException{
        PreparedStatement ps = null;
        try{
         for(ConfigCart configCart: configsToAdd) {
             String insertSQL = "INSERT INTO ConfigCart (userID, cartContainerID, displayName, "
                     + "userType, userArg1, userArg2) VALUES (?, ?, ?, ?, ?, ?);";
             ps = connection.prepareStatement(insertSQL);
             ps.setString(1, userID.toString());
             ps.setString(2, configCart.getCartContainerID().toString());
             ps.setString(3, configCart.getDisplayName());
             ps.setString(4, configCart.getUserType());
             ps.setString(5, configCart.getUserArg1());
             ps.setString(6, configCart.getUserArg2());
             
             ps.executeUpdate();
         }  
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Exception in ConfigCartDaoImpl.addToConfigCart(ConfigCart List)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        
    }
    
    @Override
    public boolean addToConfigCart(Connection connection, Long userID, Container container) throws SQLException {
        PreparedStatement ps = null;
        try{
            ArrayList<Configuration> cartContainerConfigs = container.getConfigurations();
            for(Configuration configuration: cartContainerConfigs){
                String insertSQL = "INSERT INTO ConfigCart (userID, cartContainerID, displayName"
                        + "userType, userArg1, userArg2) VALUES (?, ?, ?, ?, ?, ?);";
                ps = connection.prepareStatement(insertSQL);
                ps.setString(1, userID.toString());
                ps.setString(2, container.getContainerID().toString());
                ps.setString(3, configuration.getDisplayName());
                ps.setString(4, configuration.getDefaultType());
                ps.setString(5, configuration.getDefaultArg1());
                ps.setString(6, configuration.getDefaultArg2());
            
                ps.executeUpdate();
            }
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Exception in ConfigCartDaoImpl.addToConfigCart(Container)");
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
    public boolean createConfigCart(Connection connection, Long userID, Container container) throws SQLException {
        PreparedStatement ps = null;
        try{
            ArrayList<Configuration> cartContainerConfigs = container.getConfigurations();
            for(Configuration configuration: cartContainerConfigs){
                String insertSQL = "INSERT INTO ConfigCart (userID, cartContainerID, displayName, userType, "
                    + "userArg1, userArg2) VALUES (?, ?, ?, ?, ?);";
                ps = connection.prepareStatement(insertSQL);
                ps.setString(1, userID.toString());
                ps.setString(2, container.getContainerID().toString());
                ps.setString(3, configuration.getDisplayName());
                ps.setString(4, configuration.getDefaultType());
                ps.setString(5, configuration.getDefaultArg1());
                ps.setString(6, configuration.getDefaultArg2());
            
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
            String insertSQL = "INSERT INTO ConfigCart (userID, cartContainerID, displayName, userType, "
                    + "userArg1, userArg2) VALUES (?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, configCart.getUserID().toString());
            ps.setString(2, configCart.getCartContainerID().toString());
            ps.setString(3, configCart.getDisplayName());
            ps.setString(4, configCart.getUserType());
            ps.setString(5, configCart.getUserArg1());
            ps.setString(6, configCart.getUserArg2());
            
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

//    public ArrayList<ConfigCart> retrieveConfigCart(Connection connection, Long userID) throws SQLException{
//        PreparedStatement ps = null;
//        try{
//            String insertSQL = "SELECT * FROM ConfigCart WHERE userID = ?;";
//            ps = connection.prepareStatement(insertSQL);
//            ps.setLong(1, userID);
//            
//            ResultSet rs = ps.executeQuery();
//            
//            ArrayList<ConfigCart> configCartList = new ArrayList<>();
//            ArrayList<Container> containersInCart = new ArrayList<>();
//            //hashmap
//            while(rs.next()) {
//                ConfigCart configCart = new ConfigCart();
//                configCart.setUserID(rs.getLong("userID"));
//                //if cartContainerIt doesn't exist in map
//                HashMap<Long, Container> cartMap = new HashMap<Long, Container>(); 
//                //add to map Configuration configToAddToContainer = new Configuration
//                //configToAddToContainer.setConfigurationID(rs.getLong("cartContainerID"));
//                //configToAddToContainer.setDisplayName(rs.getString("displayName"));
//                //configToAddToContainer.setDefaultType(rs.getString("userType"));
//                //configToAddToContainer.setArg1(rs.getString("userArg1"));
//                //configToAddToContainer.setArg2(rs.getString("userArg2"));
//                //Container.addConfiguration(configToAddToContainer);
//                configCart.setCartContainerID(rs.getLong("cartContainerID"));
//                configCart.setDisplayName(rs.getString("displayName"));
//                configCart.setUserType(rs.getString("userType"));
//                configCart.setUserArg1(rs.getString("userArg1"));
//                configCart.setUserArg2(rs.getString("userArg2"));
//                configCartList.add(configCart);
//            }
//            for(Configuration configuration: configCartList){
//                
//            }
//            
//            
//            
//            
//            
//            return configCartList;
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//            System.out.println("Exception in ConfigCartDaoImpl.retrieveCart()");
//            if (ps != null && !ps.isClosed()){
//                ps.close();
//            }
//            if (connection != null && !connection.isClosed()){
//                connection.close();
//            }
//            return null;
//        }
//    }
 
    public ArrayList<ConfigCart> getConfigCart(Connection connection, Long cartContainerID) throws SQLException {
        PreparedStatement ps = null;
        try{
            String retrieveSQL = "SELECT * FROM ConfigCart WHERE cartContainerID = ?;";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setLong(1, cartContainerID);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.isBeforeFirst()){
                    return null;
            }

            ArrayList<ConfigCart> configCartList = new ArrayList<>();
            while(rs.next()) {
                ConfigCart configCart = new ConfigCart();
                configCart.setCartContainerID(rs.getLong("cartContainerID"));
                configCart.setDisplayName(rs.getString("displayName"));
                configCart.setUserType(rs.getString("userType"));
                configCart.setUserArg1(rs.getString("userArg1"));
                configCart.setUserArg2(rs.getString("userArg2"));
                configCartList.add(configCart);
            }
            return configCartList;
        }
        
        catch(Exception ex){
            System.out.println("Exception in ConfigurationsDaoImpl.getConfiguration()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return null;
        }
    }    
    
    public Configuration getCartConfigs(Connection connection, Long configID) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "SELECT * FROM ConfigCart WHERE cartConfigurationID = ?;";
            ps = connection.prepareStatement(insertSQL);
            ps.setLong(1, configID);
            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            Configuration configuration = new Configuration();
            rs.next();
            configuration.setConfigurationID(rs.getLong("cartConfigurationID"));
            configuration.setDisplayName(rs.getString("displayName"));
            configuration.setDefaultType(rs.getString("userType"));
            configuration.setDefaultArg1(rs.getString("userArg1"));
            configuration.setDefaultArg2(rs.getString("userArg2"));
            
            return configuration;
        }
        catch(Exception ex){
            //ex.printStackTrace();
            System.out.println("Exception in ConfigurationDaoImpl.getConfiguration()");
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