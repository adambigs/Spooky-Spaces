drop database if exists spooky_spaces_test;
create database spooky_spaces_test;
use spooky_spaces_test;

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

-- CREATE TABLE wishlist_location (
--     wishlist_id INT,
--     location_id INT,
--     CONSTRAINT pk_wishlist_location PRIMARY KEY (wishlist_id , location_id),
--     CONSTRAINT fk_wishlist_location_wishlist_id FOREIGN KEY (wishlist_id)
--         REFERENCES wishlist (wishlist_id),
--     CONSTRAINT fk_wishlist_location_location_id FOREIGN KEY (location_id)
--         REFERENCES location (location_id)
-- );

delimiter //
create procedure set_known_good_state()
begin

-- delete from wishlist_location;
-- alter table wishlist_location auto_increment = 1;
delete from wishlist;
alter table wishlist auto_increment = 1;
delete from comments;
alter table comments auto_increment =1;
delete from encounter_type;
delete from encounter;
alter table encounter auto_increment = 1;
delete from location;
alter table location auto_increment = 1;

insert into location 
(location_name, address, latitude, longitude)
values ('Pfister Hotel', '424 E Wisconsin Ave', '43.03956219', '-87.90551367');

insert into encounter
(encounter_description, location_id)
values ('Built in the 19th century, the hotel is apparently haunted by its namesake, Charles Pfister, who likes to haunt MLB players staying in the hotel.', 1);

insert into encounter_type
(type_id, encounter_id)
values (1, 1),(2, 1);

insert into comments
(username, rating, comment_text)
values ('cooldude69', '5', 'This place was so spooky. I was scared.');

insert into wishlist
(username, location_id)
values ('cooldude69', 1),
("swagmaster9000", 1);

-- insert into wishlist_location
-- (wishlist_id, location_id)
-- values(1,1);


-- select w.username, l.location_name
-- from wishlist_location wl
-- left outer join location l on l.location_id = wl.location_id
-- left outer join wishlist w on w.wishlist_id = wl.wishlist_id;


end //
delimiter ;