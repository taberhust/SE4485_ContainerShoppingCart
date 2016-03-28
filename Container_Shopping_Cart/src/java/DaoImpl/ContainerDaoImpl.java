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
import java.sql.ResultSet;

/**
 *
 * @author matt
 */
public class ContainerDaoImpl implements ContainerDAO{

    @Override
    public ArrayList<Container> retrieveContainer(Connection connection) throws SQLException {
        PreparedStatement ps = null;
        try{
            String retrieveSQL = "SELECT * FROM ___;";
            ps = connection.prepareStatement(retrieveSQL);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                    return null;
            }
            ArrayList<Container> containerList = new ArrayList<>();
            containerList.add();
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
        }
        
    }

    @Override
    public Container retrieveContainer(Connection connection, String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Container addContainer(Connection connection, File image) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Container editContainer(Connection connection, String id, File image) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteContainer(Connection connection, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
