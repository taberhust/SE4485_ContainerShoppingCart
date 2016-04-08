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

import DAO.ComponentsDAO;
import Entity.Components;
import Entity.Container;
import java.util.ArrayList;

/**
 *
 * @author matt & kevin
 */
public class ComponentsDaoImpl implements ComponentsDAO{
    
    @Override
    public Components addComponents(Connection connection, Components components) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Components (containerID, componentID) VALUES (?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, components.getContainerID().toString());
            ps.setString(2, components.getComponentID().toString());

            ps.executeUpdate();
            
            return components;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ComponentsDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return null;
        }
    }        

    @Override
    public ArrayList<Components> getComponents(Connection connection, Long containerID) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String retrieveSQL = "SELECT * FROM Components WHERE containerID = ?;";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setLong(1, containerID);
            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            // Increment through results and build list of containers
            ArrayList<Components> componentsList = new ArrayList<>();
            while(rs.next()) {
                Components components = new Components();
                components.setContainerID(rs.getLong("containerID"));
                components.setComponentID(rs.getLong("componentID"));
                componentsList.add(components);
            }
            return componentsList;
        }
        
        catch(Exception ex){
            System.out.println("Exception in ContainerDaoImpl.retrieveContainer()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return null;
        }
    }

    @Override
    public boolean deleteComponentsFromContainer(Connection connection, Long containerID, Long componentID) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String deleteSQL = "DELETE FROM Components WHERE containerID = ? AND componentID = ?;";
            ps = connection.prepareStatement(deleteSQL);
            ps.setLong(1, containerID);
            ps.setLong(2, componentID);
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
