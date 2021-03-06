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

import DAO.ComponentDAO;
import Entity.Component;
import java.sql.Statement;

/**
 * Implementation of ComponentDAO interface
 * 
 * @author matt & kevin
 */
public class ComponentDaoImpl implements ComponentDAO{

    /**
     * TESTING ENVIRONMENT FUNCTION ONLY
     * Add the component to the database table
     * 
     * @param connection Connection to be used
     * @param component Component to be added to the database
     * @return Component that was added to the database
     * @throws SQLException 
     */
    @Override
    public Component addComponentFT(Connection connection, Component component) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Component (componentID, imageID, componentName, componentType, version) VALUES (?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, component.getComponentID().toString());
            ps.setString(2, component.getImageID());
            ps.setString(3, component.getComponentName());
            ps.setString(4, component.getComponentType());
            ps.setString(5, component.getVersion());
            
            ps.executeUpdate();
            
            return component;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ComponentDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            return component;
        }
    }

    /**
     * Add the component to the database
     * @param connection Connection to be used
     * @param component Component to be added
     * @return Component object with the generated ID
     * @throws SQLException 
     */
    @Override
    public Component addComponent(Connection connection, Component component) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Component (imageID, componentName, componentType, version) VALUES (?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, component.getImageID());
            ps.setString(2, component.getComponentName());
            ps.setString(3, component.getComponentType());
            ps.setString(4, component.getVersion());
            
            ps.executeUpdate();
            
            // Return generated ID
            ResultSet keyRS = ps.getGeneratedKeys();
            keyRS.next();
            int lastKey = keyRS.getInt(1);
            component.setComponentID((long) lastKey);
            
            return component;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ComponentDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            return component;
        }
    }
        
    /**
     * Retrieve the component by its ID
     * 
     * @param connection Connection to be used
     * @param id ID of the component to retrieve
     * @return The component from the database
     * @throws SQLException 
     */
    @Override
    public Component getComponent(Connection connection, Long id) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "SELECT * FROM Component WHERE componentID = ?;";
            ps = connection.prepareStatement(insertSQL);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            Component component = new Component();
            rs.next();
            component.setComponentID(rs.getLong("componentID"));
            component.setImageID(rs.getString("imageID"));
            component.setComponentName(rs.getString("componentName"));
            component.setComponentType(rs.getString("componentType"));
            component.setVersion(rs.getString("version"));
            
            return component;
        }
        catch(Exception ex){
            //ex.printStackTrace();
            System.out.println("Exception in ComponentDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return null;
        }
    }

    /**
     * Delete the specified component
     * 
     * @param connection Connection to be used
     * @param id ID of the component to delete
     * @return True if the component was deleted
     * @throws SQLException 
     */
    @Override
    public boolean deleteComponent(Connection connection, Long id) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String deleteSQL = "DELETE FROM Component WHERE componentID = ?;";
            ps = connection.prepareStatement(deleteSQL);
            ps.setLong(1, id);
            ps.executeUpdate();
            
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ComponentDaoImpl.deleteComponent()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return false;
        }
    }    
}
