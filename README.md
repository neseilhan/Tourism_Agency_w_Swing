Tourism Agency Management System

Features :
User Management: Adding, updating, deleting and searching users.
Hotel Management: Adding, updating, deleting and searching hotels.
Room Management: Add, update, delete and search rooms.
Reservation Management: Add, update, delete and search reservations.
Different User Roles: Separate administration panels for Admin and Worker roles.

Used technologies :
Java
Swing GUI
PostgreSQL

Clone the project:
git clone https://github.com/neseilhan/Tourism_Agency_w_Swing.git

Set up database:

Install and run PostgreSQL.
Update the database connection information in the src/core/Db.java file according to your own PostgreSQL settings.
Create your database using the turizm.sql file in the GitHub repository and return it with the following commands.
psql -U postgres -f tourismAgency.sql

Compile and Run the Project:

Open the project in a Java IDE (such as IntelliJ IDEA).
Add the necessary dependencies (such as PostgreSQL JDBC Driver).
Run the app.java file.
Layered Architecture
The project is developed using layered architecture and is divided into the following packages:

entity: Contains entity classes corresponding to database tables.
business: Contains business logic and service layer.
dao: Contains database access objects (Data Access Objects).
view: Contains user interface components.
core: Contains helper classes and general configurations.

Application Screens and Descriptions
