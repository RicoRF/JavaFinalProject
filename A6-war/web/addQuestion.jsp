<%@page import="BL.Quiz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BL.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="java.io.IOException"%>

<%
    // Get the session
    //HttpSession session = request.getSession(false);

    // Check if the session is not null and 'logged_in_user' attribute is set
    if (session != null && session.getAttribute("logged_in_user") != null) {
        // User is logged in, continue rendering the page
%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous"></link>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
   
        <link href="styles/styles.css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    
        <%
        
if(request.getParameter("quizId") != null){

Quiz current = dl.BuzzletDB.FetchQuizzesByQuizId(Integer.parseInt(request.getParameter("quizId")));

%>
    
    <h1>Add Question for <%= current.getTitle() %></h1>
    <form name="addQuestion" method="POST" id="addQuestionForm">
        
        <label for="question">Question:</label>
        <input type="text" id="question" name="question"><br><br>
        <label for="answer">Answer:</label>
        <input type="text" id="answer" name="answer"><br><br>
        <input type="submit" name="btnSubmit" value="Add">
        
    </form>

    <%

        ArrayList<Question> questions = dl.BuzzletDB.FetchQuestions(Integer.parseInt(request.getParameter("quizId")));
        
        if(questions != null && request.getParameter("btnSubmit") == null){
        %>
            <ul id="questionList">
        <%-- Iterate through the ArrayList and display properties for each Question --%>
        <% for (Question question : questions) { %>
            <li>
                <strong>Question:</strong> <%= question.getQuestion() %><br>
                <strong>Answer:</strong> <%= question.getAnswer() %><br>                
                <!-- Add more properties as needed -->
            </li>
        <% } %>
    </ul><%
        
        }
        

        }
        

       

if(request.getParameter("btnSubmit") != null){

        Question question = new Question(request.getParameter("question"), request.getParameter("answer"), Integer.parseInt(request.getParameter("quizId")));
        if(dl.BuzzletDB.InsertQuestion(question)){
ArrayList<Question> questionss = dl.BuzzletDB.FetchQuestions(Integer.parseInt(request.getParameter("quizId")));
        
        %>
        
        <ul id="questionList">
        <%-- Iterate through the ArrayList and display properties for each Question --%>
        <% for (Question questionn : questionss) { %>
            <li>
                <strong>Question:</strong> <%= questionn.getQuestion() %><br>
                <strong>Answer:</strong> <%= questionn.getAnswer() %><br>                
                <!-- Add more properties as needed -->
            </li>
        <% } %>
        </ul>
        
        <script>
            <!-- following code is from chatGPT -->
        $(document).ready(function () {
            $("#addQuestionForm").submit(function (e) {
                e.preventDefault();
                $.ajax({
                    type: "POST",
                    url: "addQuestion.jsp",
                    data: $("#addQuestionForm").serialize(),
                    success: function () {                         
                    },
                    error: function () {
                        alert("Error adding the question");
                    }
                });
            });
        });
    </script>
        
        <%

}
else {

%>
Error adding the question
<%

}

        }
        

        %>
<a href="/Dashboard.xhtml">Go Back</a>
</body>
</html>
<%
    } else {
        %>
        You need to <a href="index.xhtml">login to see this page</a>.
        <%
    }
%>
