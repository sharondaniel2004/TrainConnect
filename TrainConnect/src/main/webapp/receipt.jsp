<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Booking Receipt</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background: linear-gradient(135deg, #3a86ff, #8338ec);
      font-family: 'Segoe UI', sans-serif;
      padding: 50px;
      color: #fff;
    }
    .receipt-container {
      background: #fff;
      color: #333;
      border-radius: 15px;
      padding: 30px;
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
      max-width: 900px;
      margin: auto;
      text-align: center;
    }
    h2 {
      color: #3a86ff;
      margin-bottom: 20px;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 25px;
    }
    th, td {
      padding: 12px;
      text-align: center;
      border: 1px solid #ccc;
    }
    th {
      background-color: #f3f3f3;
      color: #3a3a3a;
    }
    .btn-print {
      background: white;
      color: #3a86ff;
      border: 2px solid #3a86ff;
      border-radius: 50px;
      padding: 10px 30px;
      font-weight: 600;
      transition: 0.3s ease-in-out;
      box-shadow: 0 3px 10px rgba(0,0,0,0.1);
    }
    .btn-print:hover {
      background: #3a86ff;
      color: white;
    }
  </style>
</head>
<body>
  <div class="receipt-container">
    <%
      String email = request.getParameter("email");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TrainConnect", "root", "2004//sharon");
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM bookings WHERE email = ?");
      ps.setString(1, email);
      ResultSet rs = ps.executeQuery();
    %>

    <h2>Booking Receipt for <%= email %></h2>
    <table>
      <tr>
        <th>Train</th>
        <th>Passenger</th>
        <th>Age</th>
        <th>Gender</th>
        <th>From</th>
        <th>To</th>
        <th>Date</th>
        <th>Coach</th>
      </tr>
      <%
        while(rs.next()) {
      %>
      <tr>
        <td><%= rs.getString("train_name") %></td>
        <td><%= rs.getString("passenger_name") %></td>
        <td><%= rs.getInt("passenger_age") %></td>
        <td><%= rs.getString("passenger_gender") %></td>
        <td><%= rs.getString("from_station") %></td>
        <td><%= rs.getString("to_station") %></td>
        <td><%= rs.getDate("travel_date") %></td>
        <td><%= rs.getString("coach_type") %></td>
      </tr>
      <%
        }
      %>
    </table>

    <button class="btn-print" onclick="window.print()">Download / Print Ticket</button>
  </div>
</body>
</html>
