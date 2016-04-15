/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import DAO.AccountDAO;
import Entity.Account;


/**
 *@author matt & kevin
 * 
 * This only updates the Account table
 */
public class AccountDaoImpl implements AccountDAO{
    
    @Override
    public Account createAccountFT(Connection connection, Account account) throws SQLException{
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Account (userID, userName, firstName, lastName, privilege) VALUES (?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, account.getUserID().toString());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getFirstName());
            ps.setString(4, account.getLastName());
            ps.setString(5, account.getPrivilege());
            
            ps.executeUpdate();
            
            return account;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in AccountDaoImpl.create()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return account;
    }
    
    //CreateAccount() for AUTO_INCREMENT primary key
    @Override
    public Account createAccount(Connection connection, Account account) throws SQLException{
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Account (userName, firstName, lastName, privilege) VALUES (?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getFirstName());
            ps.setString(3, account.getLastName());
            ps.setString(4, account.getPrivilege());
            
            ps.executeUpdate();
            
            ResultSet fullAccount = ps.getGeneratedKeys();
            fullAccount.next();
            int genKey = fullAccount.getInt(1);
            account.setUserID((long) genKey);
            
            return account;
        }
        catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("Exception in AccountDaoImpl.create()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return account;
    }
    
    @Override
    public Account retrieveAccount(Connection connection, String username) throws SQLException{
        PreparedStatement ps = null;
        try{
            String retrieveSQL = "SELECT * FROM Account WHERE Account.userName = ?;";
            ps = connection.prepareStatement(retrieveSQL);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                    return null;
            }
            Account account = new Account();
            account.setUserID(Long.valueOf(rs.getString("userID")));
            account.setUserName(rs.getString("userName"));
            account.setFirstName(rs.getString("firstName"));
            account.setLastName(rs.getString("lastName"));
            account.setPrivilege(rs.getString("privilege"));
            return account;
        }
        catch(Exception ex){
            System.out.print("Exception in AccountDaoImpl.retrieveAccount()");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        return null;
    } 
    
    @Override
    public void deleteAccount(Connection connection, String username) throws SQLException{
        PreparedStatement ps = null;
        try{
            String deleteSQL = "DELETE FROM Account WHERE Account.userName = ?;";
            ps = connection.prepareStatement(deleteSQL);
            ps.setString(1, username);
            ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("Exception in AccountDaoImpl.deleteAccount()");
            if(ps != null && !ps.isClosed()){
                ps.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }
    
}