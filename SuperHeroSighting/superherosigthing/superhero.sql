drop database if exists `superhero`;

create database  `superhero`;

use `superhero`;

create table if not exists `super_person`
(

`super_person_id` int not null auto_increment,
`name` varchar (30) not null,
`super_power` text not null,
`description` text null,
`super_person_side` bool not null,
primary key (super_person_id)

);

create table if not exists `organization`
(

`organization_id` int not null auto_increment,
`organization_name` varchar(30) not null,
`phone` varchar(30) null,
`description` text,
`location_id` int not null,
primary key (`organization_id`)

);

create table if not exists `location`
(

`location_id` int not null auto_increment,
`location_name` varchar(30) not null,
`street` varchar (30) not null,
`city` varchar(30) not null,
`state` varchar(30) not null,
`zip` varchar(30) not null,
`latitude` double  null,
`longitude` double null,
`description` text,
primary key (`location_id`)

);

create table if not exists `sighting`
(

`sighting_id` int not null auto_increment,
`date` date not null,
`description` text null,
`location_id` int not null,
primary key(`sighting_id`)

);

-- bridge table

create table if not exists `super_person_sighting`
(

`super_person_sighting_id` int not null auto_increment,
`super_person_id` int not null,
`sighting_id` int not null,
primary key (`super_person_sighting_id`)

);

create table if not exists `super_person_organization`
(

`super_person_organization_id` int not null auto_increment,
`super_person_id` int not null, 
`organization_id` int not null,
primary key (`super_person_organization_id`)

);

-- constraint foreign key

alter table `organization`
add constraint `fk_organization_location`
foreign key(`location_id`) references `location`(`location_id`);

alter table `sighting`
add constraint `fk_sighting_location`
foreign key(`location_id`) references `location`(`location_id`);

-- bridge table constraint forieng key

alter table `super_person_sighting`
add constraint `fk_super_person_sighting_sighting`
foreign key(`sighting_id`)references `sighting`(`sighting_id`);

alter table `super_person_sighting`
add constraint `fk_super_person_sighting_super_person`
foreign key(`super_person_id`)references `super_person`(`super_person_id`);


alter table `super_person_organization`
add constraint `fk_super_person_organization`
foreign key(`organization_id`)references `organization`(`organization_id`);

alter table `super_person_organization`
add constraint `fk_super_person_organization_super_person`
foreign key(`super_person_id`)references `super_person`(`super_person_id`);

select o.* 
            from organization o 
            inner join location l on l.location_id = o.location_id
            where l.location_id= 1;
