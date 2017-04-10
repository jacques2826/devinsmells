<%-- 
    Document   : loginsuccess
    Created on : Apr 6, 2017, 6:45:56 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>You are now logged in. Sorta.</h1>
        <%
            String username = request.getParameter("username");
            out.println("Thanks for logging in " + username);
        %>
    </body>
</html>
