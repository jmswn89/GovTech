# Readme - Coding Challenge for GovTech
This repository contains solution for GovTech's coding challenge. 

## How to Compile / Install

This application is created with Apache Maven. The Apache Maven can be downloaded from  [https://maven.apache.org/](https://maven.apache.org/)download.cgi. Download and install Java (at least version 8) from [https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). Extract the downloaded Apache Maven to a directory.



To clean and package the files, plug-ins, and libraries before running the application:

% *set JAVA_HOME=*<*JDK directory*\>

% *cd* <*the solution source code directory*\>/*todoScanner*

% <*maven installation directory*\>/*mvn clean package*



Running the command above will also run the unit tests automatically.

## How to Run the Application

To run the application using Maven:

% *mvn exec:java -Dexec.mainClass=org.james.GovTech.developer.interview.todoScanner.TodoScanner -Dexec.args=src/test/resources*

To run the application by executing the JAR file directly:

% *cd target*

% *java -cp todoScanner-0.0.1-SNAPSHOT.jar org.james.GovTech.developer.interview.todoScanner.TodoScanner ..\src\test\resources*