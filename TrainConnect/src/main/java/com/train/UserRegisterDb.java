package com.train;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

@SuppressWarnings("serial")

public class UserRegisterDb extends HttpServlet {

    String url = "jdbc:mysql://localhost:3306/TrainConnect";
    String dbUser = "root";
    String dbPass = "2004//sharon";

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email"); 
        String password = req.getParameter("password");
        String confirmpassword = req.getParameter("confirmPassword");
        

   
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, dbUser, dbPass);

            String insertSql = "INSERT INTO users_login (username, fullname, email, user_password) VALUES (?,?,?,?)";
            PreparedStatement insertStmt = con.prepareStatement(insertSql);
            insertStmt.setString(1, username);
            insertStmt.setString(2, fullname);
            insertStmt.setString(3, email);
            if(confirmpassword.equals(password)) {
            	insertStmt.setString(4, password);
            }else {
            	res.setContentType("text/html");
                PrintWriter out = res.getWriter();
                out.println("<!DOCTYPE html>");
            	out.println("<html lang='en'>");
            	out.println("<head>");
            	out.println("<meta charset='UTF-8'>");
            	out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            	out.println("<link href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css' rel='stylesheet'>");
            	out.println("<title>Login Failed</title>");
            	out.println("<style>");
            	out.println("body {");
            	out.println("  background: linear-gradient(45deg, #3a86ff, #8338ec);");
            	out.println("  height: 100vh;");
            	out.println("  display: flex;");
            	out.println("  align-items: center;");
            	out.println("  justify-content: center;");
            	out.println("  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
            	out.println("}");
            	out.println(".message-box {");
            	out.println("  background: white;");
            	out.println("  padding: 2rem;");
            	out.println("  border-radius: 15px;");
            	out.println("  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);");
            	out.println("  text-align: center;");
            	out.println("}");
            	out.println(".btn-custom {");
            	out.println("  background-color: #3a86ff;");
            	out.println("  color: white;");
            	out.println("  border-radius: 50px;");
            	out.println("  padding: 0.5rem 1.2rem;");
            	out.println("  text-decoration: none;");
            	out.println("  font-weight: 500;");
            	out.println("}");
            	out.println(".btn-custom:hover {");
            	out.println("  background-color: transparent;");
            	out.println("  border: 2px solid #3a86ff;");
            	out.println("  color: #3a86ff;");
            	out.println("}");
            	out.println("</style>");
            	out.println("</head>");
            	out.println("<body>");
            	out.println("<div class='message-box'>");
            	out.println("<h3 style='color: red;'>Registration Failed. password is not matching.</h3>");
            	out.println("<a href='UserRegister.html' class='btn btn-custom'>Register</a>");
            	out.println("</div>");
            	out.println("</body>");
            	out.println("</html>");
            }
            
            insertStmt.executeUpdate();
            insertStmt.close();
            con.close();
            
      res.sendRedirect("Userpage1.html");
           

        } catch (Exception e) {
            e.printStackTrace();
        }

        
       
    }
}
