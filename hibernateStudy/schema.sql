CREATE TABLE Student (
	id BIGINT PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);
CREATE TABLE Course (
	id BIGINT PRIMARY KEY,
	title VARCHAR(200) NOT NULL
);
CREATE TABLE Enrollment (
	id BIGINT PRIMARY KEY,
    enrollmentDate DATE NOT NULL,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);

INSERT INTO Student VALUES( 1, 'Manu');
INSERT INTO Student VALUES( 2, 'Mohit');
INSERT INTO Student VALUES( 3, 'Sombati');
INSERT INTO Student VALUES( 4, 'Suresh');

INSERT INTO Course VALUES( 1, 'Necromancy');
INSERT INTO Course VALUES( 2, 'Alchemy');
INSERT INTO Course VALUES( 3, 'Horoscope');

INSERT INTO Enrollment VALUES (1, '2024-02-05', 1, 1);
INSERT INTO Enrollment VALUES (2, '2024-02-05', 1, 3);

INSERT INTO Enrollment VALUES (3, '2024-02-05', 2, 1);
INSERT INTO Enrollment VALUES (4, '2024-02-05', 2, 2);
INSERT INTO Enrollment VALUES (5, '2024-02-05', 2, 3);

INSERT INTO Enrollment VALUES (6, '2024-02-05', 3, 2);
INSERT INTO Enrollment VALUES (7, '2024-02-05', 3, 3);

INSERT INTO Enrollment VALUES (8, '2024-02-05', 4, 3);


-- DROP TABLE course, enrollment, student;