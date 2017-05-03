/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.NewUser;
import model.DAOHappyDayLog;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class Controller extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("In Controller:");
        String url ="/index.html";
        
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
            //url = "/signup.html";
            String email = request.getParameter("email");
            String first = request.getParameter("first");
            String last = request.getParameter("last");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            NewUser newUser = new NewUser(email, first, last, username, password);
            System.out.println("Controller:user:newUser=" + newUser);
            
            if(email == null || username == null || email.isEmpty() || username.isEmpty()){
                url = "/signuperror.html";
                System.out.println("Controller:sign up error");
            } else {
                DAOHappyDayLog.newUser(newUser);
                url = "/signupsuccess.jsp";
                request.setAttribute("newUser", newUser);
                System.out.println("Controller: adding new user");
            }
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
