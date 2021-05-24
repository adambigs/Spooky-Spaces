drop database if exists spooky_spaces;
create database spooky_spaces;
use spooky_spaces;

CREATE TABLE location (
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    location_name VARCHAR(100),
    address VARCHAR(100),
    latitude VARCHAR(50),
    longitude VARCHAR(50),
    image VARCHAR(100)
);

CREATE TABLE encounter (
    encounter_id INT PRIMARY KEY AUTO_INCREMENT,
    encounter_description VARCHAR(2000),
    location_id INT,
    CONSTRAINT fk_encounter_location_id FOREIGN KEY (location_id)
        REFERENCES location (location_id)
);

CREATE TABLE encounter_type (
    type_id INT PRIMARY KEY,
    encounter_id INT, 
    CONSTRAINT encounter_type_encounter_id FOREIGN KEY (encounter_id)
	REFERENCES encounter (encounter_id)
);


CREATE TABLE comments (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(25),
    rating INT,
    comment_text VARCHAR(500),
    encounter_id INT,
    CONSTRAINT fk_comments_encounter_id FOREIGN KEY (encounter_id)
        REFERENCES encounter (encounter_id)
);

CREATE TABLE wishlist (
    wishlist_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(25),
    location_id int,
    CONSTRAINT fk_wishlist_location_location_id FOREIGN KEY (location_id)
        REFERENCES location (location_id)
);

