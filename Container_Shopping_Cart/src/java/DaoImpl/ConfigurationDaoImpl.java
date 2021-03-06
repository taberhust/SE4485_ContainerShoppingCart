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

import DAO.ConfigurationDAO;
import Entity.Configuration;
import java.sql.Statement;

/**
 *Implmentation of the ConfigurationDAO interface
 * 
 * @author matt & kevin
 */
public class ConfigurationDaoImpl implements ConfigurationDAO{

    /**
     * Add the specified configuration to the table and return the ID
     * 
     * @param connection Connection to be used
     * @param configuration Configuration to add to the table
     * @return The configuration with the generated ID
     * @throws SQLException 
     */
    @Override
    public Configuration createConfiguration(Connection connection, Configuration configuration) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Configuration (displayName, defaultType, defaultArg1, defaultArg2) VALUES (?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, configuration.getDisplayName());
            ps.setString(2, configuration.getDefaultType());
            ps.setString(3, configuration.getDefaultArg1()); 
            ps.setString(4, configuration.getDefaultArg2()); 
            ps.executeUpdate();
            
            ResultSet fullConfiguration = ps.getGeneratedKeys();
            fullConfiguration.next();
            int genKey = fullConfiguration.getInt(1);
            configuration.setConfigurationID((long) genKey);
            
            return configuration;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ConfigurationDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return configuration;
    }
    
    /**
     * TESTING ENVIRONMENT FUNCTION ONLY
     * Add the specified configuration to the table
     * 
     * @param connection Connection to be used
     * @param configuration Configuration to add to the table
     * @return The configuration passed in
     * @throws SQLException 
     */
    @Override
    public Configuration createConfigurationFT(Connection connection, Configuration configuration) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Configuration (configurationID, displayName, defaultType, defaultArg1, defaultArg2) VALUES (?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, configuration.getConfigurationID().toString());
            ps.setString(2, configuration.getDisplayName());
            ps.setString(3, configuration.getDefaultType());
            ps.setString(4, configuration.getDefaultArg1()); 
            ps.setString(5, configuration.getDefaultArg2()); 
            ps.executeUpdate();
            
            return configuration;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ConfigurationDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return configuration;
    }
    
    /**
     * Retrieve the specified configuration
     * 
     * @param connection Connection to be used
     * @param configID ID of the configuration to retrieve
     * @return Configuration specified by the ID
     * @throws SQLException 
     */
    @Override
    public Configuration getConfiguration(Connection connection, Long configID) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "SELECT * FROM Configuration WHERE configurationID = ?;";
            ps = connection.prepareStatement(insertSQL);
            ps.setLong(1, configID);
            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            Configuration configuration = new Configuration();
            rs.next();
            configuration.setConfigurationID(rs.getLong("configurationID"));
            configuration.setDisplayName(rs.getString("displayName"));
            configuration.setDefaultType(rs.getString("defaultType"));
            configuration.setDefaultArg1(rs.getString("defaultArg1"));
            configuration.setDefaultArg2(rs.getString("defaultArg2"));
            
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
