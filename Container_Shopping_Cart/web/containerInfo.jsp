<%-- 
    Document   : gridview
    Created on : Mar 27, 2016, 5:58:28 PM
    Author     : Charles
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.ContainerDAO"%>
<%@page import="Entity.Container"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css"/>
        <title>NTT DATA</title>
    </head>
    <body>
        <div class= "Section_one_top">
            <div class= "div1_head_blue">
         <p class="ntt_logo"> <div class="ntt_logo_box"> <b>NTT DaTa <b></div><br> </p>
         <p class="name_logout"> Name | Logout </p>
	</div>
	<div class="div2">
	</div>
        <h1 align="center">NTT DATA</h1>
        <hr>
        <p><b> Container </b></p>
        <br>
        <label for="containerID">Container ID:
<input type="text" name="containerID" id="containerID" /></label>
        <br>

<label for="startDate">Search from:
<input type="text" name="startDate" id="startDate" /></label>

<label for="endDate">End Date:
<input type="text" name="endDate" id="endDate" /></label>

        <br><br><br><br>
        
        <label for="search">Search:
<input type="text" name="search" id="search" /></label>

<select name="Description">
<option>Application</option>
<option>Operating System</option>
</select>
        
        <input type="submit" />
        <hr>
        
        <%  
// retrieve your list from the request, with casting 
ArrayList<Container> list = (ArrayList<Container>) session.getAttribute("containersRetrieved");

%>
        
        <table style="width:100%">
            
            <th align="left">Container ID</th>
            <th align="left">Docker ID</th>
            <th align="left">Docker Name</th>
            <th align="left">Container Name</th>
            <th align="left">Category</th>
            <th align="left">Product Family</th>
            <th align="left">Version</th>

            
            <% for(Container c: list)  {%>
  <tr>
      <td><a href="HierarchicalServlet?p1=<%=c.getContainerID()%>"><%out.println(c.getContainerID());%></a></td>
      <td><%out.println(c.getDockerID());%></td>
      <td><%out.println(c.getDockerName());%></td>
      <td><%out.println(c.getContainerName());%></td>
      <td><%out.println(c.getCategory());%></td>
      <td><%out.println(c.getProductFamily());%></td>
      <td><%out.println(c.getVersion());%></td>
    
  </tr>
  <% } %>
</table>
    </body>
</html>
