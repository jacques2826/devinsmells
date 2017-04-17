<%-- 
    Document   : homepage
    Created on : Apr 11, 2017, 4:15:37 PM
    Author     : Administrator
--%>

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Happy Day</title>
    </head>
    <body>
        <h1>Hello <%out.println(request.getParameter("username"));%></h1>
        <h3>Upload Picture: </h3>
        Select a file to upload: <br>
        <form action ="upload" method="post" enctype="multipart/form-data">
            <input type="file" name="file" size="50"/> <br>
            <input type="hidden" name="userAction" value='upload'>
            <input type="submit" value="Upload"/>
        </form>
        <form name ="logout" method ="get" action ="logout">
            <input type='hidden' name='userAction' value ='logout'>
            <input type="submit" name ="logout" value="Logout">
        </form>
        <div id = "myPictures">
        <%
            File dir = new File("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\happydayapp4\\src\\main\\webapp\\" + request.getParameter("username"));
            File[] directoryListing = dir.listFiles();
            if(directoryListing != null){
                for(File file : directoryListing){
                    %>
                    <img onmouseover="preview.src=<%=file.getName()%>.src" 
                         name="<%=file.getName()%>" src="walterd04/<%=file.getName()%>" alt="" width = "250" height = "250"/>
                    <%
                }
            } else {
                out.println("No Pictures");
            }
        %>
        </div>
        <p><a href ="feed.jsp">My Feed!</a></p>
    </body>
</html>
