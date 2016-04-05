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

import DAO.ConfigurationsDAO;
import Entity.Configurations;

/**
 *
 * @author matt & kevin
 */
public class ConfigurationsDaoImpl implements ConfigurationsDAO{

    @Override
    public Configurations createConfigurations(Connection connection, Configurations configurations) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO CONFIGURATIONS (containerID, configurationID) VALUES (?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, configurations.getContainerID().toString());
            ps.setString(2, configurations.getConfigurationID().toString());

            ps.executeUpdate();
            
            return configurations;
        }
        catch(Exception ex){
            System.out.println("Exception in ConfigurationsDaoImpl.create(2 arg)");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return configurations;
    }

    @Override
    public Configurations createConfigurations(Connection connection, Configurations configurations, Long containerID) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Configurations retrieveConfigurations(Connection connection, Long containerID) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteConfigsForContainer(Connection connection, Long containerID) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
