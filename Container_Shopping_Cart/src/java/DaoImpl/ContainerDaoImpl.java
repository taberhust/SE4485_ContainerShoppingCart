/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import DAO.ContainerDAO;
import Entity.Container;
import java.sql.ResultSet;
import java.sql.Statement;

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
                container.setDockerID(rs.getString("dockerID"));
                container.setDockerName(rs.getString("dockerName"));
                container.setContainerName(rs.getString("containerName"));  
                container.setPathToIcon(rs.getString("pathToIcon"));
                container.setCategory(rs.getString("category"));
                container.setProductFamily(rs.getString("productFamily"));
                container.setVersion(rs.getString("version")); 
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
    public Container retrieveContainer(Connection connection, Long id) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String retrieveSQL = "SELECT * FROM Container WHERE containerID = ?";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            // Check if the result is empty
            if(!rs.isBeforeFirst()){
                    return null;
            }
            
            // Construct the container and return it
            rs.next();
            Container container = new Container();
            container.setContainerID(rs.getLong("containerID"));
            container.setDockerID(rs.getString("dockerID"));
            container.setDockerName(rs.getString("dockerName"));
            container.setContainerName(rs.getString("containerName"));    
            container.setPathToIcon(rs.getString("pathToIcon"));
            container.setCategory(rs.getString("category"));
            container.setProductFamily(rs.getString("productFamily"));
            container.setVersion(rs.getString("version"));

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
            String retrieveSQL = "SELECT * FROM Container WHERE UPPER(category) LIKE ?;";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setString(1, category.toUpperCase());
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
                container.setDockerID(rs.getString("dockerID"));
                container.setDockerName(rs.getString("dockerName"));
                container.setContainerName(rs.getString("containerName"));
                container.setPathToIcon(rs.getString("pathToIcon"));
                container.setCategory(rs.getString("category"));
                container.setProductFamily(rs.getString("productFamily"));
                container.setVersion(rs.getString("version"));
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
            String retrieveSQL = "SELECT * FROM Container WHERE UPPER(containerName) LIKE ?;";
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
                container.setContainerID(rs.getLong("containerID"));
                container.setDockerID(rs.getString("dockerID"));
                container.setDockerName(rs.getString("dockerName"));
                container.setContainerName(rs.getString("containerName"));  
                container.setPathToIcon(rs.getString("pathToIcon"));
                container.setCategory(rs.getString("category"));
                container.setProductFamily(rs.getString("productFamily"));
                container.setVersion(rs.getString("version"));
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
    public ArrayList<Container> retrieveContainersByProductFamily(Connection connection, String productFamily) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String retrieveSQL = "SELECT * FROM Container WHERE UPPER(productFamily) LIKE ?;";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setString(1, productFamily.toUpperCase());
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
                container.setDockerID(rs.getString("dockerID"));
                container.setDockerName(rs.getString("dockerName"));
                container.setContainerName(rs.getString("containerName"));
                container.setPathToIcon(rs.getString("pathToIcon"));
                container.setCategory(rs.getString("category"));
                container.setProductFamily(rs.getString("productFamily"));
                container.setVersion(rs.getString("version")); 
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
    public Container addContainerFT(Connection connection, Container container) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "Insert INTO Container " +
                    "(containerID, dockerID, dockerName, containerName, pathToIcon, category, productFamily, version) " + 
                    "  Values (?, ?, ?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setLong(1, container.getContainerID());
            ps.setString(2, container.getDockerID());
            ps.setString(3, container.getDockerName());
            ps.setString(4, container.getContainerName());
            ps.setString(5, container.getPathToIcon());
            ps.setString(6, container.getCategory());
            ps.setString(7, container.getProductFamily());
            ps.setString(8, container.getVersion());
            ps.executeUpdate();
            
            return container;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ContainerDaoImpl.retrieveContainer()");
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
    public Container addContainer(Connection connection, Container container) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "Insert INTO Container " +
                    "(dockerID, dockerName, containerName, pathToIcon, category, productFamily, version) " + 
                    "  Values (?, ?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, container.getDockerID());
            ps.setString(2, container.getDockerName());
            ps.setString(3, container.getContainerName());
            ps.setString(4, container.getPathToIcon());
            ps.setString(5, container.getCategory());
            ps.setString(6, container.getProductFamily());
            ps.setString(7, container.getVersion());
            ps.executeUpdate();
            
            // Return generated ID
            ResultSet keyRS = ps.getGeneratedKeys();
            keyRS.next();
            int lastKey = keyRS.getInt(1);
            container.setContainerID((long) lastKey);
            
            return container;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in ContainerDaoImpl.retrieveContainer()");
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
    public boolean editContainer(Connection connection, Long id, Container container) throws SQLException {
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String editSQL = "UPDATE Container SET containerID = ?, dockerID = ?,"
                    + " dockerName = ?, containerName = ?, "
                    + "pathToIcon = ?, category = ?, productFamily = ?, version = ?"
                    + "WHERE containerID = ?;";
            ps = connection.prepareStatement(editSQL);
            ps.setLong(1, container.getContainerID());
            ps.setString(2, container.getDockerID());
            ps.setString(3, container.getDockerName());
            ps.setString(4, container.getContainerName());
            ps.setObject(6, container.getPathToIcon());
            ps.setString(7, container.getCategory());
            ps.setString(8, container.getProductFamily());
            ps.setString(5, container.getVersion());
            ps.setLong(9, id);
            ps.executeUpdate();
            
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
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
    public boolean deleteContainer(Connection connection, Long id) throws SQLException{
        PreparedStatement ps = null;
        try{
            // Prepare the statement
            String deleteSQL = "DELETE FROM Container WHERE containerID = ?;";
            ps = connection.prepareStatement(deleteSQL);
            ps.setLong(1, id);
            ps.executeUpdate();
            
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
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
