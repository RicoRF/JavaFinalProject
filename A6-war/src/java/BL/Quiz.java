/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package BL;

import jakarta.ejb.Stateless;

/**
 *
 * @author Rico
 */
@Stateless
public class Quiz implements QuizLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private String title;
    private int user_id;
    private String date_created;
    private int quiz_id;
    private String username;

    public Quiz(String title, int user_id) {
        this.title = title;
        this.user_id = user_id;
    }
    
    public Quiz(String title, String username) {
        this.title = title;
        this.username = username;
    }

    public Quiz(String title, int user_id, String date_created, int quiz_id) {
        this.title = title;
        this.user_id = user_id;
        this.date_created = date_created;
        this.quiz_id = quiz_id;
    }
    
    public Quiz(){
        
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }
    
    
}
