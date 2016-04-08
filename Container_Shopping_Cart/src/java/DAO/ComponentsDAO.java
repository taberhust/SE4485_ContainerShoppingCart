/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Components;
import java.util.ArrayList;

/**
 *
 * @author matt & kevin
 */
public interface ComponentsDAO {
    
    Components addComponents(Connection connection, Components components) throws SQLException;
    ArrayList<Components> getComponents(Connection connection, Long containerID) throws SQLException;   
    boolean deleteComponentsFromContainer(Connection connection, Long containerID, Long componentID) throws SQLException;
}
