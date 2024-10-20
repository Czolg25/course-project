CREATE TABLE IF NOT EXISTS students ( 

    student_id SERIAL PRIMARY KEY, 

    first_name VARCHAR(50) NOT NULL, 

    last_name VARCHAR(50) NOT NULL, 

    email VARCHAR(100) NOT NULL 

); 

  

CREATE TABLE IF NOT EXISTS courses ( 

    course_id SERIAL PRIMARY KEY, 

    course_name VARCHAR(100) NOT NULL, 

    course_description TEXT 

); 

  

CREATE TABLE IF NOT EXISTS enrollments ( 

    enrollment_id SERIAL PRIMARY KEY, 

    student_id INT REFERENCES students(student_id) ON DELETE CASCADE, 

    course_id INT REFERENCES courses(course_id) ON DELETE CASCADE 

); 

insert into courses(course_name,course_description) values('test1','tt1'); 

insert into courses(course_name,course_description) values('test2','tt2'); 

insert into courses(course_name,course_description) values('test3','tt3'); 

insert into courses(course_name,course_description) values('test4','tt4'); 
