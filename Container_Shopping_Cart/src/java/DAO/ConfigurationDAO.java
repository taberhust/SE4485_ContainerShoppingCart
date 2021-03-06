/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Configuration;

/**
 * ConfigurationDAO interface
 * 
 * @author matt & kevin
 */
public interface ConfigurationDAO {
    
    Configuration createConfiguration(Connection connection, Configuration configuration) throws SQLException;
    
    // TESTING ENVIRONMENT FUNCTION ONLY
    Configuration createConfigurationFT(Connection connection, Configuration configuration) throws SQLException;
    
    Configuration getConfiguration(Connection connection, Long configID) throws SQLException;
}
