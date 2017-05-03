/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DBConnection {
    
    public static Connection getConnection(){
        Connection connection = null; 
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://34.206.148.46:3306/happyday?useSSL=false", "jdoe", "mucis");
            System.out.println("Connected to DB at " + LocalDateTime.now());
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch(ClassNotFoundException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    
    public static void main(String[] args){
        getConnection();
    }
}
