create database DataBloodline;
use DataBloodline;

create table students (
	s_id int primary key auto_increment, 
	s_name varchar(20), 
	course_id int
);

create table courses (
	c_id int primary key auto_increment,
	c_name varchar(50)
);

create table grades (
	g_id int primary key auto_increment,
	student_id int not null,
	course_id int not null,
	grade double default 0
);

create table ranks (
	r_id int primary key auto_increment,
	grade_id int not null,
	`rank` int
);

create table transcripts (
	t_id int primary key auto_increment,
	grade_id int not null,
	rank_id int not null,
	transcript varchar(200)
);

create table tables (
	t_id int primary key auto_increment,
	table_name varchar(50)
);

create table dependencies (
	d_id int primary key auto_increment,
	parent_id int not null,
	child_id int not null
);

insert into tables (table_name) 
values('students'), ('courses'), ('grades'), ('ranks'), ('transcripts');

insert into dependencies (parent_id, child_id) 
values(1, 3), (2, 3), (3, 4), (3, 5), (4, 5);