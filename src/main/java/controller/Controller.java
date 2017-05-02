/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import model.User;
import model.DAOHappyDayLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;

/**
 *
 * @author Administrator
 */
public class Controller extends HttpServlet{
    private final String filepath = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\happydayapp4\\src\\main\\webapp\\walterd04";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("In Controller:");
        String url ="/index.html";
        HttpSession userSession;
        String action = request.getParameter("userAction");
        System.out.println("userAction=" + action);
        if(action == null){
            action = "index";
        }
        
        if (action.equalsIgnoreCase("index")){
            System.out.println("Controller:home");
            url = "/index.html";
        } else if(action.equalsIgnoreCase("signup")){
            System.out.println("controller:newUser");
            url = "/signup.html";
            String email = request.getParameter("email");
            String first = request.getParameter("first");
            String last = request.getParameter("last");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            User newUser = new User(email, first, last, username, password);
            System.out.println("Controller:user:newUser=" + newUser);
            
            if(email == null || username == null || email.isEmpty() || username.isEmpty()){
                url = "/signuperror.html";
                System.out.println("Controller:sign up error");
            } else {
                DAOHappyDayLog.newUser(newUser);
                DAOHappyDayLog.addUserTable(newUser);
                url = "/login.html";
                request.setAttribute("newUser", newUser);
                System.out.println("Controller: adding new user");
                File newDirectory = new File("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\happydayapp4\\src\\main\\webapp\\"
                        + request.getParameter("username"));
                newDirectory.mkdir();
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if(action.equalsIgnoreCase("login")){
            PrintWriter out = response.getWriter();
            System.out.println("controller:Login");
            
                User user = new User();
                
                user.setUser(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));
                
                System.out.println("Username: " + user.getUser() + "\nPassword: " + user.getPassword());
                if(DAOHappyDayLog.userLogin(request.getParameter("username"), request.getParameter("password"))){
                    User userOne = new User();
                    userOne.setUser(String.valueOf(request.getParameter("username")));
                    userOne.getUser();
                    
                    userSession = request.getSession();
                    userSession.setAttribute("user", userOne.getUser());
                    
                    url = "/homepage.jsp";
                    System.out.println("controller:Login Successful");
                    
                    if (action.equalsIgnoreCase("logout")){
                        userSession = request.getSession(false);
                        if(userSession != null){
                            userSession.invalidate();
                            url = "/index.html";
                        }
                    }
                } else {
                    url = "/loginfailure.jsp";
                    System.out.println("controller:Login Failed");
                } 
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
                
        } else if (action.equalsIgnoreCase("upload")){
            String username = request.getParameter("username");
            String caption = request.getParameter("caption");
            System.out.println(username);
            
            InputStream inputStream = null;
            
            Part filePart = request.getPart("file");
            if (filePart != null){
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());
                inputStream = filePart.getInputStream();
            }
            DAOHappyDayLog.addPicture(inputStream, username, caption);
            url = "/homepage.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
            
        } else if (action.equalsIgnoreCase("logout")){
        System.out.println("controller:Logout");
        HttpSession sessionUser = request.getSession(false);
        if(sessionUser != null){
            sessionUser.invalidate();
        }
        if(sessionUser == null){
            System.out.println("controller:Logout:user logged out");
        }
        url = "/index.html";
           
        
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
