<%-- 
    Document   : gridview
    Created on : Mar 27, 2016, 5:58:28 PM
    Author     : Charles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NTT DATA</title>
    </head>
    <body>
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
        
        <table style="width:100%">
  <tr>
      <td><img src="images/img1.jpg" /></td>
    <td><img src="images/img2.jpg" /></td>
    <td><img src="images/img3.jpg" /></td>
    <td><img src="images/img1.jpg" /></td>
    <td><img src="images/img2.jpg" /></td>
    
  </tr>
  <tr>
    <td><img src="images/img1.jpg" /></td>
    <td><img src="images/img2.jpg" /></td>
    <td><img src="images/img3.jpg" /></td>
    <td><img src="images/img1.jpg" /></td>
    <td><img src="images/img2.jpg" /></td>
  </tr>
</table>
    </body>
</html>
