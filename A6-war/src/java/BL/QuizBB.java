/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package BL;

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
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rico
 */
@Named(value = "quizBB")
@RequestScoped
public class QuizBB implements QuizBBLocal, Serializable  {

    private String title;
    private String date_created;
    private String message;
    private String username;

    /*public ArrayList<Quiz> getAllQuizzes() {
        return allQuizzes;
    }*/

    public void setAllQuizzes(ArrayList<Quiz> allQuizzes) {
        this.allQuizzes = allQuizzes;
    }

    private ArrayList<Quiz> allQuizzes;
    
    public ArrayList<Quiz> getAllQuizzes() {
        if (allQuizzes == null) {
            refreshAllQuizzes();
        }
        return allQuizzes;
    }

    public void refreshAllQuizzes() {
        allQuizzes = dl.BuzzletDB.FetchQuizzes();
    }
    
    //Quizzes by ID
    
    private ArrayList<Quiz> allQuizzesById;
    
    public void setAllQuizzesById(ArrayList<Quiz> allQuizzesById) {
        this.allQuizzesById = allQuizzesById;
    }
    
    public ArrayList<Quiz> getAllQuizzesById() {
        if (allQuizzesById == null) {
            refreshAllQuizzesById();
        }
        return allQuizzesById;
    }

    public void refreshAllQuizzesById() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        Object sessionObject = externalContext.getSession(false);

        if (sessionObject != null && sessionObject instanceof jakarta.servlet.http.HttpSession) {
            jakarta.servlet.http.HttpSession session = (jakarta.servlet.http.HttpSession) sessionObject;

            // Continue with your logic using the HttpSession
            Object loggedInUserObj = session.getAttribute("logged_in_user");

            if (loggedInUserObj != null && loggedInUserObj instanceof String) {
                String loggedInUser = (String) loggedInUserObj;
        allQuizzesById = dl.BuzzletDB.FetchQuizzesById(dl.BuzzletDB.GetUserID(loggedInUser));
            }
    }
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
        //This method has code from chatGPT
     public String insertQuiz() {
        this.message = "session is null";

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        Object sessionObject = externalContext.getSession(false);

        if (sessionObject != null && sessionObject instanceof jakarta.servlet.http.HttpSession) {
            jakarta.servlet.http.HttpSession session = (jakarta.servlet.http.HttpSession) sessionObject;

            // Continue with your logic using the HttpSession
            Object loggedInUserObj = session.getAttribute("logged_in_user");

            if (loggedInUserObj != null && loggedInUserObj instanceof String) {
                String loggedInUser = (String) loggedInUserObj;
                int uid = dl.BuzzletDB.GetUserID(loggedInUser);
                Quiz quiz = new Quiz(this.title, uid);

                if (dl.BuzzletDB.InsertQuiz(quiz)) {
                    refreshAllQuizzesById();
                    this.message = "Quiz added successfully";
                } else {
                    
                    this.message = "Error creating quiz, and logged in user is " + uid;
                }

                // ... (rest of your logic)
            } else {
                // Handle the case where the 'logged_in_user' attribute is not a String or is null
                this.message = "Error: 'logged_in_user' attribute is not a valid String";
            }
        } else {
            // Handle the case where the session is null or not of type HttpSession
            this.message = "Error: Session is null or not of type HttpSession";
        }

        return message;
    }
    
}

    
   

