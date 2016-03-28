<%-- 
    Document   : response
    Created on : Mar 24, 2016, 4:53:49 PM
    Author     : steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Containers searched</title>
 
</head>
<body>
    <jsp:useBean id="mybean" scope="session" class="org.mypackage.hello.Testing" /> 
    <jsp:setProperty name="mybean" property="name" />
    <h1>Searching for PLACEHOLDER,<jsp:getProperty name="mybean" property="name" /> !</h1>
</body>
</html>
