select * from Question

select * from Answer

create table Question (
QUESTION_ID BIGINT PRIMARY KEY,
QUESTION_DESCRIPTION VARCHAR(250) NOT NULL
);

create table Answer (
ANSWER_ID BIGINT PRIMARY KEY,
ANSWER_TEXT VARCHAR(250) NOT NULL,
QUESTION_ID BIGINT not null,
CONSTRAINT CONSTRAINT1 FOREIGN KEY (QUESTION_ID)
REFERENCES Question (QUESTION_ID)
);

create table QUESTION_ANSWER (
QUESTION_ID BIGINT PRIMARY KEY,
ANSWER_ID BIGINT NOT NULL
);

insert into Question values(1, 'This is Question 1 ?');
insert into Question values(2, 'This is Question 2 ?');
insert into Question values(3, 'This is Question 3 ?');
insert into Question values(4, 'This is Question 4 ?');


insert into Answer values(1,'Ans 1',1);
insert into Answer values(2,'Ans 2',1);
insert into Answer values(3,'Ans 1',2);
insert into Answer values(4,'Ans 2',2);
insert into Answer values(5,'Ans 1',3);
insert into Answer values(6,'Ans 2',3);
insert into Answer values(7,'Ans 1',4);
insert into Answer values(8,'Ans 2',4);


insert into QUESTION_ANSWER values(1,2);
insert into QUESTION_ANSWER values(2,3); 
insert into QUESTION_ANSWER values(3,5); 
insert into QUESTION_ANSWER values(4,8);  