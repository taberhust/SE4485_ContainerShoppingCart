/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;

/**
 *
 * @author matt
 */
public class ClearDB {
    public static void main(String args[]){
	try{
            DataSource dataSource = DBConnection.getDataSource();
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            PreparedStatement ps3 = null;
            PreparedStatement ps4 = null;
            PreparedStatement ps5 = null;
            PreparedStatement ps6 = null;
            PreparedStatement ps7 = null;
            PreparedStatement ps8 = null;
            PreparedStatement ps9 = null;
            PreparedStatement ps10 = null;
            String deleteAccount = "DELETE FROM Account";
            String deleteCart = "DELETE FROM Cart";
            String deleteComponent = "DELETE FROM Component";
            String deleteComponents = "DELETE FROM Components";
            String deleteConfigCart = "DELETE FROM ConfigCart";
            String deleteConfiguration = "DELETE FROM Configuration";
            String deleteConfigurations = "DELETE FROM Configurations";
            String deleteContainer = "DELETE FROM Container";
            String deleteItems = "DELETE FROM Items";
            String deletePurchase = "DELETE FROM Purchase";

            ps1 = connection.prepareStatement(deleteAccount);
            ps2 = connection.prepareStatement(deleteCart);
            ps3 = connection.prepareStatement(deleteComponent);
            ps4 = connection.prepareStatement(deleteComponents);
            ps5 = connection.prepareStatement(deleteConfigCart);
            ps6 = connection.prepareStatement(deleteConfiguration);
            ps7 = connection.prepareStatement(deleteConfigurations);
            ps8 = connection.prepareStatement(deleteContainer);
            ps9 = connection.prepareStatement(deleteItems);
            ps10 = connection.prepareStatement(deletePurchase);
            
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
            ps5.executeUpdate();
            ps6.executeUpdate();
            ps7.executeUpdate();
            ps8.executeUpdate();
            ps9.executeUpdate();
            ps10.executeUpdate();
            
            connection.commit();
            System.out.println("Finished clearing the tables");
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }    
}
