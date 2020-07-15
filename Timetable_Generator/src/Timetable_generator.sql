drop table students cascade constraints;
drop table courses cascade constraints;
drop table branches cascade constraints;
drop table year cascade constraints;
drop table course_type cascade constraints;
drop table attendance cascade constraints;
drop table schools cascade constraints;
drop table time_slots cascade constraints;
drop table classrooms cascade constraints;
drop table faculty cascade constraints;
drop table admins cascade constraints;
drop table classroom_type cascade constraints;
drop table timetable cascade constraints;
drop view course_attendance;
drop view student_attendance;
drop view student_att;
drop view timetable_course_school;

create table branches(
	branch_id int primary key,
	name varchar(10)
);

create table year(
	year int primary key
);

create table course_type(
	type_id int,
	type_name varchar(10),
	hours_weekly int,
	primary key(type_id,hours_weekly)
);

create table schools(
	school_id int primary key,
	name varchar(10)
);

create table time_slots(
	slot int primary key
);

create table classroom_type(
	type_id int primary key,
	type_name varchar(20)
);

create table students (
	sroll varchar(10) primary key,
	name varchar(20),
	year int,
	password varchar(20),
	branch_id int,
	school_id int,
	foreign key (school_id) references schools(school_id) on delete cascade,
	foreign key (year) references year(year) on delete cascade,
	foreign key (branch_id) references branches(branch_id) on delete cascade
);

create table faculty(
	f_id int primary key,
	f_name varchar(20),
	password varchar(10),
	school_id int,
	foreign key (school_id) references schools(school_id) on delete cascade
);

create table courses (
	cid int primary key,
	cname varchar(25) not null,
	f_id int not null,
	hours_weekly int not null,
	school_id int not null,
	course_type_id int not null,
	classroom_type_id int not null,
	foreign key (f_id) references faculty(f_id) on delete cascade,
	foreign key (school_id) references schools(school_id) on delete cascade,
	foreign key (course_type_id,hours_weekly) references course_type(type_id,hours_weekly) on delete cascade,
	foreign key (classroom_type_id) references classroom_type(type_id) on delete cascade
);

create table attendance (
	sroll varchar(10),
	course_id int,
	total_no_classes int not null,
	atd int not null,
	primary key(sroll,course_id),
	foreign key (sroll) references students(sroll) on delete cascade,
	foreign key (course_id) references courses(cid) on delete cascade
);

create view course_attendance
	as select sroll roll_no,cid course_id,cname course_name,f_name faculty_name,hours_weekly 	hours_weekly,total_no_classes total_classes,atd attended_classes 
	from ((faculty inner join courses on faculty.f_id=courses.f_id) inner join attendance on 	attendance.course_id=courses.cid);

create view student_attendance
	as select sroll roll_no,cid course_id,cname course_name,f_name faculty_name,hours_weekly 	hours_weekly,total_no_classes total_classes,atd attended_classes,courses.f_id f_id
	from ((faculty inner join courses on faculty.f_id=courses.f_id) inner join attendance on 	attendance.course_id=courses.cid);

create view student_att
	as select a.sroll roll_no,a.course_id course_id,s.year year,s.branch_id branch_id
	from attendance a inner join students s on a.sroll=s.sroll;

create table classrooms(
	cr_id int,
	school_id int,
	capacity int not null,
	type_id int not null,
	primary key(cr_id,school_id),
	foreign key (school_id) references schools(school_id) on delete cascade,
	foreign key (type_id) references classroom_type(type_id) on delete cascade
);

create table admins(
	a_id int primary key,
	name varchar(20),
	password varchar(10)
);

create table timetable(
	cid int,
	cr_id int,
	school_id int,
	day int,
	slot int,
	primary key(cid,cr_id,school_id,day,slot),
	foreign key (cid) references courses(cid) on delete cascade,
	foreign key (cr_id,school_id) references classrooms(cr_id,school_id) on delete cascade,
	foreign key (slot) references time_slots(slot) on delete cascade
);

create view timetable_course_school
	as select t.cid cid,t.cr_id cr_id,t.school_id school_id,t.day day,t.slot slot,c.cname cname,s.name sname
	from ((timetable t inner join courses c on t.cid=c.cid) inner join schools s on t.school_id=s.school_id);


