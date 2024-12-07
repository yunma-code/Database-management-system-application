# 5200 Final Project
## Table of Contents
1. Overview
2. Features
3. Technical Specifications
4. Setup Instructions

### Overview
This is an Java-based GUI application designed to interact with an SQL database. 
It allows managing level users to access the Aquarium Management System to perform 
CRUD (Create, Read, Update, Delete) operations through a user interface. \
The Aquarium Management Database System includes entities: animals, staff, tank, care tasks (i.e. feeding, cleaning, training), schedule (scheduled time and staff for each task), and aquarium events (i.e. feeding time or performances session).

### Features
* Connect to MySQL database
* Perform CRUD Operations
  * Create new rows when clicking create button
  * Read and display data by default
  * Update existing data by typing new data in input box and clicking update button
  * Delete row according to table id

### Technical Specifications
* Programming Language: Java
* GUI Framework & libraries: Java Swing, AWT
* Database: MySQL
* Database Connector: JDBC
* Software: MySQL Workbench, IntelliJ

### Setup Instructions
**Prerequisites**
1. Set up SQL database (MySQL) with the necessary schema and data.
2. Install Java (version 11 or higher)
3. Download the JDBC database driver (mysql-connector-j-9.1.0) 
4. Import the database schema and data

**Steps**
1. Download the MySQL Dump file and import it by running the command (source /path/to/aquarium_management_system.sql)
2. Download mysql-connector-java-9.1.0.jar and add the jar file as database connector library to the project's classpath
3. Update database connection details (URL, USER, PASSWORD) in DatabaseConfig class
4. Run the application from IDE
