# Employee-Management-System

## A full Employee Management System Made with Javafx, JDBC, SceneBuilder 
[General Working Video](https://drive.google.com/file/d/1eYKUkd1Y6SVF2VJs_pY8e2HqofpPKZ-t/view?usp=sharing)
___

Used Xampp for Local host and SQL for Database.

Used SceneBuilder for UI Design.

Used JDBC Driver to connect to the Database

* Initial Database settings is provided, go through this text document to create the database of this project

* Jar file is provided . Go to "Application Jar File" folder to get the jar file . 

___

### Login Page 

- The user needs to input the correct “I.D. Number” and “Password” to get access to managing the 
Employees. There is another table created in this same database to compare or update the login data 
of the user

- Added “Forgot Password” feature if the user forgets the Password to sign in or if they want to change 
the current Password and set a new Password. The user needs to provide the current “I.D.”, choose the 
correct Security Questions, and type the correct Answer of that Security Question, as per the recorded 
data in database. Applied validation feature if there is any empty input field.

- The Answer that is typed by the user is not case sensitive, to achieve this, I converted both user 
inputted data and recorded data in database to lowercase String, and then compared them.

- The predefined Security Questions provided are fixed, which means the user cannot create their own 
Security Questions, but they can input their own custom Answers to it. The Security questions provided 
are as follows :- 
  - “What is your pet name ?”
  - “Where is your birth place ?”
  - “What is your secret hobby you haven't revealed yet ?”
  - “What is your favourite hobby ?”
  - “What is your childhood name ?”
  - “What is your elementary school name”
  - "What is your favourite cartoon ?"
  - "What is your favourite comic ?"
  - "Which is your favourite super hero ?"
  - "Any secret name or code ?"
  
-