insert into branches values(1,'CSE');
insert into branches values(2,'ECE');
insert into branches values(3,'EE');
insert into branches values(4,'Mech');
insert into branches values(5,'Civil');

insert into year values(1);
insert into year values(2);
insert into year values(3);
insert into year values(4);

insert into course_type values(1,'Theory',3);
insert into course_type values(1,'Theory',4);
insert into course_type values(1,'Theory',5);
insert into course_type values(2,'LAB',3);

insert into schools values(1,'SES');
insert into schools values(2,'SMS');
insert into schools values(3,'SIF');
--in project LBC is never assigned to any course
insert into schools values(4,'LBC');

insert into time_slots values(1);
insert into time_slots values(2);
insert into time_slots values(3);
insert into time_slots values(4);
insert into time_slots values(5);
insert into time_slots values(6);
insert into time_slots values(7);
insert into time_slots values(8);
insert into time_slots values(9);

insert into classroom_type values(1,'Theory');
insert into classroom_type values(2,'Computer LAB');
insert into classroom_type values(3,'Microprocessor LAB');
insert into classroom_type values(4,'EE LAB');

insert into students values('17CS01020','N Harshith',3,'17CS01020',1,1);
insert into students values('17CS01025','D Yashwanth',3,'17CS01025',1,1);
insert into students values('17CS01028','V Shanmukha',3,'17CS01028',1,1);
insert into students values('17EC01020','Ram',3,'17EC01020',2,1);
insert into students values('17EC01021','Shyam',3,'17EC01021',2,1);
insert into students values('17EC01022','Manish',3,'17EC01022',2,1);
insert into students values('17EC01023','Kalyan',3,'17EC01023',2,1);
insert into students values('17EE01020','Ramesh',3,'17EE01020',3,1);
insert into students values('17EE01021','Suresh',3,'17EE01021',3,1);
insert into students values('17ME01021','Prabhu',3,'17ME01021',4,2);
insert into students values('17ME01022','Kranti',3,'17ME01022',4,2);


insert into faculty values(1,'PLB','1',1);
insert into faculty values(2,'KK','2',1);
insert into faculty values(3,'BR','3',1);
insert into faculty values(4,'MR','4',1);
insert into faculty values(5,'SS','5',2);

insert into courses values(1,'DBMS theory',1,4,1,1,1);
insert into courses values(2,'DBMS LAB',1,3,1,2,2);
insert into courses values(3,'Microprocessor theory',2,4,1,1,1);
insert into courses values(4,'Microprocessor LAB',2,3,1,2,3);
insert into courses values(5,'EE theory',3,5,1,1,1);
insert into courses values(6,'EE LAB',4,3,1,2,4);
insert into courses values(7,'Machines theory',3,5,2,1,1);
insert into courses values(8,'Lathe theory',5,4,2,1,1);

insert into attendance values('17CS01020',1,0,0);
insert into attendance values('17CS01020',2,0,0);
insert into attendance values('17CS01025',1,0,0);
insert into attendance values('17CS01025',2,0,0);
insert into attendance values('17CS01028',1,0,0);
insert into attendance values('17CS01028',2,0,0);
insert into attendance values('17EC01020',3,0,0);
insert into attendance values('17EC01021',3,0,0);
insert into attendance values('17EC01021',4,0,0);
insert into attendance values('17EC01022',3,0,0);
insert into attendance values('17EC01022',4,0,0);
insert into attendance values('17EC01023',3,0,0);
insert into attendance values('17EC01023',4,0,0);
insert into attendance values('17EE01020',5,0,0);
insert into attendance values('17EE01020',6,0,0);
insert into attendance values('17EE01021',5,0,0);
insert into attendance values('17EE01021',6,0,0);
insert into attendance values('17ME01021',7,0,0);
insert into attendance values('17ME01022',8,0,0);
insert into attendance values('17EC01020',8,0,0);

insert into classrooms values(001,1,30,1);
insert into classrooms values(003,1,30,1);
insert into classrooms values(201,1,50,2);
insert into classrooms values(002,1,50,3);
insert into classrooms values(102,1,50,4);
insert into classrooms values(001,2,30,1);
insert into classrooms values(003,2,30,1);


insert into admins values(1,'N Harshith','1');
insert into admins values(2,'D Yashwanth','2');
insert into admins values(3,'V Shanmukha','3');



	
