package com.train;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class BookTrainServlet
 */
@SuppressWarnings("serial")
@WebServlet("/bookTrain")
public class BookTrainServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String trainId = request.getParameter("trainId");
    String fromStation = request.getParameter("from");
    String toStation = request.getParameter("to");
    String travelDate = request.getParameter("travelDate");
    int count = Integer.parseInt(request.getParameter("count"));
    String coachType = request.getParameter("coachType");
    String email = request.getParameter("email");
    String trainName = request.getParameter("trainName");


    Connection conn = null;
    PreparedStatement ps = null;

    try {
     
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TrainConnect", "root", "2004//sharon");

      String sql = "INSERT INTO bookings (train_id, train_name, from_station, to_station, travel_date, coach_type, email, passenger_name, passenger_age, passenger_gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


      ps = conn.prepareStatement(sql);

     
      for (int i = 1; i <= count; i++) {
        String name = request.getParameter("passengerName" + i);
        int age = Integer.parseInt(request.getParameter("passengerAge" + i));
        String gender = request.getParameter("passengerGender" + i);

        ps.setString(1, trainId);
        ps.setString(2, trainName);
        ps.setString(3, fromStation);
        ps.setString(4, toStation);
        ps.setDate(5, java.sql.Date.valueOf(travelDate));
        ps.setString(6, coachType);
        ps.setString(7, email);
        ps.setString(8, name);
        ps.setInt(9, age);
        ps.setString(10, gender);


        ps.executeUpdate();
      }

     
      response.sendRedirect("receipt.jsp?email=" + URLEncoder.encode(email, "UTF-8"));

      return;

    } catch (Exception e) {
      e.printStackTrace();
      response.getWriter().println("❌ Error: " + e.getMessage());
    } finally {
      try {
        if (ps != null) ps.close();
        if (conn != null) conn.close();
      } catch (Exception ignored) {}
    }
  }
}