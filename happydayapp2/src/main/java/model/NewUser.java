package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class NewUser {
    private String email, first, last, user, password;
    
    public NewUser(){
        email = "";
        first = "";
        last = "";
        user = "";
        password = "";
    }
    
    public NewUser(String email, String first, String last, String user, String password){
        this.email = email;
        this.first = first;
        this.last = last;
        this.user = user;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", first=" + first + ", last=" + last + ", user=" + user + ", password=" + password + '}';
    }
    
    
}
