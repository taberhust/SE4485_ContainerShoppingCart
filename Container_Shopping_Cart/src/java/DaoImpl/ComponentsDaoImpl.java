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

/**
 *
 * @author matt & kevin
 */
public class ComponentsDaoImpl implements ComponentsDAO{
    
    @Override
    public Components createComponents(Connection connection, Components components) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO COMPONENTS (containerID, componentID) VALUES (?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, components.getContainerID().toString());
            ps.setString(2, components.getComponentID().toString());

            ps.executeUpdate();
            
            return components;
        }
        catch(Exception ex){
            System.out.println("Exception in ComponentsDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return components;
    }        

    @Override
    public Components createComponents(Connection connection, Components components, Long containerID) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Components retrieveComponents(Connection connection, Long containerID) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteComponForContainer(Connection connection, Long containerID) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
