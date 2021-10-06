insert into course(id, name, created, updated) values (10001, 'Hibernate-Jpa', sysdate(), sysdate());
insert into course(id, name, created, updated) values (10002, 'Spring', sysdate(), sysdate());
insert into course(id, name, created, updated) values (10003, 'Spring Boot', sysdate(), sysdate());
insert into course(id, name, created, updated) values (10004, 'React JS', sysdate(), sysdate());
insert into course(id, name, created, updated) values (10005, 'React Native', sysdate(), sysdate());
insert into course(id, name, created, updated) values (10006, 'Docker', sysdate(), sysdate());
insert into course(id, name, created, updated) values (10007, 'Kubernetes', sysdate(), sysdate());
insert into course(id, name, created, updated) values (10008, 'Data Structure', sysdate(), sysdate());
insert into course(id, name, created, updated) values (10009, 'Algorithm', sysdate(), sysdate());
insert into course(id, name, created, updated) values (10010, 'Database Desing', sysdate(), sysdate());

insert into passport(id, number) values(40001, 'E129845');
insert into passport(id, number) values(40002, 'L925376');
insert into passport(id, number) values(40003, 'N725439');

insert into student(id, name, passport_id) values(20001, 'Shiva', 40001);
insert into student(id, name, passport_id) values(20002, 'Krishna', 40002);
insert into student(id, name, passport_id) values(20003, 'Raghu', 40003);

insert into review(id, rating, description, course_id) values(50001, '4', 'Enjoyed while learning', 10001);
insert into review(id, rating, description, course_id) values(50002, '5', 'Awesome lecture', 10001);
insert into review(id, rating, description, course_id) values(50003, '4', 'Great learning', 10002);

insert into student_course(student_id, course_id) values(20001, 10001);
insert into student_course(student_id, course_id) values(20001, 10003);
insert into student_course(student_id, course_id) values(20002, 10001);
insert into student_course(student_id, course_id) values(20003, 10001);


/*SELECT * FROM STUDENT, PASSPORT
WHERE STUDENT.PASSPORT_ID=PASSPORT.ID

SELECT * FROM REVIEW, COURSE
WHERE REVIEW.COURSE_ID = COURSE.ID

SELECT * FROM 
STUDENT_COURSE, STUDENT, COURSE
WHERE
STUDENT_COURSE.STUDENT_ID = STUDENT.ID AND
STUDENT_COURSE.COURSE_ID = COURSE.ID

SELECT * FROM EMPLOYEE,  FULL_TIME_EMPLOYEE 
WHERE EMPLOYEE.ID = FULL_TIME_EMPLOYEE.ID

SELECT * FROM COURSE 
WHERE COURSE.ID NOT IN
(SELECT COURSE_ID FROM STUDENT_COURSE)
*/