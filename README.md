# Java Final Project

## Project Overview

This repository contains the final project for the Java course, showcasing the application of Java EE (Jakarta EE) technologies to build a web-based system. The project demonstrates the integration of enterprise Java concepts with a focus on user interactions, business logic, and data persistence.

---

## Features

1. **Quiz Management System**:
   - Users can create, manage, and take quizzes.
   - Includes functionality for adding questions and viewing quiz results.

2. **User Roles**:
   - Admins can manage quiz content.
   - Students can participate in quizzes and track their progress.

3. **Web-Based Interface**:
   - Utilizes JSP and JSF for frontend development.
   - Styled with CSS for a clean and modern interface.

4. **Backend Logic**:
   - EJBs (Enterprise Java Beans) handle business logic.
   - Data persistence managed using a database connection.

5. **Database Interaction**:
   - Supports operations like creating, reading, updating, and deleting quiz and user data.

---

## Technical Details

- **Programming Language**: Java
- **Framework**: Java EE (Jakarta EE)
- **Frontend**: JSP, JSF, XHTML, CSS
- **Backend**: EJBs, Managed Beans
- **Database**: JDBC for database connectivity
- **Project Structure**:
  - **A6-ejb**: Contains the Enterprise Java Beans (EJB) for business logic.
  - **A6-war**: Web application resources, including JSP pages, JSF views, and web configuration files.
  - **Database**: Integration through a database access layer for managing user and quiz data.

---

## How to Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/RicoRF/JavaFinalProject.git
   ```

2. **Open the project in NetBeans**:
   - Navigate to the project folder and open it in NetBeans.

3. **Set up the database**:
   - Configure the database connection in the `BuzzletDB.java` file.
   - Ensure the database schema is properly initialized.

4. **Deploy the application**:
   - Use a Java EE-compatible server like GlassFish to deploy the application.

5. **Access the application**:
   - Open the web application in your browser by navigating to the server's URL.

---

## Repository Structure

- **A6-ejb**:
  - Contains Java classes implementing the business logic (e.g., `Question.java`, `Quiz.java`, `User.java`).
- **A6-war**:
  - `web/`: Contains web pages (e.g., `index.xhtml`, `Dashboard.xhtml`, `addQuestion.jsp`, `takeQuiz.jsp`).
  - `web/WEB-INF/`: Configuration files for JSF, web descriptors, and deployment settings.
  - `web/styles/`: Stylesheets for the frontend.
- **Database**:
  - Managed by the `dl.BuzzletDB` class for database interactions.
- **Build and Configuration Files**:
  - Includes `build.xml` and other NetBeans-generated configuration files.

---

## Lessons Learned

1. **Enterprise Java Concepts**:
   - Gained practical experience with EJBs, JSF, and JDBC.

2. **Web Application Development**:
   - Integrated backend and frontend components for a functional application.

3. **Database Integration**:
   - Applied database access techniques for managing dynamic data.

4. **Project Deployment**:
   - Learned to deploy Java EE applications using GlassFish.

---

## Future Enhancements

- Add user authentication and role-based access control.
- Implement advanced quiz analytics and reporting.
- Enhance the UI with modern JavaScript frameworks.
- Optimize database queries for better performance.

---

This project showcases a comprehensive understanding of Java EE development and serves as a strong foundation for building scalable enterprise applications.
