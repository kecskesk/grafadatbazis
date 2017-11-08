DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS station;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE schedule(
	ID INT PRIMARY KEY AUTO_INCREMENT, 
	origin INT,
	destination INT,
	start_time DATE,
	end_time DATE
);

CREATE TABLE station(
	ID INT PRIMARY KEY AUTO_INCREMENT,  
	station_name VARCHAR(255)
);

CREATE TABLE users(
	ID INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(255) UNIQUE,
	enabled BIT(1),
	password VARCHAR(255)
);

CREATE TABLE user_roles(
	ID INT PRIMARY KEY AUTO_INCREMENT, 
	username VARCHAR(255),
	user_role VARCHAR(255)
);