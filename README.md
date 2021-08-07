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
[Login Page Working Video](https://drive.google.com/file/d/17BN2U7M_heY2Xy87ZVNvY4K7nYQCF9BQ/view?usp=sharing)
[Changing I.D. Working Video](https://drive.google.com/file/d/1PhljgwW0H_xMSib-FZXLzr8lYTv7HY-2/view?usp=sharing)
[Forgot Password Working Video](https://drive.google.com/file/d/1qalZMev6BYZ4PGUe5WJfymOH3p-amBna/view?usp=sharing)
[Changing Security Question Working Video](https://drive.google.com/file/d/1i6E5XTVyRVW0o0WTVFTnEjKbSSe4j-N3/view?usp=sharing)

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
  
- The user can change the I.D., but they need to provide the old I.D. and the current Password to change 
the I.D. . Applied validation feature if there is any empty input field.

- The user can also change the selected Security Questions and it’s inputted answer. To change it, the 
user needs to provide the current I.D. and Password. Applied validation feature if there is any empty 
input field.

- At first we need to add the default Login data to the “account” table in phpMyAdmin. For Security 
Questions refer to previous mentioned Strings. Be careful while copying the Security Questions Strings, 
they are case sensitive. Note: It is best to create only one single row for Login data in “account” table.

___

### Add Employee

[Video](https://drive.google.com/file/d/1wcthC6-83OlJcki1m2x0IBkt-VOFqcdF/view?usp=sharing)

- Employees added get recorded to the “employeerec” table in the database.

- The user can attach JPG and PNG images which will be recorded as BLOB datatype in 
database. To achieve this I used the FileChooser class of JavaFX to choose/select the 
image file and File class of JAVA to store that image file. Then used the FileInputStream 
class of JAVA to read the file, and setBinaryStream() method to store that 
FileInputStream information in binary information or BLOB in the database.

- The user can view the image before sending the data to the database.

- Applied the validation for :- [Validation Working Video](https://drive.google.com/file/d/18_Ebv6oqCCgLfXgHCAAMlzEWH1mm8FCB/view?usp=sharing)
  - There should not be any empty fields
  - There should not be the any employees having same I.D. or Aadhaar number in 
the database. Every employees should have unique I.D. and Aadhaar number
