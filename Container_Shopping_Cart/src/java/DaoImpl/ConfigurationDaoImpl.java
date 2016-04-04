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

/**
 *
 * @author matt
 */
public class ConfigurationDaoImpl implements ConfigurationDAO{

    @Override
    public Configuration createConfiguration(Connection connection, Configuration configuration) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO CONFIGURATION (configurationID, fileName, lineNumber, displayName, defaultValue, configValue) VALUES (?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, configuration.getConfigurationID().toString());
            ps.setString(2, configuration.getFileName());
            ps.setString(3, configuration.getLineNumber().toString());
            ps.setString(4, configuration.getDisplayName());
            ps.setString(5, configuration.getDefaultValue());
            ps.setString(6, configuration.getConfigValue());            
            ps.executeUpdate();
            
            return configuration;
        }
        catch(Exception ex){
            System.out.println("Exception in ConfigurationDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return configuration;
    }
    
}
