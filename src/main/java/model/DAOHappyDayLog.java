/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.InputStream;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DAOHappyDayLog {
    final static boolean DEBUG = true;
    
    public static void newUser(User user){
        final String QUERY = "insert into usertable (email, first, last, username, password) VALUES (?, ?, ?, ? , ?)";
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY);){
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirst());
            statement.setString(3, user.getLast());
            statement.setString(4, user.getUser());
            statement.setString(5, user.getPassword());
            
            if(DEBUG){
                System.out.println(statement.toString());
            }
            statement.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(DAOHappyDayLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public static boolean userLogin(String username, String password){
        boolean user = false;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from usertable where username = ? and password = ?");
            
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            user = rs.next();
        }catch(SQLException ex){
            System.out.println("SQLException in userLogin: " + ex.toString());
        }
        return user;
    }
    
        public static void addUserTable(User user){
        System.out.println("Adding new User Table");
        final String QUERY = "CREATE TABLE " + user.getUser() + "("
                + "id INT(64) NOT NULL AUTO_INCREMENT, "
                + "img BLOB NOT NULL, "
                + "caption VARCHAR(140), "
                + "PRIMARY KEY(id));";
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY);){
            statement.executeUpdate();
        } catch(SQLException ex){
            System.out.println("Error in addUserTable(): " + ex.toString());
        }
    }
        
        public static void addPicture(InputStream inputStream, String username, String caption){
            System.out.println("Adding photo");
            final String QUERY = "insert into " + username + " (id, img, caption) values (null, ?, ?)";
            try(
                    Connection connection = DBConnection.getConnection();
                    PreparedStatement statement = connection.prepareStatement(QUERY);){
                if(inputStream != null){
                    statement.setBlob(2, inputStream);
                }
                statement.setString(3, caption);
                statement.executeUpdate();
            } catch(SQLException ex){
                System.out.println("Error in addPicture(): " + ex.toString());
            }
        }
}
