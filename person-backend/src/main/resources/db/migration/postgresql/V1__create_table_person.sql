CREATE TABLE PERSON (
ID NUMERIC(10) NOT NULL,
FIRST_NAME VARCHAR(80) NOT NULL,
LAST_NAME VARCHAR(80) NOT NULL,
ADDRESS VARCHAR(100) NOT NULL,
GENDER VARCHAR(10) NOT NULL,
CONSTRAINT PERSON_PKEY PRIMARY KEY (ID)
);