DROP TABLE IF EXISTS museum_exhibit;
DROP TABLE IF EXISTS exhibition;
DROP TABLE IF EXISTS installation;
DROP TABLE IF EXISTS museum;

CREATE TABLE museum (
	museum_id int NOT NULL AUTO_INCREMENT,
	museum_name varchar(250) NOT NULL,
	museum_address varchar(250) NOT NULL,
	museum_city varchar(250) NOT NULL,
	museum_country varchar(250) NOT NULL,
	museum_zip varchar(250) NOT NULL,
	museum_phone varchar(250) NOT NULL,
	museum_website varchar(250) NOT NULL
	PRIMARY KEY (museum_Id)
);

CREATE TABLE installation (
	installation_id int NOT NULL AUTO_INCREMENT,
	name_of_installation varchar(250) NOT NULL,
	type_of_installation varchar(250) NOT NULL
	PRIMARY KEY (installation_id),
	FOREIGN KEY (museum_id) REFERENCES museum (museum_id) ON DELETE CASCADE
);

CREATE TABLE exhibition (
	exhibition_id int NOT NULL AUTO_INCREMENT,
	name_of_exhibition varchar(250) NOT NULL,
	owner_of_exhibition varchar(250) NOT NULL
	type_of_exhibition varchar(250) NOT NULL
	PRIMARY KEY (exhibition_id),
);

CREATE TABLE museum_exhibition (
	exhibition_id int NOT NULL,
	museum_id int NOT NULL,
	FOREIGN KEY (museum_id) REFERENCES museum (museum_id) ON DELETE CASCADE,
	FOREIGN KEY (exhibition_id) REFERENCES exhibition (exhibition_id) ON DELETE CASCADE,
	UNIQUE KEY (exhibition_id, museum_id)
);