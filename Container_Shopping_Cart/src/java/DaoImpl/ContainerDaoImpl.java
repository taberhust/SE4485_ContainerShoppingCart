/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import DAO.ContainerDAO;
import Entity.Container;
import java.awt.Image;
import java.sql.ResultSet;

/**
 *
 * @author matt & kevin
 */
public class ContainerDaoImpl implements ContainerDAO{

    @Override
    public ArrayList<Container> retrieveAllContainers(Connection connection) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String retrieveSQL = "SELECT * FROM Container;";
            ps = connection.prepareStatement(retrieveSQL);
            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            // Increment through results and build list of containers
            ArrayList<Container> containerList = new ArrayList<>();
            while(rs.next()) {
                Container container = new Container();
                container.setContainerID(rs.getLong("containerID"));
                container.setIcon((Image)rs.getObject("icon"));
                container.setName(rs.getString("name"));
                container.setVersion(rs.getString("version"));
                container.setProductName(rs.getString("productName"));
                container.setCategory(rs.getString("category"));
                containerList.add(container);
            }
            return containerList;
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
    public Container retrieveContainer(Connection connection, String id) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String retrieveSQL = "SELECT * FROM Container WHERE containerID = ?";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            // Construct the container and return it
            rs.next();
            Container container = new Container();
            container.setContainerID(rs.getString("containerID"));
            container.setIcon((Image)rs.getObject("icon"));
            container.setName(rs.getString("name"));
            container.setVersion(rs.getString("version"));
            container.setProductName(rs.getString("productName"));
            container.setCategory(rs.getString("category"));

            return container;
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
    public ArrayList<Container> retrieveContainersByCategory(Connection connection, String category) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String retrieveSQL = "SELECT * FROM Container WHERE category = ?;";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            // Increment through results and build list of containers
            ArrayList<Container> containerList = new ArrayList<>();
            while(rs.next()) {
                Container container = new Container();
                container.setContainerID(rs.getString("containerID"));
                container.setIcon((Image)rs.getObject("icon"));
                container.setName(rs.getString("name"));
                container.setVersion(rs.getString("version"));
                container.setProductName(rs.getString("productName"));
                container.setCategory(rs.getString("category"));
                containerList.add(container);
            }
            return containerList;
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
    public ArrayList<Container> retrieveContainersByName(Connection connection, String name) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String retrieveSQL = "SELECT * FROM Container WHERE UPPER(name) LIKE ?;";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setString(1, "%" + name.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            // Increment through results and build list of containers
            ArrayList<Container> containerList = new ArrayList<>();
            while(rs.next()) {
                Container container = new Container();
                container.setContainerID(rs.getString("containerID"));
                container.setIcon((Image)rs.getObject("icon"));
                container.setName(rs.getString("name"));
                container.setVersion(rs.getString("version"));
                container.setProductName(rs.getString("productName"));
                container.setCategory(rs.getString("category"));
                containerList.add(container);
            }
            return containerList;
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
    public boolean addContainer(Connection connection, Container container) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String insertSQL = "Insert INTO Container Values (?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, container.getContainerID());
            ps.setString(2, container.getName());
            ps.setString(3, container.getVersion());
            ps.setObject(4, container.getIcon());
            ps.setString(5, container.getCategory());
            ps.setString(6, container.getProductName());
            ps.executeUpdate();
            
            return true;
        }
        catch(Exception ex){
            System.out.println("Exception in ContainerDaoImpl.retrieveContainer()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return false;
        }
    }

    @Override
    public boolean editContainer(Connection connection, String id, Container container) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String editSQL = "UPDATE Container SET containerID = ?, name = ?,"
                    + " version = ?, icon = ?, category = ?, productName = ?"
                    + "WHERE containerID = ?;";
            ps = connection.prepareStatement(editSQL);
            ps.setString(1, container.getContainerID());
            ps.setString(2, container.getName());
            ps.setString(3, container.getVersion());
            ps.setObject(4, container.getIcon());
            ps.setString(5, container.getCategory());
            ps.setString(6, container.getProductName());
            ps.setString(7, id);
            ps.executeUpdate();
            
            return true;
        }
        catch(Exception ex){
            System.out.println("Exception in ContainerDaoImpl.retrieveContainer()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            
            return false;
        }
    }

    @Override
    public boolean deleteContainer(Connection connection, String id) throws SQLException{
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String deleteSQL = "DELETE FROM Container WHERE containerID = ?;";
            ps = connection.prepareStatement(deleteSQL);
            ps.setString(1, id);
            ps.executeUpdate();
            
            return true;
        }
        catch(Exception ex){
            System.out.println("Exception in ContainerDaoImpl.retrieveContainer()");
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
