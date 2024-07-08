# Tourism Agency Management System

## Features

- **User Management**: Adding, updating, deleting, and searching users.
- **Hotel Management**: Adding, updating, deleting, and searching hotels.
- **Room Management**: Add, update, delete, and search rooms.
- **Reservation Management**: Add, update, delete, and search reservations.
- **Different User Roles**: Separate administration panels for Admin and Worker roles.

## Used Technologies

- **Java**
- **Swing GUI**
- **PostgreSQL**

## Clone the Project

```bash
git clone https://github.com/neseilhan/Tourism_Agency_w_Swing.git
```

## Set up database

Install and run PostgreSQL.
Update the database connection information in the src/core/Db.java file according to your own PostgreSQL settings.
Create your database using the turizm.sql file in the GitHub repository and return it with the following commands.
```bash
psql -U postgres -f turizm.sql
````

## Compile and Run the Project

Open the project in a Java IDE (such as IntelliJ IDEA).
Add the necessary dependencies (such as PostgreSQL JDBC Driver).
Run the app.java file.
Layered Architecture

## The project is developed using layered architecture and is divided into the following packages:

**entity**: Contains entity classes corresponding to database tables.
**business**: Contains business logic and service layer.
**dao**: Contains database access objects (Data Access Objects).
**view**: Contains user interface components.
**core**: Contains helper classes and general configurations.

## Application Screens and Descriptions

## 1.This screen is the login screen, the first admin registration is made by the developer.

![1](https://github.com/neseilhan/Tourism_Agency_w_Swing/assets/36484216/11847967-73c5-4533-9024-27634e369438)

## 2.On this screen, admin can search, add, delete and edit members in ADMIN and EMPLOYEE categories.

![2](https://github.com/neseilhan/Tourism_Agency_w_Swing/assets/36484216/2f6f9fc5-799b-42f4-8e59-9157e1fa23e3)

## 3.employee can add, delete and edit hotels. The hotel has characteristics such as hostel type and season of service.

![3](https://github.com/neseilhan/Tourism_Agency_w_Swing/assets/36484216/618825b0-8008-4ea8-b557-2cd2eff8d1d2)

## 4.The employee can add rooms and get the room features from the hotel. You can search the attached hotels according to criteria such as city, availability date and hotel name. Hotels without rooms will not be listed. Reservations are also made here.

![4](https://github.com/neseilhan/Tourism_Agency_w_Swing/assets/36484216/effc6afd-47ce-4a3d-be3d-e9444430d8a5)

## 5.On the hotel adding screen, features such as the hotel's name, city, address and phone number are added, as well as hostel types and the season in which it operates.

![5](https://github.com/neseilhan/Tourism_Agency_w_Swing/assets/36484216/92d226f2-41bb-4a8a-adb0-dcb37ac05a4a)

## 6.Hotel update screen.

![6](https://github.com/neseilhan/Tourism_Agency_w_Swing/assets/36484216/0f36a8b8-182a-4daa-a719-16a61f0ff216)

## 7.The room addition screen allows adding rooms according to the hotel's existing features. Adult and child fares must be entered separately and a specific stock must be specified.

![7](https://github.com/neseilhan/Tourism_Agency_w_Swing/assets/36484216/4939c9d4-156f-46e7-9b7d-5a49c9cd6f66)

## 8.Reservation editing screen with reservation features.

![8](https://github.com/neseilhan/Tourism_Agency_w_Swing/assets/36484216/b427f9fc-e668-424e-a103-c9b3f8e4679f)

## 9.The screen where reservations and customer information are listed.

![10](https://github.com/neseilhan/Tourism_Agency_w_Swing/assets/36484216/bbe32173-a8e9-4583-bb8f-17ba60e6d6a6)



