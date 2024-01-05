/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package BL;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.SessionScoped;
import javax.ejb.Stateless;
import jakarta.inject.Named;

/**
 *
 * @author Rico
 */
@Named(value = "user")
@Stateless
@Dependent
public class User implements UserLocal {

    private int id;
    private String user_name;
    private String password;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User(int id, String user_name, String password) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
    }
    
    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }
    
    public User() {
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
