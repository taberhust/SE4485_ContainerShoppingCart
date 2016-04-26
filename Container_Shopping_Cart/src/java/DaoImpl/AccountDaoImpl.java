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
 * Implementation of AccountDAO interface
 * 
 * @author matt & kevin
 */
public class AccountDaoImpl implements AccountDAO{
    
    /**
     * TESTING ENVIRONMENT FUNCTION ONLY
     * Creates the account in the database
     * 
     * @param connection Connection to be used
     * @param account Account to create in the table
     * @return The account that was added or null if it failed
     * @throws SQLException 
     */
    @Override
    public Account createAccountFT(Connection connection, Account account) throws SQLException{
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Account (userID, userName, firstName, lastName, privilege, password) VALUES (?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, account.getUserID().toString());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getFirstName());
            ps.setString(4, account.getLastName());
            ps.setString(5, account.getPrivilege());
            ps.setString(6, account.getPassword());
            
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
    
    /**
     * Create account in the database
     * 
     * @param connection Connection to be used
     * @param account Account to be created
     * @return Account with the generated ID or null if it failed
     * @throws SQLException 
     */
    //CreateAccount() for AUTO_INCREMENT primary key
    @Override
    public Account createAccount(Connection connection, Account account) throws SQLException{
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO Account (userName, firstName, lastName, privilege, password) VALUES (?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getFirstName());
            ps.setString(3, account.getLastName());
            ps.setString(4, account.getPrivilege());
            ps.setString(5, account.getPassword());
            
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
    
    /**
     * Retrieve the account associated with the specified username
     * 
     * @param connection Connection to be used
     * @param username
     * @return
     * @throws SQLException 
     */
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
            account.setPassword(rs.getString("password"));
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
    
    /**
     * Delete the account specified by the username
     * 
     * @param connection Connection to be used
     * @param username Username of the user
     * @throws SQLException 
     */
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