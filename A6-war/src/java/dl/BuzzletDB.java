/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package dl;

import BL.Question;
import BL.Quiz;
import BL.User;
import jakarta.resource.cci.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rico
 */
public class BuzzletDB {
    
    private BuzzletDB() {
    }
    
    public static BuzzletDB getInstance() {
        return BuzzletDBHolder.INSTANCE;
    }
    
    private static class BuzzletDBHolder {

        private static final BuzzletDB INSTANCE = new BuzzletDB();
    }
    
    public static java.sql.Connection GetConnection(){
            
             java.sql.Connection conn = null;
        String user = "root";
        String password = "";
        String dbURL = "jdbc:mysql://localhost:3306/buzzletdb";
        try {
            Class.forName("com.mysql.jdbc.Driver");//very important line of code!
            conn = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException ex) {
            
        }
        catch (ClassNotFoundException cnfe) {

        }
        return conn;
            
        }
    
    public static boolean InsertUser(User user){
            
            String sql = "INSERT INTO user (user_name, password)"
                + "VALUES (?, ?)";
        
        PreparedStatement ps;
        try {
            
            java.sql.Connection conn = GetConnection();
            if(conn == null){
                return false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUser_name());
            ps.setString(2, user.getPassword());
            
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(sql);
            System.out.println(ex);
        }
        return false;
            
        }
    
    
    
    public static boolean Login(User user){        
        
            String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";
        
        PreparedStatement ps;
        
        try {
            
            java.sql.Connection conn = GetConnection();
            if(conn == null){
                return false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUser_name());
            ps.setString(2, user.getPassword());
            
            java.sql.ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            // At least one row exists, indicating a successful login
            return true;
        }
            
        } catch (SQLException ex) {
            System.out.println(sql);
            System.out.println(ex);
        }
        return false;
            
        
        }
    
    
    
    
    
    
    public static Integer GetUserID(String username){
        
        String sql = "SELECT * FROM user WHERE user_name = ?";
        
        PreparedStatement ps;
        try {
            
            java.sql.Connection conn = GetConnection();
            if(conn == null){
                return -1;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            
            //int rowsAffected = ps.executeQuery();
            
            java.sql.ResultSet rs = ps.executeQuery();
            
                    
                while(rs.next()){

                                return rs.getInt("user_id");
                                
                    
            }
                    
                    //else { return -2; }
            
        } catch (SQLException ex) {
            System.out.println(sql);
            System.out.println(ex);
        }
        return -3;
        
    }
    
    
    
    
    public static boolean InsertQuiz(Quiz quiz){
            
            String sql = "INSERT INTO quiz (title, user_id)"
                + "VALUES (?, ?)";
        
        PreparedStatement ps;
        try {
            
            java.sql.Connection conn = GetConnection();
            if(conn == null){
                return false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, quiz.getTitle());
            ps.setInt(2, quiz.getUser_id());
            
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(sql);
            System.out.println(ex);
        }
        return false;
            
        }
    
    
    
    
    
    
    public static ArrayList<Quiz> FetchQuizzes(){
                        
                String sql = "SELECT * FROM quiz";
                
                ArrayList<Quiz> result = new ArrayList<>();
                
                try
                {
                        java.sql.Connection conn = GetConnection();
                        if(conn == null) { System.out.println("Conn is null"); }
                        PreparedStatement st = conn.prepareStatement(sql);
                        
                   
                        java.sql.ResultSet rs = st.executeQuery();
                        
                        while(rs.next()){

                                Quiz q = new Quiz(rs.getString("title"), rs.getInt("user_id"), rs.getString("date_created"), rs.getInt("quiz_id"));
                                result.add(q);
                                
                    }
                        
                    }
                    catch (SQLException e){
                    }
                    
                    return result;            
        }
    
    public static Quiz FetchQuizzesByQuizId(int quizId){
                        
                String sql = "SELECT * FROM quiz WHERE quiz_id = ?";
                
                Quiz result = null;
                
                try
                {
                        java.sql.Connection conn = GetConnection();
                        if(conn == null) { System.out.println("Conn is null"); }
                        PreparedStatement st = conn.prepareStatement(sql);
                        st.setInt(1, quizId);
                   
                        java.sql.ResultSet rs = st.executeQuery();
                        
                        while(rs.next()){

                                Quiz q = new Quiz(rs.getString("title"), rs.getInt("user_id"), rs.getString("date_created"), rs.getInt("quiz_id"));
                                result = q;
                                
                    }
                        
                    }
                    catch (SQLException e){
                    }
                    
                    return result;            
        }
    
    public static ArrayList<Quiz> FetchQuizzesById(int loggedInUserID){
                        
                String sql = "SELECT * FROM quiz WHERE user_id = ?";
                
                ArrayList<Quiz> result = new ArrayList<>();
                
                try
                {
                        java.sql.Connection conn = GetConnection();
                        if(conn == null) { System.out.println("Conn is null"); }
                        PreparedStatement st = conn.prepareStatement(sql);
                        st.setInt(1, loggedInUserID);
                   
                        java.sql.ResultSet rs = st.executeQuery();
                        
                        while(rs.next()){

                                Quiz q = new Quiz(rs.getString("title"), rs.getInt("user_id"), rs.getString("date_created"), rs.getInt("quiz_id"));
                                result.add(q);
                                
                    }
                        
                    }
                    catch (SQLException e){
                    }
                    
                    return result;            
        }
    
    public static ArrayList<Question> FetchQuestions(int quizId){
                        
                String sql = "SELECT * FROM question WHERE quiz_id = ?";
                
                ArrayList<Question> result = new ArrayList<>();
                
                try
                {
                        java.sql.Connection conn = GetConnection();
                        if(conn == null) { System.out.println("Conn is null"); }
                        PreparedStatement st = conn.prepareStatement(sql);
                        st.setInt(1, quizId);
                        java.sql.ResultSet rs = st.executeQuery();
                        
                        while(rs.next()){

                                Question qn = new Question(rs.getInt("question_id"), rs.getString("question"), rs.getString("answer"), rs.getInt("quiz_id"), rs.getString("date_created"));
                                result.add(qn);
                                
                    }
                        
                    }
                    catch (SQLException e){
                    }
                    
                    return result;            
        }
    
    public static Question FetchQuestion(Integer id){
                        
                String sql = "SELECT * FROM question WHERE question_id = ?";
                
                try
                {
                        java.sql.Connection conn = GetConnection();
                        if(conn == null) { System.out.println("Conn is null"); }
                        PreparedStatement st = conn.prepareStatement(sql);
                        st.setInt(1, id);
                        java.sql.ResultSet rs = st.executeQuery();
                        
                        while(rs.next()){

                                Question qn = new Question(rs.getInt("question_id"), rs.getString("question"), rs.getString("answer"), rs.getInt("quiz_id"), rs.getString("date_created"));
                                return qn;
                                
                    }
                        
                    }
                    catch (SQLException e){
                    }
                
                    Question qn = new Question("", "", -1);
                
                    return qn;            
        }
    
    
    
    
    
    
    
    
    public static boolean InsertQuestion(Question question){
            
            String sql = "INSERT INTO question (question, answer, quiz_id)"
                + "VALUES (?, ?, ?)";
        
        PreparedStatement ps;
        try {
            
            java.sql.Connection conn = GetConnection();
            if(conn == null) { return false; }
            ps = conn.prepareStatement(sql);
            ps.setString(1, question.getQuestion());
            ps.setString(2, question.getAnswer());
            ps.setInt(3, question.getQuiz_id());
            
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(sql);
            System.out.println(ex);
        }
        return false;
            
        }
    
}
