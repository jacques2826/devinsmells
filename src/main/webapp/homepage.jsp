<%-- 
    Document   : homepage
    Created on : Apr 11, 2017, 4:15:37 PM
    Author     : Administrator
--%>

<%@page import="java.io.InputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Happy Day</title>

    </head>
    <body>
        <%String username = request.getParameter("username");%>
        <h1>Hello <%out.println(username);%></h1>
        <h3> Choose File to Upload in Server </h3>
            <form name="upload" action="upload" method="post" enctype="multipart/form-data">
                <input type ="file" name ="file" value ='submit'>
                <input type ="text" name="caption" value="Caption: ">
                <input type ="hidden" name ="userAction" value="upload">
                <input type ="submit" name = "upload" value ="Submit">
            </form> 
        <form name ="logout" method ="get" action ="logout">
            <input type='hidden' name='userAction' value ='logout'>
            <input type="submit" name ="logout" value="Logout">
        </form>
        <div id = "myPictures">
        <%
            File dir = new File("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\happydayapp4\\src\\main\\webapp\\" + request.getParameter("username"));
            File[] directoryListing = dir.listFiles();
            //String textFiles = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\happydayapp4\\src\\main\\webapp\\" + request.getParameter("username") + "\\Comments";
            if(directoryListing != null){
                for(File file : directoryListing){
                    String filepath = username + "\\" + file.getName();
                    %>
                    <img onmouseover="preview.src=<%=file.getName()%>.src" 
                         name="<%=file.getName()%>" src="<%=filepath%>" alt="" width = "250" height = "250"/>
                    <%
                }
            }           
            
        %>
        </div>
    </body>
</html>
