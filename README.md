# Employee-Management-System

## A full Employee Management System Made with Java, Javafx, JDBC, SceneBuilder 
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

- The user needs to input the correct **I.D. Number** and **Password** to get access to managing the 
**Employees**. There is another table created in this same database to compare or update the **login data**
of the user

- Added **Forgot Password** feature if the user forgets the Password to sign in or if they want to change 
the **current** Password and set a **new** Password. The user needs to provide the **current** **I.D.**, choose the 
correct **Security Questions**, and type the correct **Answer** of that **Security Question**, as per the recorded 
data in database. Applied **validation** feature if there is any empty input field. [Forgot Password Working Video](https://drive.google.com/file/d/1qalZMev6BYZ4PGUe5WJfymOH3p-amBna/view?usp=sharing) 

- The Answer that is typed by the user is **not case sensitive**, to achieve this, I converted both user 
inputted data and recorded data in database to **lowercase** String, and then compared them.

- The predefined **Security Questions** provided are fixed, which means the user cannot create their own 
Security Questions, but they can input their own custom Answers to it. The **Security questions** provided 
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
  
- The user can change the **I.D.**, but they need to provide the **old I.D.** and the **current Password** to change 
the **I.D.** . Applied **validation** feature if there is any empty input field. [Changing I.D. Working Video](https://drive.google.com/file/d/1PhljgwW0H_xMSib-FZXLzr8lYTv7HY-2/view?usp=sharing)

- The user can also change the **selected Security Questions** and it’s inputted **answer**. To change it, the 
user needs to provide the **current I.D.** and **Password**. Applied **validation** feature if there is any empty 
input field. [Changing Security Question Working Video](https://drive.google.com/file/d/1i6E5XTVyRVW0o0WTVFTnEjKbSSe4j-N3/view?usp=sharing)

- At first we need to add the default **Login data** to the **“account”** table in **phpMyAdmin**. For **Security 
Questions** refer to previous mentioned Strings. Be careful while copying the Security Questions Strings, 
they are **case sensitive**. Note: It is best to create only one single row for Login data in “account” table.

___

### Add Employee

[Add Employee Video](https://drive.google.com/file/d/1wcthC6-83OlJcki1m2x0IBkt-VOFqcdF/view?usp=sharing)

- Employees added get recorded to the **“employeerec”** table in the database.

- The user can attach **JPG** and **PNG** images which will be recorded as **BLOB** datatype in 
database. To achieve this I used the `FileChooser` class of **JavaFX** to choose/select the 
image file and `File` class of **JAVA** to store that image file. Then used the `FileInputStream`
class of **JAVA** to read the file, and `setBinaryStream()` method to store that 
`FileInputStream` information in **binary** information or **BLOB** in the database.

- The user can view the image before sending the data to the database.

- Applied the validation for :- [Validation Working Video](https://drive.google.com/file/d/18_Ebv6oqCCgLfXgHCAAMlzEWH1mm8FCB/view?usp=sharing)
  - There should not be any empty fields.
  - There should not be the any employees having same **I.D.** or **Aadhaar number** in 
the database. Every employees should have unique **I.D.** and **Aadhaar number**.
___

### View or Edit Employee
[View or Edit Employee Working Video](https://drive.google.com/file/d/1S7D2YSDk1dHwC3Lvbbf9dpE1eNtlE8jY/view?usp=sharing)

- The user can view as well as edit the added record of the employee. The record of the 
employee edited will be updated to the **“employeerec”** table in database.

- The user need to provide the **I.D. Number** or **Aadhaar Number** of the employee to 
search and get their data from database.

- Added image viewer of the employee. Here the binary information of the image file that 
is stored in the database as **BLOB** is fetched with `getBinaryStream()` method. Then that 
fetched **binary** information is read by the `read()` method of `InputStream` class, and 
written to a new file “photo.jpg” by the `write()` method of `OutputStream` class. Finally 
the `ImageView` class of **JavaFx** is used to view that image file “photo.jpg”.

- The user can edit all the information of the employee inclcuding the photo of the 
employee, except the **I.D. Number**. Here the I.D. number is kept **static**.

- The **DatePicker** is not provided here, user has to manually set the date in 
**DD-MM-YYYY** format.

___

### Employee List
[Employee List Working Video](https://drive.google.com/file/d/1kHRHG8IPT2MfOWr7X2pHp3NHKrXReFn6/view?usp=sharing)

- All the list of employees are show in a **table** form.

- There are only few columns created for displaying the employee information. To see 
the full employee data, there is **view/edit** employee option, but first the user need to 
**select** a row by clicking on it.

- The table includes the column of :-
  - Id
  - Name
  - Department
  - Phone Number
  - Date Of Joining

- Added a **Search Box** to search employee from the table, by a specific keyword from the 
columns. To achieve this, I created object of `FilteredList` class from **JavaFx** and initially 
stored all the rows of employee from the table in it, which will also store all the 
employees if the **Search Box** is empty. Then I **linked** the search box (it’s text property) 
with that `FilteredList` object using `Listener` from **JavaFx** to store all the rows of the 
employee according to the **search text**; and also used the `setPredicate()` method to 
compare the search text through each column one by one from that `FilteredList` object. 
After comparing, a **new** list of **employee** is generated whose columns are **matching** with 
the search text, then that `FilteredList` object is **overriden** with that new generated list. 
Finally I **displayed** the list on the table stored in the `FilteredList` object, which is result of 
the search. This whole **process** of searching, overriding the list and displaying it on the 
table, happens **every time** the search box is **provoked**, as it is linked by **Listener** with the 
`FilteredList` object.

Remember if the search text does not matches with any of the column then an **empty** 
list will be stored, and if the search box empty then **all employees** list will be shown.

Here to make the search, **not case sensitive**, I first converted both the search text and 
each column text to lower case and then compared them during the comparing process. 
I also used `trim()` method of `String` in the search text to delete the **unwanted spaces**.


___

### Delete Employee
[Delete Employee Working Video](https://drive.google.com/file/d/1OkeFmwg-oYHxaetIst1yn0jHEgybD24K/view?usp=sharing)

- The user needs to provide **I.D. Number** of that **Employee** whose information is going to be deleted in the **"employeerec"** table.

- Before deleting, a window of **Employee** with full data is show so that the user can make sure that they are deleting correct employee.

- An confirmation pop-up is also shown while deleting.
 
