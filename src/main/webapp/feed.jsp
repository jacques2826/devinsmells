<%-- 
    Document   : feed
    Created on : Apr 11, 2017, 4:52:34 PM
    Author     : Administrator
--%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="model.Picture"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Happy Day Feed</title>
    </head>
    <body>
        <h1><%out.println(request.getParameter("username") + "'s Feed");%></h1>
        
    </body>
</html>
