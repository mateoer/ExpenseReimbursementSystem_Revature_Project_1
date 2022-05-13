--DROP TABLE users ;
--DROP TABLE reimbursement ;
--DROP TABLE reimb_status ;
--DROP TABLE reimb_type ;
--DROP TABLE user_role ;

--CREATE TABLE users (
--	 user_id SERIAL 	   	    			
--	,username TEXT UNIQUE NOT NULL	
--	,user_password TEXT NOT NULL	
--	,first_name TEXT    NOT NULL 
--	,last_name TEXT     NOT NULL 
--	,email TEXT 		   UNIQUE NOT NULL 		
--	,user_role INTEGER         NOT NULL 			
--	,PRIMARY KEY (user_id)
--	,FOREIGN KEY (user_role) REFERENCES user_role (user_role_id)
--	-- username , email (UNv1)	
--);
--
--CREATE TABLE reimbursement (
--	  reimb_id  SERIAL      --PK
--	 ,reimb_amount NUMERIC (6,2)
--	 ,reimb_submitted  TIMESTAMP NOT NULL
--	 ,reimb_resolved   TIMESTAMP 
--	 ,reimb_description TEXT
--	 ,reimb_receipt		BYTEA
--	 ,reimb_author		 INTEGER NOT NULL --FK
--	 ,reimb_resolver	 INTEGER NOT NULL --FK
--	 ,reimb_status_id	 INTEGER NOT NULL --FK
--	 ,reimb_type_id		 INTEGER NOT NULL --FK
--	 ,PRIMARY KEY (reimb_id)
--	 ,FOREIGN KEY (reimb_author) REFERENCES users (user_id)
--	 ,FOREIGN KEY (reimb_resolver) REFERENCES users (user_id)
--	 ,FOREIGN KEY (reimb_status_id) REFERENCES reimb_status (reimb_status_id)
--	 ,FOREIGN KEY (reimb_type_id) REFERENCES reimb_type (reimb_type_id)
--);
--
--CREATE TABLE reimb_status (
--		reimb_status_id INTEGER  --PK
--		,reimb_status TEXT
--		,PRIMARY KEY (reimb_status_id)
--);
--
--CREATE TABLE reimb_type (
--		reimb_type_id  INTEGER  --PK
--		,reimb_type TEXT 
--		,PRIMARY KEY (reimb_type_id)
--);
--
--CREATE TABLE user_role (
--		user_role_id INTEGER --PK
--		,user_role TEXT
--		,PRIMARY KEY (user_role_id)
--);

INSERT INTO users (username, user_password, first_name, last_name, email, user_role) VALUES 
	('mateoer', '123', 'Eric', 'Perez','eric234@revature.com', 2),
	('admin', 'abc', 'Mark', 'Wahlberg', 'markimer123@gmail.com', 1);

INSERT INTO user_role (user_role_id, user_role) VALUES 
	(1, 'Finance Manager'),
	(2, 'Employee');

INSERT INTO reimb_status (reimb_status_id, reimb_status) VALUES 
	(1, 'Pending'),
	(2, 'Approved'),
	(3, 'Denied');

INSERT INTO reimb_type (reimb_type_id, reimb_type) VALUES 
	(1, 'Lodging'),
	(2, 'Travel'),
	(3, 'Food'),
	(4, 'Other');

INSERT INTO reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) VALUES 
	(10.00, current_date, 'Enthuware test', 3,4,1,4),
	(250.00, current_date, 'Gas', 3,4,1,4),
	(500.00, current_date, 'Food for convention', 3,4,1,3),
	(700.00, current_date, 'Personal jewelry', 3,4,1,4);

INSERT INTO reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) VALUES 
	(10.00, current_date, 'Enthuware test', 6,4,1,4),
	(1200.00, current_date, 'PS5',6,4,1,4);

--select all reimbursements based on type (1. pending, 2. approved, 3. denied)
SELECT * FROM reimbursement WHERE reimb_status_id = 1;

--finance manager approves (reimb_status_id = 2) OR denies (reimb_status_id = 3)
UPDATE reimbursement 
SET reimb_resolved = current_date, reimb_status_id = 3
WHERE reimb_id = 12;



---CREATE USER
INSERT INTO users (first_name, last_name, email, username, user_password, user_role)
VALUES ('Sue','Ortiz','sueortiz@gmail.com','sue546','avocado', 1)
RETURNING [user_id];

INSERT INTO users (first_name, last_name, email, username, user_password, user_role)
VALUES ('Sue','Smith','sueo@gmail.com','sue545','avocado', 1)
RETURNING user_id;

----UPDATE USER (EMAIL, PASS, NAME, LASTNAME, USER ROLE)
UPDATE users 
SET user_role = 2
--WHERE first_name = ? AND last_name = ? AND email = ? AND username = ?;
WHERE first_name = 'Sue' AND last_name = 'Ortiz' AND email = 'sueortiz@gmail.com' AND username = 'sue546';


UPDATE users 
SET user_role = 2
WHERE user_id = 1;

UPDATE users 
SET user_role = 2
WHERE user_id = 7;

