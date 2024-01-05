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
public class Question implements QuestionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private int id;
    private String question;
    private String answer;
    private int quiz_id;
    private String date_created;
    private String student_answer;

    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }

    public Question(int id, String question, String answer, int quiz_id, String date_created) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.quiz_id = quiz_id;
        this.date_created = date_created;
    }

    public Question(String question, String answer, int quiz_id) {
        this.question = question;
        this.answer = answer;
        this.quiz_id = quiz_id;
    }
    
    public Question(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
    
}
