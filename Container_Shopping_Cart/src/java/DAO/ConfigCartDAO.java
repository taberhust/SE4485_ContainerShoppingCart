/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.ConfigCart;

/**
 *
 * @author matt & kevin
 */
public interface ConfigCartDAO {
    
    ConfigCart createConfigCart(Connection connection, ConfigCart configCart) throws SQLException;
    
}