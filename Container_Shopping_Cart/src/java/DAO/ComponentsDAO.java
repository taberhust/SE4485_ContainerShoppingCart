/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Components;

/**
 *
 * @author matt & kevin
 */
public interface ComponentsDAO {
    
    Components createComponents(Connection connection, Components components) throws SQLException;
    
    Components createComponents(Connection connection, Components components, Long containerID) throws SQLException;
    
    Components retrieveComponents(Connection connection, Long containerID) throws SQLException;
    
    void deleteComponForContainer(Connection connection, Long containerID) throws SQLException;
    
}
