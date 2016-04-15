/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;  
  
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  

import Entity.Container;
import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

import DAOInterface.DAOInterface;
  
public class HierarchicalServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;
    ArrayList<Container> allContainers = new ArrayList<>();
    
    
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {
        
        DataSource dataSource;
        try {
            dataSource = DBConnection.getDataSource();
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            
            DAOInterface DAO = new DAOInterface();
            
            allContainers = new ArrayList<>(DAO.retrieveContainersByName(connection, request.getParameter("p1")));
        } catch (SQLException e) {
        }
  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false);  
        if(session!=null)  
        session.setAttribute("containersRetrieved", allContainers);
        
        RequestDispatcher rd=request.getRequestDispatcher("containerInfo.jsp");    
            rd.include(request,response);
  
         
  
        out.close();    
    }
    
    public void doGet(HttpServletRequest request,
                  HttpServletResponse response) throws IOException,
                                                       ServletException {
  doPost(request, response);
}
}  