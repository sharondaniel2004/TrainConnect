package com.train;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class getInTouch
 */
@WebServlet("/getInTouch")
public class getInTouch extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private static final String DB_URL = "jdbc:mysql://localhost:3306/TrainConnect";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "2004//sharon";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "INSERT INTO messages (fullname, email, message) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, message);

            int rowsInserted = stmt.executeUpdate();
            stmt.close();
            conn.close();

            response.setContentType("text/html");
            if (rowsInserted > 0) {
            	PrintWriter out = response.getWriter();

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
            	out.println("<h2>Thank you, " + name + "! Your message has been saved.</h2>");
            	out.println("</div>");
            	out.println("</body>");
            	out.println("</html>");
                return;
            } else {
                response.getWriter().println("<h2>Oops! Something went wrong.</h2>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}