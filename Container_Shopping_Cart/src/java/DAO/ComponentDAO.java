/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Component;

/**
 * ComponentDAO interface
 * 
 * @author matt & kevin
 */
public interface ComponentDAO {
    
    // TESTING ENVIRONMENT FUNCTION ONLY
    Component addComponentFT(Connection connection, Component component) throws SQLException;
    
    Component addComponent(Connection connection, Component component) throws SQLException;
    
    Component getComponent(Connection connection, Long id) throws SQLException;
    
    boolean deleteComponent(Connection connection, Long id) throws SQLException;
}
