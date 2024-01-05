/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package BL;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import javax.ejb.Stateless;

/**
 *
 * @author Rico
 */

import java.io.Serializable;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rico
 */
@Named(value = "userBB")
@ManagedBean
@SessionScoped
public class UserBB implements UserBBLocal, Serializable  {

    private String user_name;
    private String password;
    private String message;
    private String messageLogin;

    public String getMessageLogin() {
        return messageLogin;
    }

    public void setMessageLogin(String messageLogin) {
        this.messageLogin = messageLogin;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    /*
    public String insertUser(){
        
        User user = new User(this.user_name, this.password);
        
        if(dl.BuzzletDB.InsertUser(user)){
            
            this.message = "User created";
           
            // Access the HttpSession through FacesContext
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (facesContext != null) {
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

            // Add some data to the session
            session.setAttribute("logged_in_user", this.user_name);
            
                    // Check if the attribute is set
            String loggedInUser = (String) session.getAttribute("logged_in_user");

            if (loggedInUser == null) {
             this.message = "Error creating session.";
            }
          
        }
        
        else {
            this.message = "faces context is null";
        }

            return message;
            
        }
        
        else {
            
            this.message = "Error while creating user";
            return message;
            
        }
    }*/
    
    //The following method is from chatGPT
    
    public String submitForm() {
        
        User user = new User(this.user_name, this.password);
        
        if(dl.BuzzletDB.InsertUser(user)){
            
        this.message = "User created";
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        // Check for validation errors
        if (facesContext.isValidationFailed()) {
            return "facesContext error";
        }

        Map<String, Object> sessionMap = externalContext.getSessionMap();

        // Add some data to the session
        sessionMap.put("logged_in_user", this.user_name);

        // Redirect to Dashboard.xhtml
        try {
            
            externalContext.redirect("Dashboard.xhtml");
            
        } catch (IOException e) {
            // Handle the exception if redirect fails
            e.printStackTrace();
        }
                
        }
        
        
        else {
            
            this.message = "Error while creating user";
            return message;
            
        }
        
        return message;
    }
    
    public String submitFormLogin() {
        
        User user = new User(this.user_name, this.password);
        
        if(dl.BuzzletDB.Login(user)){
            
        this.messageLogin = "User logged in succesfully";
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        // Check for validation errors
        if (facesContext.isValidationFailed()) {
            return "facesContext error";
        }

        Map<String, Object> sessionMap = externalContext.getSessionMap();

        // Add some data to the session
        sessionMap.put("logged_in_user", this.user_name);

        // Redirect to Dashboard.xhtml
        try {
            
            externalContext.redirect("Dashboard.xhtml");
            
        } catch (IOException e) {
            // Handle the exception if redirect fails
            e.printStackTrace();
        }
                
        }
        
        
        else {
            
            this.messageLogin = "Error while login. Please try again";
            return messageLogin;
            
        }
        
        return messageLogin;
    }


   
}