-----REMOVE USER (must delete from previous tables before)
SELECT u.user_id  FROM users u WHERE first_name = 'Sue' AND last_name = 'Ortiz';
--sues id = 5
DELETE FROM reimbursement WHERE reimb_author = 5;

DELETE FROM users WHERE user_id = 5;
--DELETE FROM users WHERE first_name = 'Sue' AND last_name = 'Ortiz';

---SELECTS
SELECT * FROM users u FULL OUTER JOIN reimbursement r ON u.user_id = r.reimb_author 
LEFT JOIN reimb_status rs2 ON rs2.reimb_status_id = r.reimb_status_id  
LEFT JOIN reimb_type rt ON rt.reimb_type_id = r.reimb_type_id 
FULL OUTER JOIN user_role ur ON ur.user_role_id = u.user_role WHERE u.user_id = 1;



SELECT *  
FROM users u FULL OUTER JOIN reimbursement r ON u.user_id = r.reimb_author 
LEFT JOIN reimb_status rs2 ON rs2.reimb_status_id = r.reimb_status_id  
LEFT JOIN reimb_type rt ON rt.reimb_type_id = r.reimb_type_id 
FULL OUTER JOIN user_role ur ON ur.user_role_id = u.user_role ;





UPDATE reimbursement 
SET reimb_status_id = 2
WHERE reimb_id = 10;

-------------------------------------------------------------------------
-------------------------------------------------------------------------
------------EMPLOYEES----------------------------------------------------
-------------------------------------------------------------------------

---view past tickets
SELECT u.user_id FROM users WHERE username = ?; --can use id, names, OR ANY combination
--eric id = 3

SELECT * FROM 
reimbursement r 
LEFT JOIN reimb_status rs2  ON r.reimb_status_id = rs2.reimb_status_id 
LEFT JOIN reimb_type rt ON r.reimb_type_id = rt.reimb_type_id 
LEFT JOIN users u ON r.reimb_author = u.user_id ORDER BY r.reimb_submitted 
;

----add reimbursement request
INSERT INTO reimbursement (reimb_status_id, reimb_type_id, reimb_description, reimb_amount,reimb_author ,reimb_submitted)
VALUES (1, 4, 'asd', 13.00, 3, current_date);




-------------------------------------------------------------------------
-------------------------------------------------------------------------
------------FINANCE MANAGER----------------------------------------------
-------------------------------------------------------------------------

--view all reimbursements for all employees
SELECT * FROM 
reimbursement r 
INNER JOIN reimb_status rs2  ON r.reimb_status_id = rs2.reimb_status_id 
INNER JOIN reimb_type rt ON r.reimb_type_id = rt.reimb_type_id 
INNER JOIN users u ON r.reimb_resolver = u.user_id 
WHERE u.user_role = 2; 



---filter request by status (pending)
SELECT * FROM 
reimbursement r 
LEFT JOIN reimb_status rs2  ON r.reimb_status_id = rs2.reimb_status_id 
LEFT JOIN reimb_type rt ON r.reimb_type_id = rt.reimb_type_id 
LEFT JOIN users u ON r.reimb_author = u.user_id 
WHERE r.reimb_status_id = 1 ;

---filter request by status (accepted)
SELECT * FROM 
reimbursement r 
LEFT JOIN reimb_status rs2  ON r.reimb_status_id = rs2.reimb_status_id 
LEFT JOIN reimb_type rt ON r.reimb_type_id = rt.reimb_type_id 
LEFT JOIN users u ON r.reimb_author = u.user_id 
WHERE r.reimb_status_id = 2 ;

---filter request by status (denied)
SELECT * FROM 
reimbursement r 
LEFT JOIN reimb_status rs2  ON r.reimb_status_id = rs2.reimb_status_id 
LEFT JOIN reimb_type rt ON r.reimb_type_id = rt.reimb_type_id 
LEFT JOIN users u ON r.reimb_author = u.user_id 
WHERE r.reimb_status_id = 3 ;


---approve a reimbursement 
UPDATE reimbursement  
SET reimb_status_id = 2, reimb_resolved = current_date 
WHERE reimb_id = 9;

SELECT * FROM reimbursement r WHERE r.reimb_id = 9;

---deny a reimbursement 
UPDATE reimbursement  
SET reimb_status_id = 3, reimb_resolved = current_date 
WHERE reimb_id = 9;



SELECT * FROM users u WHERE u.user_id = 4 AND u.user_role = 1;





ALTER TABLE reimbursement
ALTER COLUMN reimb_resolver 
DROP NOT NULL;



SELECT * FROM reimbursement r WHERE r.reimb_author = 3;
SELECT * FROM reimbursement r ;
DELETE FROM reimbursement WHERE reimb_description = 'bread';

UPDATE reimbursement SET reimb_status_id = 2, reimb_resolved = current_date, reimb_resolver = 4 WHERE reimb_id = 22;



SELECT * FROM users ;
SELECT * FROM reimbursement ;
SELECT * FROM reimb_status ;
SELECT * FROM reimb_type ;
SELECT * FROM user_role ;




























