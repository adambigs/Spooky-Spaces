drop database if exists spooky_spaces_test;
create database spooky_spaces_test;
use spooky_spaces_test;

CREATE TABLE encounter_type (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name ENUM('visual', 'auditory', 'touch', 'temperature')
);

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
    type_id INT,
    CONSTRAINT fk_encounter_location_id FOREIGN KEY (location_id)
        REFERENCES location (location_id),
    CONSTRAINT fk_encounter_type_id FOREIGN KEY (type_id)
        REFERENCES encounter_type (type_id)
);

CREATE TABLE comments (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(25),
    rating INT,
    comment_text VARCHAR(500)
);

CREATE TABLE encounter_comments (
    encounter_id INT,
    comment_id INT,
    CONSTRAINT pk_encounter_comments PRIMARY KEY (encounter_id , comment_id),
    CONSTRAINT fk_encounter_comments_encounter_id FOREIGN KEY (encounter_id)
        REFERENCES encounter (encounter_id),
    CONSTRAINT fk_encounter_comments_comment_id FOREIGN KEY (comment_id)
        REFERENCES comments (comment_id)
);

CREATE TABLE wishlist (
    wishlist_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(25)
);

CREATE TABLE wishlist_location (
    wishlist_id INT,
    location_id INT,
    CONSTRAINT pk_wishlist_location PRIMARY KEY (wishlist_id , location_id),
    CONSTRAINT fk_wishlist_location_wishlist_id FOREIGN KEY (wishlist_id)
        REFERENCES wishlist (wishlist_id),
    CONSTRAINT fk_wishlist_location_location_id FOREIGN KEY (location_id)
        REFERENCES location (location_id)
);
