Drop database carBooking;
create database carBooking;
use carBooking;
create table customers(
	customer_id INT NOT NULL AUTO_INCREMENT,
	first_name varchar(15),
	last_name varchar(15),
	age int,
	county varchar(15),
	dateOfBirth varchar(10),
	PRIMARY KEY (customer_id)
);

create table vehicles(
	vehicle_id INT NOT NULL AUTO_INCREMENT,
	vehicle_type varchar(20),
	vehicle_year varchar(10),
	vehicle_colour varchar(15),
	vehicle_engine float,
	PRIMARY KEY (vehicle_id)
);

create table bookings(
	booking_id int NOT NULL AUTO_INCREMENT,
	vehicle_id int,
	customer_id int,
	start_date varchar(10),
	end_date varchar(10),
	PRIMARY KEY (booking_id),
	FOREIGN KEY (vehicle_id) REFERENCES vehicles (vehicle_id),
	FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

INSERT INTO customers VALUES(
	1,"Conor", "Raftery", 25, "Galway", "04-03-1993"
);

INSERT INTO vehicles VALUES(
	1, "Ford", "01", "Black", 1.4
);

INSERT INTO bookings VALUES(
	1,1,1,"01-01-2018","02-01-2018"
);