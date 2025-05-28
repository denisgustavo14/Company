CREATE TABLE EMPLOYEE (ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                       FIRST_NAME VARCHAR(30),
                       MID_NAME VARCHAR(30),
                       FATHER_LAST_NAME VARCHAR(30),
                       MOTHER_LAST_NAME VARCHAR(30),
                       AGE INT,
                       GENDER VARCHAR(20),
                       BIRTHDATE DATE,
                       POSITION_EMPLOYEE VARCHAR(30));

INSERT INTO EMPLOYEE (FIRST_NAME, MID_NAME, FATHER_LAST_NAME, MOTHER_LAST_NAME, AGE, GENDER, BIRTHDATE, POSITION_EMPLOYEE) VALUES
('Denis', 'Gustavo', 'Garcia', 'Mendez', 37, 'MALE', '1988-04-20', 'dev'),
('Laura', 'Daniela', 'Consuelo', 'Tarango', 31, 'FEMALE', '1994-11-02', 'doc'),
('Daniel', 'Eli', 'Garcia', 'Mendez', 24, 'MALE', '2000-12-14', 'doc');