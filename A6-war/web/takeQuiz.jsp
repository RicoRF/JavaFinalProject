<%-- 
    Document   : takeQuiz
    Created on : Dec 12, 2023, 10:34:23 a.m.
    Author     : Rico
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="BL.Question"%>
<%@page import="BL.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous"></link>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="styles/styles.css" rel="stylesheet" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz</title>
    </head>
    <body>
        <div class="row">
            <div class="col-md-12">
                <%


        
if(request.getParameter("quizId") != null){

Quiz current = dl.BuzzletDB.FetchQuizzesByQuizId(Integer.parseInt(request.getParameter("quizId")));

%>

<h1><%= current.getTitle() %></h1>

<%
ArrayList<Question> currentQuestions;
// Get questions and store them in an array

if(session.getAttribute(current.getTitle()) == null){

currentQuestions = dl.BuzzletDB.FetchQuestions(Integer.parseInt(request.getParameter("quizId")));

    }
    
    else {
    //Clear session
    /*session.removeAttribute(current.getTitle());
    session.removeAttribute(current.getTitle() + "qn");*/
    currentQuestions = (ArrayList<Question>) session.getAttribute(current.getTitle());
    
    }
    
if(currentQuestions == null){
%>
There are no questions to show.
<%
    
    }

else {

// Count number of questions

int nQuestions = currentQuestions.size();

// Store questions in a session variable

session.setAttribute(current.getTitle(), currentQuestions);

if (session.getAttribute(current.getTitle() + "qn") == null) {

            // If not set, initialize it to 0
            session.setAttribute(current.getTitle() + "qn", 0);
            //Show question 1 of index 0
            
            %>
                                    
            <%
            
        }

        int activeQuestion = Integer.parseInt(session.getAttribute(current.getTitle() + "qn").toString());
%>
<!--Active question is <%= activeQuestion %>-->
<%
        if(request.getParameter("btnSubmit") != null){

            String answer = request.getParameter("answer");
            int questionId = Integer.parseInt(request.getParameter("questionId"));

            currentQuestions.get(activeQuestion).setStudent_answer(answer);
            
            %>
            
            <%
            
            session.setAttribute(current.getTitle(), currentQuestions);
            
            activeQuestion++;
            session.setAttribute(current.getTitle() + "qn", activeQuestion);

}

if(activeQuestion == nQuestions){
if(!currentQuestions.isEmpty()){
//Following code is from chatGPT
int correctAnswersCount = 0;
%>

<h3>Results</h3>
    <ul>

<%
for (Question question : currentQuestions) {
    String studentAnswer = question.getStudent_answer();
    String correctAnswer = question.getAnswer();
    boolean isCorrect = studentAnswer != null && studentAnswer.toLowerCase().equals(correctAnswer.toLowerCase());

    %>
    <li>
        <strong>Question:</strong> <%= question.getQuestion() %><br>
        
        <%-- Display Student Answer with conditional styling --%>
        <strong>Answer:</strong>
        <span style="color: <%= isCorrect ? "green" : "red" %>;">
            <%= studentAnswer %>
        </span><br>

        <%-- Display Correct Answer if Student Answer is Incorrect --%>
        <% if (!isCorrect) { %>
            <strong>Correct Answer:</strong> <%= correctAnswer %><br>
        <% } %>

        <%-- Update correct answers count --%>
        <% if (isCorrect) { correctAnswersCount++; } %>
    </li>
    
<%
}
%>
</ul>
<%
int totalQuestions = currentQuestions.size();
int scorePercentage = (correctAnswersCount * 100) / totalQuestions;
%>

<p>
    <strong>Score:</strong> <%= scorePercentage %>%<br>
    <strong>Correct Answers:</strong> <%= correctAnswersCount %> / <%= totalQuestions %>
</p>

<a href="/">Go back</a>
<%
    
    //Clear session
    session.removeAttribute(current.getTitle());
    session.removeAttribute(current.getTitle() + "qn");
    
}
%>

<%
}
else {
%>

             Question <%= activeQuestion + 1 %> of <%= nQuestions %>

        <form name="question" method="POST" action="takeQuiz.jsp?quizId=<%= request.getParameter("quizId") %>">
            
            <label for="answer"><%= currentQuestions.get(activeQuestion).getQuestion() %></label>
            <input type="text" name="answer" id="answer"><br>
            <input type="hidden" name="questionId" value="<%= currentQuestions.get(activeQuestion).getId() %>">
            <input type="submit" name="btnSubmit" id="btnSubmit">
            
        </form>   
        
        <% 
            }

            }
}
else {%>
There is no quiz ID to retrieve.
<% } 

%>
            </div>
        </div>
            </body>
</html>
