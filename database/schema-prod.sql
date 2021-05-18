drop database if exists spooky_spaces;
create database spooky_spaces;
use spooky_spaces;

create table encounter_type (
type_id int primary key auto_increment,
type_name enum('visual', 'auditory', 'touch', 'temperature')
);

create table location (
location_id int primary key auto_increment,
location_name varchar(100),
address varchar(100),
latitude varchar(50),
longitude varchar(50),
image varchar(100)
);

create table encounter (
encounter_id int primary key auto_increment,
encounter_description varchar(2000),
constraint fk_encounter_location_id
foreign key (location_id)
references location(location_id),
constraint fk_encounter_type_id
foreign key (type_id)
references encounter_type(type_id)
);

create table comments (
comment_id int primary key auto_increment,
username varchar(25),
rating int,
comment_text varchar(500)
);

create table encounter_comments (
encounter_id int,
comment_id int,
constraint pk_encounter_comments
primary key (encounter_id, comment_id),
constraint fk_encounter_comments_encounter_id
foreign key (encounter_id)
references encounter(encounter_id),
constraint fk_encounter_comments_comment_id
foreign key (comment_id)
references 
