/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Configurations;
import java.util.ArrayList;

/**
 * ConfigurationsDAO interface
 * 
 * @author matt & kevin
 */
public interface ConfigurationsDAO {
    
    ArrayList<Configurations> getConfigurations(Connection connection, Long containerID) throws SQLException;
    
    // TESTING ENVIRONMENT FUNCTION ONLY
    Configurations createConfigurations(Connection connection, Configurations configurations) throws SQLException;
    
    Configurations createConfigurations(Connection connection, Configurations configurations, Long containerID) throws SQLException;
    
    Configurations retrieveConfigurations(Connection connection, Long containerID) throws SQLException;
    
    void deleteConfigsForContainer(Connection connection, Long containerID) throws SQLException;
    
}
