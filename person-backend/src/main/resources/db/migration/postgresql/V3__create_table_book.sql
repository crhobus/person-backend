CREATE TABLE BOOK (
ID NUMERIC(10) NOT NULL,
AUTHOR VARCHAR(120) NOT NULL,
LAUNCH_DATE TIMESTAMP NOT NULL,
PRICE NUMERIC(10,2) NOT NULL,
TITLE VARCHAR(200) NOT NULL,
CONSTRAINT BOOK_PKEY PRIMARY KEY (ID)
);