# Revature_Project_1_ExpenseReimbursementSystem

## Project Description
Expense Reimbursement System (ERS) - Java

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Technologies Used

* Java 11
* Tomcat v 9.0.62
* DBeaver
* AWS 
* Spring STS 3
* JavaScript
* HTML
* CSS
* Servlets
* JDBC

## Features

List of features ready and TODOs for future development
* User validation at login
* Dynamic home page based on login credentials
* Onload functionalities to retrieve reimbursement tables
* Filtering requests
* Request, Approve and Deny functionalities

To-do list:
* Additional safety measures to destroy session upon hitting "Go back" button


## Getting Started

> git clone https://github.com/mateoer/Revature_Project_1_ExpenseReimbursementSystem.git

> The process of creating an AWS is lengthy, but there are many guides out there

> For information about Tomcat installation https://tomcat.apache.org/download-90.cgi


## Usage

- Once an AWS DB is ready make sure to save the endpoint, port number, username, and password
- Then save them in an environment variable on the local system as this:
-     TRAINING_DB_ENDPOINT -> endpoint and port
-     TRAINING_DB_USERNAME -> username
-     TRAINING_DB_PASSWORD -> password
- In a DB application (DBeaver) connected to the AWS endpoint make a new database and name it ERS_DB. Then run the script ERS_DB.sql in this repository. 
   Note that the script is in PostgreSQL

- Load your Java IDE (in this case Spring STS v3). 
- Go to **File** **>** **Open Project from File System** and select this project
  **Expense Reimbursement System** wherever it was downloaded in your system


- Once Tomcat is installed and ready to run, on the **Servers** panel on Spring STS right click, **New > Server > Apache > Tomcat v9.0 Server** 
-  Double click on the newly created server icon and for **HTTP/1.1** put **9005** under Port Number
-  Right click on the newly created server icon again and select **Add and Remove**. Select the project name on the Available panel, **Add >**
-  It will move the project to the Configured panel, click **Finish**
-  Start the server by clicking on the Start button (or Ctrl+Alt+R)
  
-  Once the server is up and running go to a web browser and type in:
-    http://localhost:9005/ExpenseReimbursementSystem/index.html
-  This is the landing page of the project.
  
- To enter with authorized credentials use:
>                         **username**   **password**
>                         
>  **Employee**             mateoer         123
>  
>  **Finance Manager**       admin          abc




## License

No License
