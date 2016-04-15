<%-- 
    Document   : gridview
    Created on : Mar 27, 2016, 5:58:28 PM
    Author     : Charles
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.ContainerDAO"%>
<%@page import="Entity.Container"%>
<%@page import="DAOInterface.DAOInterface"%>
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
DAOInterface daoInt = new DAOInterface();

ArrayList<Container> categories = new ArrayList();
categories.add(list.get(0));
for (Container c:list)
{
    if(daoInt.checkString(c.getContainerName(), categories))
        categories.add(c);
}
%>
        
        <table style="width:100%">
            
            <% for(Container c: categories)  {%>
  <tr>
      <td><a href="HierarchicalServlet?p1=<%=c.getContainerName()%>"><img src="<%=c.getPathToIcon()%>.png"></a></td>
    
  </tr>
  <% } %>
</table>
    </body>
</html>