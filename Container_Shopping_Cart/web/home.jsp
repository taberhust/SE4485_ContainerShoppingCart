<%-- 
    Document   : home
    Created on : Apr 9, 2016, 8:23:01 PM
    Author     : Charles
--%>

<%@page import="java.sql.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="css/style.css"/>
<!DOCTYPE html>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Welcome <%=session.getAttribute("name")%></title>  
</head>  
<body>  
    
    <div class= "Section_one_top">
            <div class= "div1_head_blue">
         <p class="ntt_logo"> <div class="ntt_logo_box"> <b>NTT DATA <b></div><br> </p>
                     <p class="name_logout"> <a href="cart.jsp"><button>Cart</button></a> You are logged in as <%=session.getAttribute("name")%> | Logout </p>
	</div>
	<div class="div2">
	</div>
    <h1> NTT DATA </h1>
    <form action="gridview.jsp" method="post">
       Container ID:
        <input type="text" name="name"  />
        <input type="submit" value="search" /></form>
    
    <table border="1" style="background-color:#ddd;border-collapse:collapse;border:3px solid #E5E4E2;color:#000000;width:100%" cellpadding="3" cellspacing="3">
	<thead>
		<tr>
                    
                        <th scope="col"><a href="GridviewServlet?p1=app">Application Containers</a></th>
        
			<th scope="col"><a href="GridviewServlet?p1=os">OS Containers</a></th>
                        
			<th scope="col"><a href="GridviewServlet?p1=dispAll">Display All Containers</a></th>
		</tr>
        </thead>
</table>
   
</body>  
</html>  
