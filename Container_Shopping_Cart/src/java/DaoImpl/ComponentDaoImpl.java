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

/**
 *
 * @author matt
 */
public class ComponentDaoImpl implements ComponentDAO{

    @Override
    public Component createComponent(Connection connection, Component component) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO COMPONENT (componentID, imageID, componentName, componentType, version) VALUES (?, ?, ?, ?, ?);";
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
            System.out.println("Exception in ComponentDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return component;
    }
    
}
