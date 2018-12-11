


<p align="center">
  <b>This is a JAX-RS/Jersey, Java RMI and JAXB frameworks to develop a simple Car Hire
Booking System.
</b><br>
</p>


# Description
This project creates a web client page which provieds users the ability to Create/Read/Update and Delete bookings in a bookings database. The web client interacts with a RESTful JAX-RS Web service which is deployed on a Apache Tomcat Server. The RESTful Web Service also acts as an RMI client to the RMI Database Server which will handle persistience and CRUD functionality of the database.
The Web Client page provides users with the ability to Create/Modify/Update/Delete
bookings for a specific vehicle for a given set of dates. The Web Client interacts with a RESTful JAX-RS
Web Service for bookings which is deployed on Apache Tomcat Server. The RESTful Web Service acts as
an RMI client to a RMI Database Server which handles persistence.

This project itself has 3 folders:

  * FrontEnd - Contains client side files
  * JerseyRestful - Contains files to communicate client and server
  * RMIServer - Contains files which communicate with server to database


# How to run the program

 * Download all files
 * Create database with script included (mySql)
 * Run RMI server
 * Run Restful Services
 * URL to client is: http://localhost:8080/JerseyRestful/webapi/FrontEnd
 * To view via XML: http://localhost:8080/JerseyRestful/webapi/myresource


# Technologies

* [Maven](https://maven.apache.org/)
* [Tomcat Server](https://tomcat.apache.org/)
* [Jersey](https://jersey.github.io/)
* [JAX-RS](https://en.wikipedia.org/wiki/Java_API_for_RESTful_Web_Services)
* [MySQL Server](https://dev.mysql.com/downloads/)
* [JAXB](https://www.oracle.com/technetwork/articles/javase/index-140168.html)

# Developers
* [Conor Raftery](https://github.com/Raftery93)	
