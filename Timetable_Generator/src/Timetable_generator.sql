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
	type_name varchar(30)
);

create table students (
	sroll varchar(10) primary key,
	name varchar(20) not null,
	year int not null,
	password varchar(20) not null,
	branch_id int not null,
	school_id int not null,
	foreign key (school_id) references schools(school_id) on delete cascade,
	foreign key (year) references year(year) on delete cascade,
	foreign key (branch_id) references branches(branch_id) on delete cascade
);

create table faculty(
	f_id int primary key,
	f_name varchar(20) not null,
	password varchar(10) not null,
	school_id int not null,
	foreign key (school_id) references schools(school_id) on delete cascade
);

create table courses (
	cid int primary key,
	cname varchar(40) not null,
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
	sroll varchar(10) not null,
	course_id int not null,
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
insert into classroom_type values(4,'Electronics LAB');
insert into classroom_type values(5,'Machinery LAB');
insert into classroom_type values(6,'Fliud Dynamics LAB');
insert into classroom_type values(7,'Civil Engineering LAB');
insert into classroom_type values(8,'ELectronics LAB');
insert into classroom_type values(9,'IMP LAB');

insert into students values('19CS01008','sai',1,'19CS01008',1,1);
insert into students values('19CS01009','sree',1,'19CS01009',1,1);
insert into students values('19CS01010','mahesh',1,'19CS01010',1,1);
insert into students values('19CS01001','Ramanandh',1,'19CS01001',1,1);
insert into students values('19CS01002','Shyam kumar',1,'19CS01002',1,1);
insert into students values('19CS01003','Manish pandey',1,'19CS01003',1,1);
insert into students values('19CS01004','Kalyan verma',1,'19CS01004',1,1);
insert into students values('19CS01005','Ramesh gundu',1,'19CS01005',1,1);
insert into students values('19CS01006','Suresh babu',1,'19CS01006',1,1);
insert into students values('19CS01007','Prabhudeva',1,'19CS01007',1,1);

insert into students values('19EC01007','Ram dev',1,'19EC01007',2,1);
insert into students values('19EC01008','Shyam',1,'19EC01008',2,1);
insert into students values('19EC01009','Manish',1,'19EC01009',2,1);
insert into students values('19EC01010','Kalyan',1,'19EC01010',2,1);
insert into students values('19EC01001','Katharina',1,'19EC01001',2,1);
insert into students values('19EC01002','Noah',1,'19EC01002',2,1);
insert into students values('19EC01003','Helge',1,'19EC01003',2,1);
insert into students values('19EC01004','Tronte',1,'19EC01004',2,1);
insert into students values('19EC01005','Adam',1,'19EC01005',2,1);
insert into students values('19EC01006','Eva',1,'19EC01006',2,1);

insert into students values('19EE01009','Ramesh',1,'19EE01009',3,1);
insert into students values('19EE01010','Suresh',1,'19EE01010',3,1);
insert into students values('19EE01001','Claudia',1,'19EE01001',3,1);
insert into students values('19EE01002','Hanno',1,'19EE01002',3,1);
insert into students values('19EE01003','Bhanu',1,'19EE01003',3,1);
insert into students values('19EE01004','Rambabu',1,'19EE01004',3,1);
insert into students values('19EE01005','Raghu',1,'19EE01005',3,1);
insert into students values('19EE01006','Hari',1,'19EE01006',3,1);
insert into students values('19EE01007','Chinmay',1,'19EE01007',3,1);
insert into students values('19EE01008','Charan',1,'19EE01008',3,1);

insert into students values('19ME01009','Prabhu',1,'19ME01009',4,2);
insert into students values('19ME01010','Kranti',1,'19ME01010',4,2);
insert into students values('19ME01001','Oliver',1,'19ME01001',4,2);
insert into students values('19ME01002','Benjamin',1,'19ME01002',4,2);
insert into students values('19ME01003','Elijah',1,'19ME01003',4,2);
insert into students values('19ME01004','Lucas',1,'19ME01004',4,2);
insert into students values('19ME01005','Mason',1,'19ME01005',4,2);
insert into students values('19ME01006','Logan',1,'19ME01006',4,2);
insert into students values('19ME01007','Alexander',1,'19ME01007',4,2);
insert into students values('19ME01008','Ethan',1,'19ME01008',4,2);

insert into students values('19CE01001','Samuel',1,'19CE01001',5,3);
insert into students values('19CE01002','David',1,'19CE01002',5,3);
insert into students values('19CE01003','Joseph',1,'19CE01003',5,3);
insert into students values('19CE01004','Carter',1,'19CE01004',5,3);
insert into students values('19CE01005','Owen',1,'19CE01005',5,3);
insert into students values('19CE01006','Wyatt',1,'19CE01006',5,3);
insert into students values('19CE01007','John',1,'19CE01007',5,3);
insert into students values('19CE01008','Jack',1,'19CE01008',5,3);
insert into students values('19CE01009','Luke',1,'19CE01009',5,3);
insert into students values('19CE01010','Jayden',1,'19CE01010',5,3);

insert into students values('18CS01001','Dylan',2,'18CS01001',1,1);
insert into students values('18CS01002','Grayson',2,'18CS01002',1,1);
insert into students values('18CS01003','Levi',2,'18CS01003',1,1);
insert into students values('18CS01004','Isaac',2,'18CS01004',1,1);
insert into students values('18CS01005','Gabriel',2,'18CS01005',1,1);
insert into students values('18CS01006','Julian',2,'18CS01006',1,1);
insert into students values('18CS01007','Mateo',2,'18CS01007',1,1);
insert into students values('18CS01008','Anthony',2,'18CS01008',1,1);
insert into students values('18CS01009','Jaxon',2,'18CS01009',1,1);
insert into students values('18CS01010','Jordan',2,'18CS01010',1,1);

insert into students values('18EC01001','Lincoln',2,'18EC01001',2,1);
insert into students values('18EC01002','Joshua',2,'18EC01002',2,1);
insert into students values('18EC01003','Christopher',2,'18EC01003',2,1);
insert into students values('18EC01004','Andrew',2,'18EC01004',2,1);
insert into students values('18EC01005','Theodore',2,'18EC01005',2,1);
insert into students values('18EC01006','Caleb',2,'18EC01006',2,1);
insert into students values('18EC01007','Ryan',2,'18EC01007',2,1);
insert into students values('18EC01008','Asher',2,'18EC01008',2,1);
insert into students values('18EC01009','Nathan',2,'18EC01009',2,1);
insert into students values('18EC01010','Santiago',2,'18EC01010',2,1);

insert into students values('18EE01001','Thomas',2,'18EE01001',3,1);
insert into students values('18EE01002','Leo',2,'18EE01002',3,1);
insert into students values('18EE01003','Isaiah',2,'18EE01003',3,1);
insert into students values('18EE01004','Charles',2,'18EE01004',3,1);
insert into students values('18EE01005','Josiah',2,'18EE01005',3,1);
insert into students values('18EE01006','Hudson',2,'18EE01006',3,1);
insert into students values('18EE01007','Christian',2,'18EE01007',3,1);
insert into students values('18EE01008','Hunter',2,'18EE01008',3,1);
insert into students values('18EE01009','Connor',2,'18EE01009',3,1);
insert into students values('18EE01010','Austin',2,'18EE01010',3,1);

insert into students values('18ME01001','Eli',2,'18ME01001',4,2);
insert into students values('18ME01002','Ezra',2,'18ME01002',4,2);
insert into students values('18ME01003','Aaron',2,'18ME01003',4,2);
insert into students values('18ME01004','Landon',2,'18ME01004',4,2);
insert into students values('18ME01005','Adrian',2,'18ME01005',4,2);
insert into students values('18ME01006','Jonathan',2,'18ME01006',4,2);
insert into students values('18ME01007','Nolan',2,'18ME01007',4,2);
insert into students values('18ME01008','Jeremiah',2,'18ME01008',4,2);
insert into students values('18ME01009','Easton',2,'18ME01009',4,2);
insert into students values('18ME01010','Greyson',2,'18ME01010',4,2);

insert into students values('18CE01001','Elias',2,'18CE01001',5,3);
insert into students values('18CE01002','Colton',2,'18CE01002',5,3);
insert into students values('18CE01003','Cameron',2,'18CE01003',5,3);
insert into students values('18CE01004','Carson',2,'18CE01004',5,3);
insert into students values('18CE01005','Robert',2,'18CE01005',5,3);
insert into students values('18CE01006','Angel',2,'18CE01006',5,3);
insert into students values('18CE01007','Maverick',2,'18CE01007',5,3);
insert into students values('18CE01008','Nicholas',2,'18CE01008',5,3);
insert into students values('18CE01009','Dominic',2,'18CE01009',5,3);
insert into students values('18CE01010','Jaxson',2,'18CE01010',5,3);

insert into students values('17CS01020','N Harshith',3,'17CS01020',1,1);
insert into students values('17CS01025','D Yashwanth',3,'17CS01025',1,1);
insert into students values('17CS01028','V Shanmukha',3,'17CS01028',1,1);
insert into students values('17CS01001','Jonas',3,'17CS01001',1,1);
insert into students values('17CS01002','Martha',3,'17CS01002',1,1);
insert into students values('17CS01003','Michael',3,'17CS01003',1,1);
insert into students values('17CS01004','Bartoz',3,'17CS01004',1,1);
insert into students values('17CS01005','Ulrich',3,'17CS01005',1,1);
insert into students values('17CS01006','Agnes',3,'17CS01006',1,1);
insert into students values('17CS01007','Hannah',3,'17CS01007',1,1);

insert into students values('17EC01020','Ram',3,'17EC01020',2,1);
insert into students values('17EC01021','Shyam',3,'17EC01021',2,1);
insert into students values('17EC01022','Manish',3,'17EC01022',2,1);
insert into students values('17EC01023','Kalyan',3,'17EC01023',2,1);
insert into students values('17EC01001','Katharina',3,'17EC01001',2,1);
insert into students values('17EC01002','Noah',3,'17EC01002',2,1);
insert into students values('17EC01003','Helge',3,'17EC01003',2,1);
insert into students values('17EC01004','Tronte',3,'17EC01004',2,1);
insert into students values('17EC01005','Adam',3,'17EC01005',2,1);
insert into students values('17EC01006','Eva',3,'17EC01006',2,1);

insert into students values('17EE01020','Ramesh',3,'17EE01020',3,1);
insert into students values('17EE01021','Suresh',3,'17EE01021',3,1);
insert into students values('17EE01001','Claudia',3,'17EE01001',3,1);
insert into students values('17EE01002','Hanno',3,'17EE01002',3,1);
insert into students values('17EE01003','Bhanu',3,'17EE01003',3,1);
insert into students values('17EE01004','Rambabu',3,'17EE01004',3,1);
insert into students values('17EE01005','Raghu',3,'17EE01005',3,1);
insert into students values('17EE01006','Hari',3,'17EE01006',3,1);
insert into students values('17EE01007','Chinmay',3,'17EE01007',3,1);
insert into students values('17EE01008','Charan',3,'17EE01008',3,1);

insert into students values('17ME01021','Prabhu',3,'17ME01021',4,2);
insert into students values('17ME01022','Kranti',3,'17ME01022',4,2);
insert into students values('17ME01001','Oliver',3,'17ME01001',4,2);
insert into students values('17ME01002','Benjamin',3,'17ME01002',4,2);
insert into students values('17ME01003','Elijah',3,'17ME01003',4,2);
insert into students values('17ME01004','Lucas',3,'17ME01004',4,2);
insert into students values('17ME01005','Mason',3,'17ME01005',4,2);
insert into students values('17ME01006','Logan',3,'17ME01006',4,2);
insert into students values('17ME01007','Alexander',3,'17ME01007',4,2);
insert into students values('17ME01008','Ethan',3,'17ME01008',4,2);

insert into students values('17CE01001','Samuel',3,'17CE01001',5,3);
insert into students values('17CE01002','David',3,'17CE01002',5,3);
insert into students values('17CE01003','Joseph',3,'17CE01003',5,3);
insert into students values('17CE01004','Carter',3,'17CE01004',5,3);
insert into students values('17CE01005','Owen',3,'17CE01005',5,3);
insert into students values('17CE01006','Wyatt',3,'17CE01006',5,3);
insert into students values('17CE01007','John',3,'17CE01007',5,3);
insert into students values('17CE01008','Jack',3,'17CE01008',5,3);
insert into students values('17CE01009','Luke',3,'17CE01009',5,3);
insert into students values('17CE01010','Jayden',3,'17CE01010',5,3);

insert into students values('16CS01001','Dylan',4,'16CS01001',1,1);
insert into students values('16CS01002','Grayson',4,'16CS01002',1,1);
insert into students values('16CS01003','Levi',4,'16CS01003',1,1);
insert into students values('16CS01004','Isaac',4,'16CS01004',1,1);
insert into students values('16CS01005','Gabriel',4,'16CS01005',1,1);
insert into students values('16CS01006','Julian',4,'16CS01006',1,1);
insert into students values('16CS01007','Mateo',4,'16CS01007',1,1);
insert into students values('16CS01008','Anthony',4,'16CS01008',1,1);
insert into students values('16CS01009','Jaxon',4,'16CS01009',1,1);
insert into students values('16CS01010','Jordan',4,'16CS01010',1,1);

insert into students values('16EC01001','Lincoln',4,'16EC01001',2,1);
insert into students values('16EC01002','Joshua',4,'16EC01002',2,1);
insert into students values('16EC01003','Christopher',4,'16EC01003',2,1);
insert into students values('16EC01004','Andrew',4,'16EC01004',2,1);
insert into students values('16EC01005','Theodore',4,'16EC01005',2,1);
insert into students values('16EC01006','Caleb',4,'16EC01006',2,1);
insert into students values('16EC01007','Ryan',4,'16EC01007',2,1);
insert into students values('16EC01008','Asher',4,'16EC01008',2,1);
insert into students values('16EC01009','Nathan',4,'16EC01009',2,1);
insert into students values('16EC01010','Santiago',4,'16EC01010',2,1);

insert into students values('16EE01001','Thomas',4,'16EE01001',3,1);
insert into students values('16EE01002','Leo',4,'16EE01002',3,1);
insert into students values('16EE01003','Isaiah',4,'16EE01003',3,1);
insert into students values('16EE01004','Charles',4,'16EE01004',3,1);
insert into students values('16EE01005','Josiah',4,'16EE01005',3,1);
insert into students values('16EE01006','Hudson',4,'16EE01006',3,1);
insert into students values('16EE01007','Christian',4,'16EE01007',3,1);
insert into students values('16EE01008','Hunter',4,'16EE01008',3,1);
insert into students values('16EE01009','Connor',4,'16EE01009',3,1);
insert into students values('16EE01010','Austin',4,'16EE01010',3,1);

insert into students values('16ME01001','Eli',4,'16ME01001',4,2);
insert into students values('16ME01002','Ezra',4,'16ME01002',4,2);
insert into students values('16ME01003','Aaron',4,'16ME01003',4,2);
insert into students values('16ME01004','Landon',4,'16ME01004',4,2);
insert into students values('16ME01005','Adrian',4,'16ME01005',4,2);
insert into students values('16ME01006','Jonathan',4,'16ME01006',4,2);
insert into students values('16ME01007','Nolan',4,'16ME01007',4,2);
insert into students values('16ME01008','Jeremiah',4,'16ME01008',4,2);
insert into students values('16ME01009','Easton',4,'16ME01009',4,2);
insert into students values('16ME01010','Greyson',4,'16ME01010',4,2);

insert into students values('16CE01001','Elias',4,'16CE01001',5,3);
insert into students values('16CE01002','Colton',4,'16CE01002',5,3);
insert into students values('16CE01003','Cameron',4,'16CE01003',5,3);
insert into students values('16CE01004','Carson',4,'16CE01004',5,3);
insert into students values('16CE01005','Robert',4,'16CE01005',5,3);
insert into students values('16CE01006','Angel',4,'16CE01006',5,3);
insert into students values('16CE01007','Maverick',4,'16CE01007',5,3);
insert into students values('16CE01008','Nicholas',4,'16CE01008',5,3);
insert into students values('16CE01009','Dominic',4,'16CE01009',5,3);
insert into students values('16CE01010','Jaxson',4,'16CE01010',5,3);

insert into faculty values(1,'Harry','1',1);
insert into faculty values(2,'Ema Watson','2',1);
insert into faculty values(3,'Paul','3',1);
insert into faculty values(4,'Abimanyudu','4',1);
insert into faculty values(5,'Dronacharya','5',1);
insert into faculty values(6,'Steven','6',1);
insert into faculty values(7,'Thiago','7',1);
insert into faculty values(8,'Zane','8',1);
insert into faculty values(9,'Matias','9',1);
insert into faculty values(10,'Judah','10',1);
insert into faculty values(11,'Knox','11',2);
insert into faculty values(12,'Kaden','12',2);
insert into faculty values(13,'Paxton','13',2);
insert into faculty values(14,'Kyrie','14',2);
insert into faculty values(15,'Kyle','15',2);
insert into faculty values(16,'Griffin','16',3);
insert into faculty values(17,'Josue','17',3);
insert into faculty values(18,'Kenneth','18',3);
insert into faculty values(19,'Beckett','19',3);
insert into faculty values(20,'Enzo','20',3);

insert into courses values(51,'mechanics',12,5,2,1,1);
insert into courses values(52,'Mathematics-1',9,5,1,1,1);
insert into courses values(53,'physics',15,5,2,1,1);
insert into courses values(54,'programming',2,4,1,1,1);
insert into courses values(55,'Electronic technology',6,5,1,1,1);
insert into courses values(56,'programming LAB',3,3,1,2,2);
insert into courses values(57,'Electronic technology LAB',6,3,1,2,8);
insert into courses values(58,'IMP LAB',20,3,2,2,9);

insert into courses values(59,'computer environments',4,5,1,1,1);
insert into courses values(60,'computer signal processing',8,5,1,1,1);
insert into courses values(61,'design and analysis of algorithms',2,4,1,1,1);
insert into courses values(62,'design and analysis of algorithms LAB',1,3,1,2,2);

insert into courses values(63,'digial electronics',9,5,1,1,1);
insert into courses values(64,'satellite communiction',12,5,1,1,1);
insert into courses values(65,'chip structures',6,4,1,1,1);
insert into courses values(66,'digital electronics LAB',9,3,1,2,3);

insert into courses values(67,'powerful electronics',10,5,1,1,1);
insert into courses values(68,'circuit design',3,5,1,1,1);
insert into courses values(69,'circuit architecture',1,4,1,1,1);
insert into courses values(70,'powerful electronics LAB',10,3,1,2,4);

insert into courses values(71,'pulley construction',13,5,2,1,1);
insert into courses values(72,'mechanical movements',14,5,2,1,1);
insert into courses values(73,'mechanics of machines',15,4,2,1,1);
insert into courses values(74,'mechanical movements LAB',14,3,2,2,5);

insert into courses values(75,'construction equipments',16,5,3,1,1);
insert into courses values(76,'clay',17,5,3,1,1);
insert into courses values(77,'cement',18,4,3,1,1);
insert into courses values(78,'construction equipments LAB',16,3,3,2,7);

insert into courses values(1,'DBMS theory',1,4,1,1,1);
insert into courses values(2,'CN theory',4,4,1,1,1);
insert into courses values(3,'CD theory',3,4,1,1,1);
insert into courses values(4,'COA theory',5,4,1,1,1);
insert into courses values(5,'OS theory',6,4,1,1,1);
insert into courses values(6,'FLAT theory',7,4,1,1,1);
insert into courses values(7,'AA theory',2,4,1,1,1);
insert into courses values(8,'STV theory',7,4,1,1,1);
insert into courses values(9,'NSS theory',1,4,1,1,1);
insert into courses values(10,'AGT theory',2,3,1,1,1);
insert into courses values(11,'Microprocessor theory',8,3,1,1,1);
insert into courses values(12,'PE theory',9,3,1,1,1);
insert into courses values(13,'EPTD theory',10,3,1,1,1);
insert into courses values(14,'ATD theory',3,3,1,1,1);
insert into courses values(15,'IVP theory',2,3,1,1,1);
insert into courses values(16,'EPQ theory',5,5,1,1,1);
insert into courses values(17,'SMPC theory',7,5,1,1,1);
insert into courses values(18,'TAED theory',5,3,1,1,1);
insert into courses values(19,'IMP theory',11,4,2,1,1);
insert into courses values(20,'MS theory',12,4,2,1,1);
insert into courses values(21,'RDS theory',13,4,2,1,1);
insert into courses values(22,'FDS theory',14,3,2,1,1);
--insert into courses values(23,'NLM theory',15,4,2,1,1);
insert into courses values(24,'ABC theory',14,4,2,1,1);
insert into courses values(25,'NMP theory',16,4,3,1,1);
insert into courses values(26,'KS theory',17,4,3,1,1);
insert into courses values(27,'LDS theory',18,3,3,1,1);
insert into courses values(28,'UDS theory',19,5,3,1,1);
insert into courses values(29,'KLM theory',20,4,3,1,1);
insert into courses values(30,'LPC theory',18,4,3,1,1);
insert into courses values(31,'DBMS LAB',1,3,1,2,2);
insert into courses values(32,'ATD LAB',8,3,1,2,3);
insert into courses values(33,'OS LAB',3,3,1,2,2);
insert into courses values(34,'Microprocessor LAB',9,3,1,2,3);
insert into courses values(35,'CN LAB',2,3,1,2,2);
insert into courses values(36,'PE LAB',7,3,1,2,4);
insert into courses values(37,'EZ LAB',6,3,1,2,4);
insert into courses values(38,'ZMP LAB',3,3,2,2,5);
insert into courses values(39,'RDS LAB',2,3,3,2,7);
insert into courses values(40,'FDS LAB',7,3,2,2,6);
insert into courses values(41,'Survey LAB',6,3,3,2,7);
insert into courses values(42,'LDM theory',18,3,2,1,1);
insert into courses values(43,'UDXS theory',19,5,2,1,1);
insert into courses values(44,'KLMN theory',19,4,2,1,1);
insert into courses values(45,'LPGC theory',14,4,2,1,1);
insert into courses values(46,'PQR theory',11,3,3,1,1);
insert into courses values(47,'ST theory',12,5,3,1,1);
insert into courses values(48,'MN theory',20,4,3,1,1);
insert into courses values(49,'GC theory',13,4,3,1,1);
insert into courses values(50,'ZC theory',18,4,1,1,1);

insert into attendance values('19CS01001',51,0,0);
insert into attendance values('19CS01002',51,0,0);
insert into attendance values('19CS01003',51,0,0);
insert into attendance values('19CS01004',51,0,0);
insert into attendance values('19CS01005',51,0,0);
insert into attendance values('19CS01006',51,0,0);
insert into attendance values('19CS01007',51,0,0);
insert into attendance values('19CS01008',51,0,0);
insert into attendance values('19CS01009',51,0,0);
insert into attendance values('19CS01010',51,0,0);
insert into attendance values('19CS01001',52,0,0);
insert into attendance values('19CS01002',52,0,0);
insert into attendance values('19CS01003',52,0,0);
insert into attendance values('19CS01004',52,0,0);
insert into attendance values('19CS01005',52,0,0);
insert into attendance values('19CS01006',52,0,0);
insert into attendance values('19CS01007',52,0,0);
insert into attendance values('19CS01008',52,0,0);
insert into attendance values('19CS01009',52,0,0);
insert into attendance values('19CS01010',52,0,0);
insert into attendance values('19CS01001',53,0,0);
insert into attendance values('19CS01002',53,0,0);
insert into attendance values('19CS01003',53,0,0);
insert into attendance values('19CS01004',53,0,0);
insert into attendance values('19CS01005',53,0,0);
insert into attendance values('19CS01006',53,0,0);
insert into attendance values('19CS01007',53,0,0);
insert into attendance values('19CS01008',53,0,0);
insert into attendance values('19CS01009',53,0,0);
insert into attendance values('19CS01010',53,0,0);
insert into attendance values('19CS01001',54,0,0);
insert into attendance values('19CS01002',54,0,0);
insert into attendance values('19CS01003',54,0,0);
insert into attendance values('19CS01004',54,0,0);
insert into attendance values('19CS01005',54,0,0);
insert into attendance values('19CS01006',54,0,0);
insert into attendance values('19CS01007',54,0,0);
insert into attendance values('19CS01008',54,0,0);
insert into attendance values('19CS01009',54,0,0);
insert into attendance values('19CS01010',54,0,0);
insert into attendance values('19CS01001',55,0,0);
insert into attendance values('19CS01002',55,0,0);
insert into attendance values('19CS01003',55,0,0);
insert into attendance values('19CS01004',55,0,0);
insert into attendance values('19CS01005',55,0,0);
insert into attendance values('19CS01006',55,0,0);
insert into attendance values('19CS01007',55,0,0);
insert into attendance values('19CS01008',55,0,0);
insert into attendance values('19CS01009',55,0,0);
insert into attendance values('19CS01010',55,0,0);
insert into attendance values('19CS01001',56,0,0);
insert into attendance values('19CS01002',56,0,0);
insert into attendance values('19CS01003',56,0,0);
insert into attendance values('19CS01004',56,0,0);
insert into attendance values('19CS01005',56,0,0);
insert into attendance values('19CS01006',56,0,0);
insert into attendance values('19CS01007',56,0,0);
insert into attendance values('19CS01008',56,0,0);
insert into attendance values('19CS01009',56,0,0);
insert into attendance values('19CS01010',56,0,0);
insert into attendance values('19CS01001',57,0,0);
insert into attendance values('19CS01002',57,0,0);
insert into attendance values('19CS01003',57,0,0);
insert into attendance values('19CS01004',57,0,0);
insert into attendance values('19CS01005',57,0,0);
insert into attendance values('19CS01006',57,0,0);
insert into attendance values('19CS01007',57,0,0);
insert into attendance values('19CS01008',57,0,0);
insert into attendance values('19CS01009',57,0,0);
insert into attendance values('19CS01010',57,0,0);
insert into attendance values('19CS01001',58,0,0);
insert into attendance values('19CS01002',58,0,0);
insert into attendance values('19CS01003',58,0,0);
insert into attendance values('19CS01004',58,0,0);
insert into attendance values('19CS01005',58,0,0);
insert into attendance values('19CS01006',58,0,0);
insert into attendance values('19CS01007',58,0,0);
insert into attendance values('19CS01008',58,0,0);
insert into attendance values('19CS01009',58,0,0);
insert into attendance values('19CS01010',58,0,0);

insert into attendance values('19EC01001',51,0,0);
insert into attendance values('19EC01002',51,0,0);
insert into attendance values('19EC01003',51,0,0);
insert into attendance values('19EC01004',51,0,0);
insert into attendance values('19EC01005',51,0,0);
insert into attendance values('19EC01006',51,0,0);
insert into attendance values('19EC01007',51,0,0);
insert into attendance values('19EC01008',51,0,0);
insert into attendance values('19EC01009',51,0,0);
insert into attendance values('19EC01010',51,0,0);
insert into attendance values('19EC01001',52,0,0);
insert into attendance values('19EC01002',52,0,0);
insert into attendance values('19EC01003',52,0,0);
insert into attendance values('19EC01004',52,0,0);
insert into attendance values('19EC01005',52,0,0);
insert into attendance values('19EC01006',52,0,0);
insert into attendance values('19EC01007',52,0,0);
insert into attendance values('19EC01008',52,0,0);
insert into attendance values('19EC01009',52,0,0);
insert into attendance values('19EC01010',52,0,0);
insert into attendance values('19EC01001',53,0,0);
insert into attendance values('19EC01002',53,0,0);
insert into attendance values('19EC01003',53,0,0);
insert into attendance values('19EC01004',53,0,0);
insert into attendance values('19EC01005',53,0,0);
insert into attendance values('19EC01006',53,0,0);
insert into attendance values('19EC01007',53,0,0);
insert into attendance values('19EC01008',53,0,0);
insert into attendance values('19EC01009',53,0,0);
insert into attendance values('19EC01010',53,0,0);
insert into attendance values('19EC01001',54,0,0);
insert into attendance values('19EC01002',54,0,0);
insert into attendance values('19EC01003',54,0,0);
insert into attendance values('19EC01004',54,0,0);
insert into attendance values('19EC01005',54,0,0);
insert into attendance values('19EC01006',54,0,0);
insert into attendance values('19EC01007',54,0,0);
insert into attendance values('19EC01008',54,0,0);
insert into attendance values('19EC01009',54,0,0);
insert into attendance values('19EC01010',54,0,0);
insert into attendance values('19EC01001',55,0,0);
insert into attendance values('19EC01002',55,0,0);
insert into attendance values('19EC01003',55,0,0);
insert into attendance values('19EC01004',55,0,0);
insert into attendance values('19EC01005',55,0,0);
insert into attendance values('19EC01006',55,0,0);
insert into attendance values('19EC01007',55,0,0);
insert into attendance values('19EC01008',55,0,0);
insert into attendance values('19EC01009',55,0,0);
insert into attendance values('19EC01010',55,0,0);
insert into attendance values('19EC01001',56,0,0);
insert into attendance values('19EC01002',56,0,0);
insert into attendance values('19EC01003',56,0,0);
insert into attendance values('19EC01004',56,0,0);
insert into attendance values('19EC01005',56,0,0);
insert into attendance values('19EC01006',56,0,0);
insert into attendance values('19EC01007',56,0,0);
insert into attendance values('19EC01008',56,0,0);
insert into attendance values('19EC01009',56,0,0);
insert into attendance values('19EC01010',56,0,0);
insert into attendance values('19EC01001',57,0,0);
insert into attendance values('19EC01002',57,0,0);
insert into attendance values('19EC01003',57,0,0);
insert into attendance values('19EC01004',57,0,0);
insert into attendance values('19EC01005',57,0,0);
insert into attendance values('19EC01006',57,0,0);
insert into attendance values('19EC01007',57,0,0);
insert into attendance values('19EC01008',57,0,0);
insert into attendance values('19EC01009',57,0,0);
insert into attendance values('19EC01010',57,0,0);
insert into attendance values('19EC01001',58,0,0);
insert into attendance values('19EC01002',58,0,0);
insert into attendance values('19EC01003',58,0,0);
insert into attendance values('19EC01004',58,0,0);
insert into attendance values('19EC01005',58,0,0);
insert into attendance values('19EC01006',58,0,0);
insert into attendance values('19EC01007',58,0,0);
insert into attendance values('19EC01008',58,0,0);
insert into attendance values('19EC01009',58,0,0);
insert into attendance values('19EC01010',58,0,0);

insert into attendance values('19EE01001',51,0,0);
insert into attendance values('19EE01002',51,0,0);
insert into attendance values('19EE01003',51,0,0);
insert into attendance values('19EE01004',51,0,0);
insert into attendance values('19EE01005',51,0,0);
insert into attendance values('19EE01006',51,0,0);
insert into attendance values('19EE01007',51,0,0);
insert into attendance values('19EE01008',51,0,0);
insert into attendance values('19EE01009',51,0,0);
insert into attendance values('19EE01010',51,0,0);
insert into attendance values('19EE01001',52,0,0);
insert into attendance values('19EE01002',52,0,0);
insert into attendance values('19EE01003',52,0,0);
insert into attendance values('19EE01004',52,0,0);
insert into attendance values('19EE01005',52,0,0);
insert into attendance values('19EE01006',52,0,0);
insert into attendance values('19EE01007',52,0,0);
insert into attendance values('19EE01008',52,0,0);
insert into attendance values('19EE01009',52,0,0);
insert into attendance values('19EE01010',52,0,0);
insert into attendance values('19EE01001',53,0,0);
insert into attendance values('19EE01002',53,0,0);
insert into attendance values('19EE01003',53,0,0);
insert into attendance values('19EE01004',53,0,0);
insert into attendance values('19EE01005',53,0,0);
insert into attendance values('19EE01006',53,0,0);
insert into attendance values('19EE01007',53,0,0);
insert into attendance values('19EE01008',53,0,0);
insert into attendance values('19EE01009',53,0,0);
insert into attendance values('19EE01010',53,0,0);
insert into attendance values('19EE01001',54,0,0);
insert into attendance values('19EE01002',54,0,0);
insert into attendance values('19EE01003',54,0,0);
insert into attendance values('19EE01004',54,0,0);
insert into attendance values('19EE01005',54,0,0);
insert into attendance values('19EE01006',54,0,0);
insert into attendance values('19EE01007',54,0,0);
insert into attendance values('19EE01008',54,0,0);
insert into attendance values('19EE01009',54,0,0);
insert into attendance values('19EE01010',54,0,0);
insert into attendance values('19EE01001',55,0,0);
insert into attendance values('19EE01002',55,0,0);
insert into attendance values('19EE01003',55,0,0);
insert into attendance values('19EE01004',55,0,0);
insert into attendance values('19EE01005',55,0,0);
insert into attendance values('19EE01006',55,0,0);
insert into attendance values('19EE01007',55,0,0);
insert into attendance values('19EE01008',55,0,0);
insert into attendance values('19EE01009',55,0,0);
insert into attendance values('19EE01010',55,0,0);
insert into attendance values('19EE01001',56,0,0);
insert into attendance values('19EE01002',56,0,0);
insert into attendance values('19EE01003',56,0,0);
insert into attendance values('19EE01004',56,0,0);
insert into attendance values('19EE01005',56,0,0);
insert into attendance values('19EE01006',56,0,0);
insert into attendance values('19EE01007',56,0,0);
insert into attendance values('19EE01008',56,0,0);
insert into attendance values('19EE01009',56,0,0);
insert into attendance values('19EE01010',56,0,0);
insert into attendance values('19EE01001',57,0,0);
insert into attendance values('19EE01002',57,0,0);
insert into attendance values('19EE01003',57,0,0);
insert into attendance values('19EE01004',57,0,0);
insert into attendance values('19EE01005',57,0,0);
insert into attendance values('19EE01006',57,0,0);
insert into attendance values('19EE01007',57,0,0);
insert into attendance values('19EE01008',57,0,0);
insert into attendance values('19EE01009',57,0,0);
insert into attendance values('19EE01010',57,0,0);
insert into attendance values('19EE01001',58,0,0);
insert into attendance values('19EE01002',58,0,0);
insert into attendance values('19EE01003',58,0,0);
insert into attendance values('19EE01004',58,0,0);
insert into attendance values('19EE01005',58,0,0);
insert into attendance values('19EE01006',58,0,0);
insert into attendance values('19EE01007',58,0,0);
insert into attendance values('19EE01008',58,0,0);
insert into attendance values('19EE01009',58,0,0);
insert into attendance values('19EE01010',58,0,0);

insert into attendance values('19ME01001',51,0,0);
insert into attendance values('19ME01002',51,0,0);
insert into attendance values('19ME01003',51,0,0);
insert into attendance values('19ME01004',51,0,0);
insert into attendance values('19ME01005',51,0,0);
insert into attendance values('19ME01006',51,0,0);
insert into attendance values('19ME01007',51,0,0);
insert into attendance values('19ME01008',51,0,0);
insert into attendance values('19ME01009',51,0,0);
insert into attendance values('19ME01010',51,0,0);
insert into attendance values('19ME01001',52,0,0);
insert into attendance values('19ME01002',52,0,0);
insert into attendance values('19ME01003',52,0,0);
insert into attendance values('19ME01004',52,0,0);
insert into attendance values('19ME01005',52,0,0);
insert into attendance values('19ME01006',52,0,0);
insert into attendance values('19ME01007',52,0,0);
insert into attendance values('19ME01008',52,0,0);
insert into attendance values('19ME01009',52,0,0);
insert into attendance values('19ME01010',52,0,0);
insert into attendance values('19ME01001',53,0,0);
insert into attendance values('19ME01002',53,0,0);
insert into attendance values('19ME01003',53,0,0);
insert into attendance values('19ME01004',53,0,0);
insert into attendance values('19ME01005',53,0,0);
insert into attendance values('19ME01006',53,0,0);
insert into attendance values('19ME01007',53,0,0);
insert into attendance values('19ME01008',53,0,0);
insert into attendance values('19ME01009',53,0,0);
insert into attendance values('19ME01010',53,0,0);
insert into attendance values('19ME01001',54,0,0);
insert into attendance values('19ME01002',54,0,0);
insert into attendance values('19ME01003',54,0,0);
insert into attendance values('19ME01004',54,0,0);
insert into attendance values('19ME01005',54,0,0);
insert into attendance values('19ME01006',54,0,0);
insert into attendance values('19ME01007',54,0,0);
insert into attendance values('19ME01008',54,0,0);
insert into attendance values('19ME01009',54,0,0);
insert into attendance values('19ME01010',54,0,0);
insert into attendance values('19ME01001',55,0,0);
insert into attendance values('19ME01002',55,0,0);
insert into attendance values('19ME01003',55,0,0);
insert into attendance values('19ME01004',55,0,0);
insert into attendance values('19ME01005',55,0,0);
insert into attendance values('19ME01006',55,0,0);
insert into attendance values('19ME01007',55,0,0);
insert into attendance values('19ME01008',55,0,0);
insert into attendance values('19ME01009',55,0,0);
insert into attendance values('19ME01010',55,0,0);
insert into attendance values('19ME01001',56,0,0);
insert into attendance values('19ME01002',56,0,0);
insert into attendance values('19ME01003',56,0,0);
insert into attendance values('19ME01004',56,0,0);
insert into attendance values('19ME01005',56,0,0);
insert into attendance values('19ME01006',56,0,0);
insert into attendance values('19ME01007',56,0,0);
insert into attendance values('19ME01008',56,0,0);
insert into attendance values('19ME01009',56,0,0);
insert into attendance values('19ME01010',56,0,0);
insert into attendance values('19ME01001',57,0,0);
insert into attendance values('19ME01002',57,0,0);
insert into attendance values('19ME01003',57,0,0);
insert into attendance values('19ME01004',57,0,0);
insert into attendance values('19ME01005',57,0,0);
insert into attendance values('19ME01006',57,0,0);
insert into attendance values('19ME01007',57,0,0);
insert into attendance values('19ME01008',57,0,0);
insert into attendance values('19ME01009',57,0,0);
insert into attendance values('19ME01010',57,0,0);
insert into attendance values('19ME01001',58,0,0);
insert into attendance values('19ME01002',58,0,0);
insert into attendance values('19ME01003',58,0,0);
insert into attendance values('19ME01004',58,0,0);
insert into attendance values('19ME01005',58,0,0);
insert into attendance values('19ME01006',58,0,0);
insert into attendance values('19ME01007',58,0,0);
insert into attendance values('19ME01008',58,0,0);
insert into attendance values('19ME01009',58,0,0);
insert into attendance values('19ME01010',58,0,0);


insert into attendance values('19CE01001',51,0,0);
insert into attendance values('19CE01002',51,0,0);
insert into attendance values('19CE01003',51,0,0);
insert into attendance values('19CE01004',51,0,0);
insert into attendance values('19CE01005',51,0,0);
insert into attendance values('19CE01006',51,0,0);
insert into attendance values('19CE01007',51,0,0);
insert into attendance values('19CE01008',51,0,0);
insert into attendance values('19CE01009',51,0,0);
insert into attendance values('19CE01010',51,0,0);
insert into attendance values('19CE01001',52,0,0);
insert into attendance values('19CE01002',52,0,0);
insert into attendance values('19CE01003',52,0,0);
insert into attendance values('19CE01004',52,0,0);
insert into attendance values('19CE01005',52,0,0);
insert into attendance values('19CE01006',52,0,0);
insert into attendance values('19CE01007',52,0,0);
insert into attendance values('19CE01008',52,0,0);
insert into attendance values('19CE01009',52,0,0);
insert into attendance values('19CE01010',52,0,0);
insert into attendance values('19CE01001',53,0,0);
insert into attendance values('19CE01002',53,0,0);
insert into attendance values('19CE01003',53,0,0);
insert into attendance values('19CE01004',53,0,0);
insert into attendance values('19CE01005',53,0,0);
insert into attendance values('19CE01006',53,0,0);
insert into attendance values('19CE01007',53,0,0);
insert into attendance values('19CE01008',53,0,0);
insert into attendance values('19CE01009',53,0,0);
insert into attendance values('19CE01010',53,0,0);
insert into attendance values('19CE01001',54,0,0);
insert into attendance values('19CE01002',54,0,0);
insert into attendance values('19CE01003',54,0,0);
insert into attendance values('19CE01004',54,0,0);
insert into attendance values('19CE01005',54,0,0);
insert into attendance values('19CE01006',54,0,0);
insert into attendance values('19CE01007',54,0,0);
insert into attendance values('19CE01008',54,0,0);
insert into attendance values('19CE01009',54,0,0);
insert into attendance values('19CE01010',54,0,0);
insert into attendance values('19CE01001',55,0,0);
insert into attendance values('19CE01002',55,0,0);
insert into attendance values('19CE01003',55,0,0);
insert into attendance values('19CE01004',55,0,0);
insert into attendance values('19CE01005',55,0,0);
insert into attendance values('19CE01006',55,0,0);
insert into attendance values('19CE01007',55,0,0);
insert into attendance values('19CE01008',55,0,0);
insert into attendance values('19CE01009',55,0,0);
insert into attendance values('19CE01010',55,0,0);
insert into attendance values('19CE01001',56,0,0);
insert into attendance values('19CE01002',56,0,0);
insert into attendance values('19CE01003',56,0,0);
insert into attendance values('19CE01004',56,0,0);
insert into attendance values('19CE01005',56,0,0);
insert into attendance values('19CE01006',56,0,0);
insert into attendance values('19CE01007',56,0,0);
insert into attendance values('19CE01008',56,0,0);
insert into attendance values('19CE01009',56,0,0);
insert into attendance values('19CE01010',56,0,0);
insert into attendance values('19CE01001',57,0,0);
insert into attendance values('19CE01002',57,0,0);
insert into attendance values('19CE01003',57,0,0);
insert into attendance values('19CE01004',57,0,0);
insert into attendance values('19CE01005',57,0,0);
insert into attendance values('19CE01006',57,0,0);
insert into attendance values('19CE01007',57,0,0);
insert into attendance values('19CE01008',57,0,0);
insert into attendance values('19CE01009',57,0,0);
insert into attendance values('19CE01010',57,0,0);
insert into attendance values('19CE01001',58,0,0);
insert into attendance values('19CE01002',58,0,0);
insert into attendance values('19CE01003',58,0,0);
insert into attendance values('19CE01004',58,0,0);
insert into attendance values('19CE01005',58,0,0);
insert into attendance values('19CE01006',58,0,0);
insert into attendance values('19CE01007',58,0,0);
insert into attendance values('19CE01008',58,0,0);
insert into attendance values('19CE01009',58,0,0);
insert into attendance values('19CE01010',58,0,0);

insert into attendance values('18CS01001',59,0,0);
insert into attendance values('18CS01002',59,0,0);
insert into attendance values('18CS01003',59,0,0);
insert into attendance values('18CS01004',59,0,0);
insert into attendance values('18CS01005',59,0,0);
insert into attendance values('18CS01006',59,0,0);
insert into attendance values('18CS01007',59,0,0);
insert into attendance values('18CS01008',59,0,0);
insert into attendance values('18CS01009',59,0,0);
insert into attendance values('18CS01010',59,0,0);
insert into attendance values('18CS01001',60,0,0);
insert into attendance values('18CS01002',60,0,0);
insert into attendance values('18CS01003',60,0,0);
insert into attendance values('18CS01004',60,0,0);
insert into attendance values('18CS01005',60,0,0);
insert into attendance values('18CS01006',60,0,0);
insert into attendance values('18CS01007',60,0,0);
insert into attendance values('18CS01008',60,0,0);
insert into attendance values('18CS01009',60,0,0);
insert into attendance values('18CS01010',60,0,0);
insert into attendance values('18CS01001',61,0,0);
insert into attendance values('18CS01002',61,0,0);
insert into attendance values('18CS01003',61,0,0);
insert into attendance values('18CS01004',61,0,0);
insert into attendance values('18CS01005',61,0,0);
insert into attendance values('18CS01006',61,0,0);
insert into attendance values('18CS01007',61,0,0);
insert into attendance values('18CS01008',61,0,0);
insert into attendance values('18CS01009',61,0,0);
insert into attendance values('18CS01010',61,0,0);
insert into attendance values('18CS01001',62,0,0);
insert into attendance values('18CS01002',62,0,0);
insert into attendance values('18CS01003',62,0,0);
insert into attendance values('18CS01004',62,0,0);
insert into attendance values('18CS01005',62,0,0);
insert into attendance values('18CS01006',62,0,0);
insert into attendance values('18CS01007',62,0,0);
insert into attendance values('18CS01008',62,0,0);
insert into attendance values('18CS01009',62,0,0);
insert into attendance values('18CS01010',62,0,0);

insert into attendance values('18EC01001',63,0,0);
insert into attendance values('18EC01002',63,0,0);
insert into attendance values('18EC01003',63,0,0);
insert into attendance values('18EC01004',63,0,0);
insert into attendance values('18EC01005',63,0,0);
insert into attendance values('18EC01006',63,0,0);
insert into attendance values('18EC01007',63,0,0);
insert into attendance values('18EC01008',63,0,0);
insert into attendance values('18EC01009',63,0,0);
insert into attendance values('18EC01010',63,0,0);
insert into attendance values('18EC01001',64,0,0);
insert into attendance values('18EC01002',64,0,0);
insert into attendance values('18EC01003',64,0,0);
insert into attendance values('18EC01004',64,0,0);
insert into attendance values('18EC01005',64,0,0);
insert into attendance values('18EC01006',64,0,0);
insert into attendance values('18EC01007',64,0,0);
insert into attendance values('18EC01008',64,0,0);
insert into attendance values('18EC01009',64,0,0);
insert into attendance values('18EC01010',64,0,0);
insert into attendance values('18EC01001',65,0,0);
insert into attendance values('18EC01002',65,0,0);
insert into attendance values('18EC01003',65,0,0);
insert into attendance values('18EC01004',65,0,0);
insert into attendance values('18EC01005',65,0,0);
insert into attendance values('18EC01006',65,0,0);
insert into attendance values('18EC01007',65,0,0);
insert into attendance values('18EC01008',65,0,0);
insert into attendance values('18EC01009',65,0,0);
insert into attendance values('18EC01010',65,0,0);
insert into attendance values('18EC01001',66,0,0);
insert into attendance values('18EC01002',66,0,0);
insert into attendance values('18EC01003',66,0,0);
insert into attendance values('18EC01004',66,0,0);
insert into attendance values('18EC01005',66,0,0);
insert into attendance values('18EC01006',66,0,0);
insert into attendance values('18EC01007',66,0,0);
insert into attendance values('18EC01008',66,0,0);
insert into attendance values('18EC01009',66,0,0);
insert into attendance values('18EC01010',66,0,0);

insert into attendance values('18EE01001',67,0,0);
insert into attendance values('18EE01002',67,0,0);
insert into attendance values('18EE01003',67,0,0);
insert into attendance values('18EE01004',67,0,0);
insert into attendance values('18EE01005',67,0,0);
insert into attendance values('18EE01006',67,0,0);
insert into attendance values('18EE01007',67,0,0);
insert into attendance values('18EE01008',67,0,0);
insert into attendance values('18EE01009',67,0,0);
insert into attendance values('18EE01010',67,0,0);
insert into attendance values('18EE01001',68,0,0);
insert into attendance values('18EE01002',68,0,0);
insert into attendance values('18EE01003',68,0,0);
insert into attendance values('18EE01004',68,0,0);
insert into attendance values('18EE01005',68,0,0);
insert into attendance values('18EE01006',68,0,0);
insert into attendance values('18EE01007',68,0,0);
insert into attendance values('18EE01008',68,0,0);
insert into attendance values('18EE01009',68,0,0);
insert into attendance values('18EE01010',68,0,0);
insert into attendance values('18EE01001',69,0,0);
insert into attendance values('18EE01002',69,0,0);
insert into attendance values('18EE01003',69,0,0);
insert into attendance values('18EE01004',69,0,0);
insert into attendance values('18EE01005',69,0,0);
insert into attendance values('18EE01006',69,0,0);
insert into attendance values('18EE01007',69,0,0);
insert into attendance values('18EE01008',69,0,0);
insert into attendance values('18EE01009',69,0,0);
insert into attendance values('18EE01010',69,0,0);
insert into attendance values('18EE01001',70,0,0);
insert into attendance values('18EE01002',70,0,0);
insert into attendance values('18EE01003',70,0,0);
insert into attendance values('18EE01004',70,0,0);
insert into attendance values('18EE01005',70,0,0);
insert into attendance values('18EE01006',70,0,0);
insert into attendance values('18EE01007',70,0,0);
insert into attendance values('18EE01008',70,0,0);
insert into attendance values('18EE01009',70,0,0);
insert into attendance values('18EE01010',70,0,0);

insert into attendance values('18ME01001',71,0,0);
insert into attendance values('18ME01002',71,0,0);
insert into attendance values('18ME01003',71,0,0);
insert into attendance values('18ME01004',71,0,0);
insert into attendance values('18ME01005',71,0,0);
insert into attendance values('18ME01006',71,0,0);
insert into attendance values('18ME01007',71,0,0);
insert into attendance values('18ME01008',71,0,0);
insert into attendance values('18ME01009',71,0,0);
insert into attendance values('18ME01010',71,0,0);
insert into attendance values('18ME01001',72,0,0);
insert into attendance values('18ME01002',72,0,0);
insert into attendance values('18ME01003',72,0,0);
insert into attendance values('18ME01004',72,0,0);
insert into attendance values('18ME01005',72,0,0);
insert into attendance values('18ME01006',72,0,0);
insert into attendance values('18ME01007',72,0,0);
insert into attendance values('18ME01008',72,0,0);
insert into attendance values('18ME01009',72,0,0);
insert into attendance values('18ME01010',72,0,0);
insert into attendance values('18ME01001',73,0,0);
insert into attendance values('18ME01002',73,0,0);
insert into attendance values('18ME01003',73,0,0);
insert into attendance values('18ME01004',73,0,0);
insert into attendance values('18ME01005',73,0,0);
insert into attendance values('18ME01006',73,0,0);
insert into attendance values('18ME01007',73,0,0);
insert into attendance values('18ME01008',73,0,0);
insert into attendance values('18ME01009',73,0,0);
insert into attendance values('18ME01010',73,0,0);
insert into attendance values('18ME01001',74,0,0);
insert into attendance values('18ME01002',74,0,0);
insert into attendance values('18ME01003',74,0,0);
insert into attendance values('18ME01004',74,0,0);
insert into attendance values('18ME01005',74,0,0);
insert into attendance values('18ME01006',74,0,0);
insert into attendance values('18ME01007',74,0,0);
insert into attendance values('18ME01008',74,0,0);
insert into attendance values('18ME01009',74,0,0);
insert into attendance values('18ME01010',74,0,0);


insert into attendance values('18CE01001',75,0,0);
insert into attendance values('18CE01002',75,0,0);
insert into attendance values('18CE01003',75,0,0);
insert into attendance values('18CE01004',75,0,0);
insert into attendance values('18CE01005',75,0,0);
insert into attendance values('18CE01006',75,0,0);
insert into attendance values('18CE01007',75,0,0);
insert into attendance values('18CE01008',75,0,0);
insert into attendance values('18CE01009',75,0,0);
insert into attendance values('18CE01010',75,0,0);
insert into attendance values('18CE01001',76,0,0);
insert into attendance values('18CE01002',76,0,0);
insert into attendance values('18CE01003',76,0,0);
insert into attendance values('18CE01004',76,0,0);
insert into attendance values('18CE01005',76,0,0);
insert into attendance values('18CE01006',76,0,0);
insert into attendance values('18CE01007',76,0,0);
insert into attendance values('18CE01008',76,0,0);
insert into attendance values('18CE01009',76,0,0);
insert into attendance values('18CE01010',76,0,0);
insert into attendance values('18CE01001',77,0,0);
insert into attendance values('18CE01002',77,0,0);
insert into attendance values('18CE01003',77,0,0);
insert into attendance values('18CE01004',77,0,0);
insert into attendance values('18CE01005',77,0,0);
insert into attendance values('18CE01006',77,0,0);
insert into attendance values('18CE01007',77,0,0);
insert into attendance values('18CE01008',77,0,0);
insert into attendance values('18CE01009',77,0,0);
insert into attendance values('18CE01010',77,0,0);
insert into attendance values('18CE01001',78,0,0);
insert into attendance values('18CE01002',78,0,0);
insert into attendance values('18CE01003',78,0,0);
insert into attendance values('18CE01004',78,0,0);
insert into attendance values('18CE01005',78,0,0);
insert into attendance values('18CE01006',78,0,0);
insert into attendance values('18CE01007',78,0,0);
insert into attendance values('18CE01008',78,0,0);
insert into attendance values('18CE01009',78,0,0);
insert into attendance values('18CE01010',78,0,0);

insert into attendance values('17CS01001',1,0,0);
insert into attendance values('17CS01002',1,0,0);
insert into attendance values('17CS01003',1,0,0);
insert into attendance values('17CS01004',1,0,0);
insert into attendance values('17CS01005',1,0,0);
insert into attendance values('17CS01006',1,0,0);
insert into attendance values('17CS01007',1,0,0);
insert into attendance values('17CS01020',1,0,0);
insert into attendance values('17CS01025',1,0,0);
insert into attendance values('17CS01028',1,0,0);
insert into attendance values('17CS01001',2,0,0);
insert into attendance values('17CS01002',2,0,0);
insert into attendance values('17CS01003',2,0,0);
insert into attendance values('17CS01004',2,0,0);
insert into attendance values('17CS01005',2,0,0);
insert into attendance values('17CS01006',2,0,0);
insert into attendance values('17CS01007',2,0,0);
insert into attendance values('17CS01025',2,0,0);
insert into attendance values('17CS01028',2,0,0);
insert into attendance values('17CS01020',2,0,0);
insert into attendance values('17CS01001',3,0,0);
insert into attendance values('17CS01002',3,0,0);
insert into attendance values('17CS01003',3,0,0);
insert into attendance values('17CS01004',3,0,0);
insert into attendance values('17CS01005',3,0,0);
insert into attendance values('17CS01006',3,0,0);
insert into attendance values('17CS01007',3,0,0);
insert into attendance values('17CS01025',3,0,0);
insert into attendance values('17CS01028',3,0,0);
insert into attendance values('17CS01020',3,0,0);
insert into attendance values('17CS01001',15,0,0);
insert into attendance values('17CS01002',15,0,0);
insert into attendance values('17CS01003',15,0,0);
insert into attendance values('17CS01004',15,0,0);
insert into attendance values('17CS01005',15,0,0);
insert into attendance values('17CS01006',15,0,0);
insert into attendance values('17CS01007',15,0,0);
insert into attendance values('17CS01025',15,0,0);
insert into attendance values('17CS01028',15,0,0);
insert into attendance values('17CS01020',15,0,0);
insert into attendance values('17CS01001',31,0,0);
insert into attendance values('17CS01002',31,0,0);
insert into attendance values('17CS01003',31,0,0);
insert into attendance values('17CS01004',31,0,0);
insert into attendance values('17CS01005',31,0,0);
insert into attendance values('17CS01006',31,0,0);
insert into attendance values('17CS01007',31,0,0);
insert into attendance values('17CS01025',31,0,0);
insert into attendance values('17CS01028',31,0,0);
insert into attendance values('17CS01020',31,0,0);

insert into attendance values('17EC01001',7,0,0);
insert into attendance values('17EC01002',7,0,0);
insert into attendance values('17EC01003',7,0,0);
insert into attendance values('17EC01004',7,0,0);
insert into attendance values('17EC01005',7,0,0);
insert into attendance values('17EC01006',7,0,0);
insert into attendance values('17EC01020',7,0,0);
insert into attendance values('17EC01021',7,0,0);
insert into attendance values('17EC01022',7,0,0);
insert into attendance values('17EC01023',7,0,0);
insert into attendance values('17EC01001',11,0,0);
insert into attendance values('17EC01002',11,0,0);
insert into attendance values('17EC01003',11,0,0);
insert into attendance values('17EC01004',11,0,0);
insert into attendance values('17EC01005',11,0,0);
insert into attendance values('17EC01006',11,0,0);
insert into attendance values('17EC01020',11,0,0);
insert into attendance values('17EC01021',11,0,0);
insert into attendance values('17EC01022',11,0,0);
insert into attendance values('17EC01023',11,0,0);
insert into attendance values('17EC01001',13,0,0);
insert into attendance values('17EC01002',13,0,0);
insert into attendance values('17EC01003',13,0,0);
insert into attendance values('17EC01004',13,0,0);
insert into attendance values('17EC01005',13,0,0);
insert into attendance values('17EC01006',13,0,0);
insert into attendance values('17EC01020',13,0,0);
insert into attendance values('17EC01021',13,0,0);
insert into attendance values('17EC01022',13,0,0);
insert into attendance values('17EC01023',13,0,0);
insert into attendance values('17EC01001',15,0,0);
insert into attendance values('17EC01002',15,0,0);
insert into attendance values('17EC01003',15,0,0);
insert into attendance values('17EC01004',15,0,0);
insert into attendance values('17EC01005',15,0,0);
insert into attendance values('17EC01006',15,0,0);
insert into attendance values('17EC01020',15,0,0);
insert into attendance values('17EC01021',15,0,0);
insert into attendance values('17EC01022',15,0,0);
insert into attendance values('17EC01023',15,0,0);
insert into attendance values('17EC01001',34,0,0);
insert into attendance values('17EC01002',34,0,0);
insert into attendance values('17EC01003',34,0,0);
insert into attendance values('17EC01004',34,0,0);
insert into attendance values('17EC01005',34,0,0);
insert into attendance values('17EC01006',34,0,0);
insert into attendance values('17EC01020',34,0,0);
insert into attendance values('17EC01021',34,0,0);
insert into attendance values('17EC01022',34,0,0);
insert into attendance values('17EC01023',34,0,0);

insert into attendance values('17EE01001',22,0,0);
insert into attendance values('17EE01002',22,0,0);
insert into attendance values('17EE01003',22,0,0);
insert into attendance values('17EE01004',22,0,0);
insert into attendance values('17EE01005',22,0,0);
insert into attendance values('17EE01006',22,0,0);
insert into attendance values('17EE01007',22,0,0);
insert into attendance values('17EE01008',22,0,0);
insert into attendance values('17EE01020',22,0,0);
insert into attendance values('17EE01021',22,0,0);
insert into attendance values('17EE01001',12,0,0);
insert into attendance values('17EE01002',12,0,0);
insert into attendance values('17EE01003',12,0,0);
insert into attendance values('17EE01004',12,0,0);
insert into attendance values('17EE01005',12,0,0);
insert into attendance values('17EE01006',12,0,0);
insert into attendance values('17EE01007',12,0,0);
insert into attendance values('17EE01008',12,0,0);
insert into attendance values('17EE01020',12,0,0);
insert into attendance values('17EE01021',12,0,0);
insert into attendance values('17EE01001',17,0,0);
insert into attendance values('17EE01002',17,0,0);
insert into attendance values('17EE01003',17,0,0);
insert into attendance values('17EE01004',17,0,0);
insert into attendance values('17EE01005',17,0,0);
insert into attendance values('17EE01006',17,0,0);
insert into attendance values('17EE01007',17,0,0);
insert into attendance values('17EE01008',17,0,0);
insert into attendance values('17EE01020',17,0,0);
insert into attendance values('17EE01021',17,0,0);
insert into attendance values('17EE01001',18,0,0);
insert into attendance values('17EE01002',18,0,0);
insert into attendance values('17EE01003',18,0,0);
insert into attendance values('17EE01004',18,0,0);
insert into attendance values('17EE01005',18,0,0);
insert into attendance values('17EE01006',18,0,0);
insert into attendance values('17EE01007',18,0,0);
insert into attendance values('17EE01008',18,0,0);
insert into attendance values('17EE01020',18,0,0);
insert into attendance values('17EE01021',18,0,0);
insert into attendance values('17EE01001',36,0,0);
insert into attendance values('17EE01002',36,0,0);
insert into attendance values('17EE01003',36,0,0);
insert into attendance values('17EE01004',36,0,0);
insert into attendance values('17EE01005',36,0,0);
insert into attendance values('17EE01006',36,0,0);
insert into attendance values('17EE01007',36,0,0);
insert into attendance values('17EE01008',36,0,0);
insert into attendance values('17EE01020',36,0,0);
insert into attendance values('17EE01021',36,0,0);

insert into attendance values('17ME01001',22,0,0);
insert into attendance values('17ME01002',22,0,0);
insert into attendance values('17ME01003',22,0,0);
insert into attendance values('17ME01004',22,0,0);
insert into attendance values('17ME01005',22,0,0);
insert into attendance values('17ME01006',22,0,0);
insert into attendance values('17ME01007',22,0,0);
insert into attendance values('17ME01008',22,0,0);
insert into attendance values('17ME01022',22,0,0);
insert into attendance values('17ME01021',22,0,0);
insert into attendance values('17ME01001',40,0,0);
insert into attendance values('17ME01002',40,0,0);
insert into attendance values('17ME01003',40,0,0);
insert into attendance values('17ME01004',40,0,0);
insert into attendance values('17ME01005',40,0,0);
insert into attendance values('17ME01006',40,0,0);
insert into attendance values('17ME01007',40,0,0);
insert into attendance values('17ME01008',40,0,0);
insert into attendance values('17ME01022',40,0,0);
insert into attendance values('17ME01021',40,0,0);
insert into attendance values('17ME01001',26,0,0);
insert into attendance values('17ME01002',26,0,0);
insert into attendance values('17ME01003',26,0,0);
insert into attendance values('17ME01004',26,0,0);
insert into attendance values('17ME01005',26,0,0);
insert into attendance values('17ME01006',26,0,0);
insert into attendance values('17ME01007',26,0,0);
insert into attendance values('17ME01008',26,0,0);
insert into attendance values('17ME01022',26,0,0);
insert into attendance values('17ME01021',26,0,0);
insert into attendance values('17ME01001',27,0,0);
insert into attendance values('17ME01002',27,0,0);
insert into attendance values('17ME01003',27,0,0);
insert into attendance values('17ME01004',27,0,0);
insert into attendance values('17ME01005',27,0,0);
insert into attendance values('17ME01006',27,0,0);
insert into attendance values('17ME01007',27,0,0);
insert into attendance values('17ME01008',27,0,0);
insert into attendance values('17ME01022',27,0,0);
insert into attendance values('17ME01021',27,0,0);
insert into attendance values('17ME01001',10,0,0);
insert into attendance values('17ME01002',10,0,0);
insert into attendance values('17ME01003',10,0,0);
insert into attendance values('17ME01004',10,0,0);
insert into attendance values('17ME01005',10,0,0);
insert into attendance values('17ME01006',10,0,0);
insert into attendance values('17ME01007',10,0,0);
insert into attendance values('17ME01008',10,0,0);
insert into attendance values('17ME01022',10,0,0);
insert into attendance values('17ME01021',10,0,0);

insert into attendance values('17CE01001',19,0,0);
insert into attendance values('17CE01002',19,0,0);
insert into attendance values('17CE01003',19,0,0);
insert into attendance values('17CE01004',19,0,0);
insert into attendance values('17CE01005',19,0,0);
insert into attendance values('17CE01006',19,0,0);
insert into attendance values('17CE01007',19,0,0);
insert into attendance values('17CE01008',19,0,0);
insert into attendance values('17CE01009',19,0,0);
insert into attendance values('17CE01010',19,0,0);
insert into attendance values('17CE01001',20,0,0);
insert into attendance values('17CE01002',20,0,0);
insert into attendance values('17CE01003',20,0,0);
insert into attendance values('17CE01004',20,0,0);
insert into attendance values('17CE01005',20,0,0);
insert into attendance values('17CE01006',20,0,0);
insert into attendance values('17CE01007',20,0,0);
insert into attendance values('17CE01008',20,0,0);
insert into attendance values('17CE01009',20,0,0);
insert into attendance values('17CE01010',20,0,0);
insert into attendance values('17CE01001',21,0,0);
insert into attendance values('17CE01002',21,0,0);
insert into attendance values('17CE01003',21,0,0);
insert into attendance values('17CE01004',21,0,0);
insert into attendance values('17CE01005',21,0,0);
insert into attendance values('17CE01006',21,0,0);
insert into attendance values('17CE01007',21,0,0);
insert into attendance values('17CE01008',21,0,0);
insert into attendance values('17CE01009',21,0,0);
insert into attendance values('17CE01010',21,0,0);
insert into attendance values('17CE01001',39,0,0);
insert into attendance values('17CE01002',39,0,0);
insert into attendance values('17CE01003',39,0,0);
insert into attendance values('17CE01004',39,0,0);
insert into attendance values('17CE01005',39,0,0);
insert into attendance values('17CE01006',39,0,0);
insert into attendance values('17CE01007',39,0,0);
insert into attendance values('17CE01008',39,0,0);
insert into attendance values('17CE01009',39,0,0);
insert into attendance values('17CE01010',39,0,0);
insert into attendance values('17CE01001',25,0,0);
insert into attendance values('17CE01002',25,0,0);
insert into attendance values('17CE01003',25,0,0);
insert into attendance values('17CE01004',25,0,0);
insert into attendance values('17CE01005',25,0,0);
insert into attendance values('17CE01006',25,0,0);
insert into attendance values('17CE01007',25,0,0);
insert into attendance values('17CE01008',25,0,0);
insert into attendance values('17CE01009',25,0,0);
insert into attendance values('17CE01010',25,0,0);

insert into attendance values('16CS01001',4,0,0);
insert into attendance values('16CS01002',4,0,0);
insert into attendance values('16CS01003',4,0,0);
insert into attendance values('16CS01004',4,0,0);
insert into attendance values('16CS01005',4,0,0);
insert into attendance values('16CS01006',4,0,0);
insert into attendance values('16CS01007',4,0,0);
insert into attendance values('16CS01008',4,0,0);
insert into attendance values('16CS01009',4,0,0);
insert into attendance values('16CS01010',4,0,0);
insert into attendance values('16CS01001',5,0,0);
insert into attendance values('16CS01002',5,0,0);
insert into attendance values('16CS01003',5,0,0);
insert into attendance values('16CS01004',5,0,0);
insert into attendance values('16CS01005',5,0,0);
insert into attendance values('16CS01006',5,0,0);
insert into attendance values('16CS01007',5,0,0);
insert into attendance values('16CS01008',5,0,0);
insert into attendance values('16CS01009',5,0,0);
insert into attendance values('16CS01010',5,0,0);
insert into attendance values('16CS01001',6,0,0);
insert into attendance values('16CS01002',6,0,0);
insert into attendance values('16CS01003',6,0,0);
insert into attendance values('16CS01004',6,0,0);
insert into attendance values('16CS01005',6,0,0);
insert into attendance values('16CS01006',6,0,0);
insert into attendance values('16CS01007',6,0,0);
insert into attendance values('16CS01008',6,0,0);
insert into attendance values('16CS01009',6,0,0);
insert into attendance values('16CS01010',6,0,0);
insert into attendance values('16CS01001',33,0,0);
insert into attendance values('16CS01002',33,0,0);
insert into attendance values('16CS01003',33,0,0);
insert into attendance values('16CS01004',33,0,0);
insert into attendance values('16CS01005',33,0,0);
insert into attendance values('16CS01006',33,0,0);
insert into attendance values('16CS01007',33,0,0);
insert into attendance values('16CS01008',33,0,0);
insert into attendance values('16CS01009',33,0,0);
insert into attendance values('16CS01010',33,0,0);
insert into attendance values('16CS01001',30,0,0);
insert into attendance values('16CS01002',30,0,0);
insert into attendance values('16CS01003',30,0,0);
insert into attendance values('16CS01004',30,0,0);
insert into attendance values('16CS01005',30,0,0);
insert into attendance values('16CS01006',30,0,0);
insert into attendance values('16CS01007',30,0,0);
insert into attendance values('16CS01008',30,0,0);
insert into attendance values('16CS01009',30,0,0);
insert into attendance values('16CS01010',30,0,0);

insert into attendance values('16EC01001',8,0,0);
insert into attendance values('16EC01002',8,0,0);
insert into attendance values('16EC01003',8,0,0);
insert into attendance values('16EC01004',8,0,0);
insert into attendance values('16EC01005',8,0,0);
insert into attendance values('16EC01006',8,0,0);
insert into attendance values('16EC01007',8,0,0);
insert into attendance values('16EC01008',8,0,0);
insert into attendance values('16EC01009',8,0,0);
insert into attendance values('16EC01010',8,0,0);
insert into attendance values('16EC01001',14,0,0);
insert into attendance values('16EC01002',14,0,0);
insert into attendance values('16EC01003',14,0,0);
insert into attendance values('16EC01004',14,0,0);
insert into attendance values('16EC01005',14,0,0);
insert into attendance values('16EC01006',14,0,0);
insert into attendance values('16EC01007',14,0,0);
insert into attendance values('16EC01008',14,0,0);
insert into attendance values('16EC01009',14,0,0);
insert into attendance values('16EC01010',14,0,0);
insert into attendance values('16EC01001',32,0,0);
insert into attendance values('16EC01002',32,0,0);
insert into attendance values('16EC01003',32,0,0);
insert into attendance values('16EC01004',32,0,0);
insert into attendance values('16EC01005',32,0,0);
insert into attendance values('16EC01006',32,0,0);
insert into attendance values('16EC01007',32,0,0);
insert into attendance values('16EC01008',32,0,0);
insert into attendance values('16EC01009',32,0,0);
insert into attendance values('16EC01010',32,0,0);
insert into attendance values('16EC01001',16,0,0);
insert into attendance values('16EC01002',16,0,0);
insert into attendance values('16EC01003',16,0,0);
insert into attendance values('16EC01004',16,0,0);
insert into attendance values('16EC01005',16,0,0);
insert into attendance values('16EC01006',16,0,0);
insert into attendance values('16EC01007',16,0,0);
insert into attendance values('16EC01008',16,0,0);
insert into attendance values('16EC01009',16,0,0);
insert into attendance values('16EC01010',16,0,0);

insert into attendance values('16EE01001',9,0,0);
insert into attendance values('16EE01002',9,0,0);
insert into attendance values('16EE01003',9,0,0);
insert into attendance values('16EE01004',9,0,0);
insert into attendance values('16EE01005',9,0,0);
insert into attendance values('16EE01006',9,0,0);
insert into attendance values('16EE01007',9,0,0);
insert into attendance values('16EE01008',9,0,0);
insert into attendance values('16EE01009',9,0,0);
insert into attendance values('16EE01010',9,0,0);
insert into attendance values('16EE01001',28,0,0);
insert into attendance values('16EE01002',28,0,0);
insert into attendance values('16EE01003',28,0,0);
insert into attendance values('16EE01004',28,0,0);
insert into attendance values('16EE01005',28,0,0);
insert into attendance values('16EE01006',28,0,0);
insert into attendance values('16EE01007',28,0,0);
insert into attendance values('16EE01008',28,0,0);
insert into attendance values('16EE01009',28,0,0);
insert into attendance values('16EE01010',28,0,0);
insert into attendance values('16EE01001',29,0,0);
insert into attendance values('16EE01002',29,0,0);
insert into attendance values('16EE01003',29,0,0);
insert into attendance values('16EE01004',29,0,0);
insert into attendance values('16EE01005',29,0,0);
insert into attendance values('16EE01006',29,0,0);
insert into attendance values('16EE01007',29,0,0);
insert into attendance values('16EE01008',29,0,0);
insert into attendance values('16EE01009',29,0,0);
insert into attendance values('16EE01010',29,0,0);
insert into attendance values('16EE01001',50,0,0);
insert into attendance values('16EE01002',50,0,0);
insert into attendance values('16EE01003',50,0,0);
insert into attendance values('16EE01004',50,0,0);
insert into attendance values('16EE01005',50,0,0);
insert into attendance values('16EE01006',50,0,0);
insert into attendance values('16EE01007',50,0,0);
insert into attendance values('16EE01008',50,0,0);
insert into attendance values('16EE01009',50,0,0);
insert into attendance values('16EE01010',50,0,0);

insert into attendance values('16ME01001',42,0,0);
insert into attendance values('16ME01002',42,0,0);
insert into attendance values('16ME01003',42,0,0);
insert into attendance values('16ME01004',42,0,0);
insert into attendance values('16ME01005',42,0,0);
insert into attendance values('16ME01006',42,0,0);
insert into attendance values('16ME01007',42,0,0);
insert into attendance values('16ME01008',42,0,0);
insert into attendance values('16ME01009',42,0,0);
insert into attendance values('16ME01010',42,0,0);
insert into attendance values('16ME01001',43,0,0);
insert into attendance values('16ME01002',43,0,0);
insert into attendance values('16ME01003',43,0,0);
insert into attendance values('16ME01004',43,0,0);
insert into attendance values('16ME01005',43,0,0);
insert into attendance values('16ME01006',43,0,0);
insert into attendance values('16ME01007',43,0,0);
insert into attendance values('16ME01008',43,0,0);
insert into attendance values('16ME01009',43,0,0);
insert into attendance values('16ME01010',43,0,0);
insert into attendance values('16ME01001',44,0,0);
insert into attendance values('16ME01002',44,0,0);
insert into attendance values('16ME01003',44,0,0);
insert into attendance values('16ME01004',44,0,0);
insert into attendance values('16ME01005',44,0,0);
insert into attendance values('16ME01006',44,0,0);
insert into attendance values('16ME01007',44,0,0);
insert into attendance values('16ME01008',44,0,0);
insert into attendance values('16ME01009',44,0,0);
insert into attendance values('16ME01010',44,0,0);
insert into attendance values('16ME01001',45,0,0);
insert into attendance values('16ME01002',45,0,0);
insert into attendance values('16ME01003',45,0,0);
insert into attendance values('16ME01004',45,0,0);
insert into attendance values('16ME01005',45,0,0);
insert into attendance values('16ME01006',45,0,0);
insert into attendance values('16ME01007',45,0,0);
insert into attendance values('16ME01008',45,0,0);
insert into attendance values('16ME01009',45,0,0);
insert into attendance values('16ME01010',45,0,0);

insert into attendance values('16CE01001',46,0,0);
insert into attendance values('16CE01002',46,0,0);
insert into attendance values('16CE01003',46,0,0);
insert into attendance values('16CE01004',46,0,0);
insert into attendance values('16CE01005',46,0,0);
insert into attendance values('16CE01006',46,0,0);
insert into attendance values('16CE01007',46,0,0);
insert into attendance values('16CE01008',46,0,0);
insert into attendance values('16CE01009',46,0,0);
insert into attendance values('16CE01010',46,0,0);
insert into attendance values('16CE01001',47,0,0);
insert into attendance values('16CE01002',47,0,0);
insert into attendance values('16CE01003',47,0,0);
insert into attendance values('16CE01004',47,0,0);
insert into attendance values('16CE01005',47,0,0);
insert into attendance values('16CE01006',47,0,0);
insert into attendance values('16CE01007',47,0,0);
insert into attendance values('16CE01008',47,0,0);
insert into attendance values('16CE01009',47,0,0);
insert into attendance values('16CE01010',47,0,0);
insert into attendance values('16CE01001',48,0,0);
insert into attendance values('16CE01002',48,0,0);
insert into attendance values('16CE01003',48,0,0);
insert into attendance values('16CE01004',48,0,0);
insert into attendance values('16CE01005',48,0,0);
insert into attendance values('16CE01006',48,0,0);
insert into attendance values('16CE01007',48,0,0);
insert into attendance values('16CE01008',48,0,0);
insert into attendance values('16CE01009',48,0,0);
insert into attendance values('16CE01010',48,0,0);
insert into attendance values('16CE01001',49,0,0);
insert into attendance values('16CE01002',49,0,0);
insert into attendance values('16CE01003',49,0,0);
insert into attendance values('16CE01004',49,0,0);
insert into attendance values('16CE01005',49,0,0);
insert into attendance values('16CE01006',49,0,0);
insert into attendance values('16CE01007',49,0,0);
insert into attendance values('16CE01008',49,0,0);
insert into attendance values('16CE01009',49,0,0);
insert into attendance values('16CE01010',49,0,0);

insert into classrooms values(001,1,30,1);
insert into classrooms values(002,1,30,1);
insert into classrooms values(003,1,30,1);
insert into classrooms values(004,1,60,1);
insert into classrooms values(101,1,30,1);
insert into classrooms values(102,1,30,1);
insert into classrooms values(103,1,30,1);
insert into classrooms values(104,1,60,1);
insert into classrooms values(303,1,60,1);

insert into classrooms values(001,2,30,1);
insert into classrooms values(002,2,30,1);
insert into classrooms values(003,2,30,1);
insert into classrooms values(004,2,60,1);

insert into classrooms values(001,3,30,1);
insert into classrooms values(002,3,30,1);
insert into classrooms values(003,3,30,1);
insert into classrooms values(004,3,60,1);
insert into classrooms values(102,3,30,1);

insert into classrooms values(201,1,60,2);
insert into classrooms values(202,1,60,2);
insert into classrooms values(203,1,60,3);
insert into classrooms values(204,1,60,3);
insert into classrooms values(301,1,60,4);
insert into classrooms values(302,1,60,8);

insert into classrooms values(101,2,60,5);
insert into classrooms values(102,2,60,6);
insert into classrooms values(103,2,60,9);

insert into classrooms values(101,3,50,7);

insert into admins values(1,'N Harshith','1');
insert into admins values(2,'D Yashwanth','2');
insert into admins values(3,'V Shanmukha','3');



	

