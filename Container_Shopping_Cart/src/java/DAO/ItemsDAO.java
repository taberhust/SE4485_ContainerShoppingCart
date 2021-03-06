/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Items;
import java.util.ArrayList;

/**
 * ItemsDAO interface
 * 
 * @author matt & kevin
 */
public interface ItemsDAO {
    
    Items createItems(Connection connection, Items items) throws SQLException;
    ArrayList<Items> getItems(Connection connection, Long purchaseID) throws SQLException;
}
