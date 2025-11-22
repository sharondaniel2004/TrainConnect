🚆 TrainConnect – Train Ticket Booking System

TrainConnect is a web-based train ticket booking system built using HTML, CSS, JavaScript (Frontend) and Java, JSP, Servlets, JDBC, MySQL (Backend).
Users can search trains, enter passenger information, choose coach type, book tickets, and receive a downloadable receipt. Booking details are also sent to the user through a common email.

⭐ Features

Train search by boarding & destination

Train selection with coach type (Economy / AC)

Add multiple passengers (Name, Gender, Age)

Common email for all passengers

Booking stored in MySQL database

Downloadable ticket receipt

Email confirmation via JavaMail API

Responsive Bootstrap UI

🧰 Tech Stack

Frontend: HTML, CSS, Bootstrap, JavaScript
Backend: Java, JSP, Servlets, JDBC
Database: MySQL
Email Service: JavaMail API
Server: Apache Tomcat

📁 Project Structure
TrainConnect/
 ├─ src/
 │   ├─ controller/       # Servlets (Login, Search, Booking)
 │   ├─ dao/              # Database operations
 │   ├─ model/            # POJO classes
 │   └─ util/             # Email utility
 │
 ├─ WebContent/
 │   ├─ index.html
 │   ├─ login.html
 │   ├─ available_trains.html
 │   ├─ passenger_details.html
 │   └─ receipt.jsp
 │
 └─ WEB-INF/
     └─ web.xml

🏗️ System Flow
Select Route ➜ Show Trains ➜ Select Train + Coach ➜
Enter Passenger Details ➜ Store in Database ➜
Generate Receipt ➜ Send Email Confirmation

🗄️ Database Schema (MySQL)

users(user_id, username, password)
trains(train_id, train_name, from_station, to_station, departure, arrival, price)
bookings(booking_id, train_id, email, total_price)
passengers(passenger_id, booking_id, name, gender, age)

⚙️ Setup Instructions
1️⃣ Clone the Repository
git clone https://github.com/yourusername/TrainConnect.git

2️⃣ Import into Eclipse

File → Import → Dynamic Web Project

Select Apache Tomcat server

3️⃣ Configure MySQL

Create DB:

CREATE DATABASE trainconnect;


Add tables as listed above.

4️⃣ Update DB Credentials

In DBConnection.java:

String URL = "jdbc:mysql://localhost:3306/trainconnect";
String USER = "root";
String PASS = "yourpassword";

5️⃣ Run Project

Start Tomcat → Visit:

http://localhost:8080/TrainConnect

📧 Email Module (JavaMail)

Sends full booking + passenger details to user’s entered email

Uses SMTP authentication
