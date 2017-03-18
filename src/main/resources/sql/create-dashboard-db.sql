
DROP TABLE groups IF EXISTS;
DROP TABLE modules IF EXISTS;
DROP TABLE roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE tasks IF EXISTS;
DROP TABLE task_mappings IF EXISTS;
DROP TABLE task_status IF EXISTS;
DROP TABLE resources IF EXISTS;
DROP TABLE project_structure IF EXISTS;
DROP TABLE projects IF EXISTS;
DROP TABLE project_account_mapping IF EXISTS;
DROP TABLE resource_project_mapping IF EXISTS;
DROP TABLE project IF EXISTS;
DROP TABLE module IF EXISTS;
DROP TABLE release IF EXISTS;
DROP TABLE account IF EXISTS;
DROP TABLE role IF EXISTS;
DROP TABLE allocation IF EXISTS;
DROP TABLE risk IF EXISTS;
DROP TABLE defect IF EXISTS;

 

CREATE TABLE groups (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  /*admin_id INT DEFAULT -1,*/
  create_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_ts TIMESTAMP AS CURRENT_TIMESTAMP
);

CREATE TABLE modules (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  /*admin_id INT DEFAULT -1,*/
  create_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_ts TIMESTAMP AS CURRENT_TIMESTAMP
);

CREATE TABLE roles (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	access_index INT NOT NULL,
	/*admin_id INT DEFAULT -1,*/
	create_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_ts TIMESTAMP AS CURRENT_TIMESTAMP
);

CREATE TABLE users (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(50) DEFAULT 'password',
	access_code INT NOT NULL,
	is_deleted BOOLEAN DEFAULT 0,
	admin_id INT DEFAULT -1,
	create_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_ts TIMESTAMP AS CURRENT_TIMESTAMP,
	delete_ts TIMESTAMP DEFAULT NULL,
	UNIQUE (email)
);

CREATE TABLE user_roles (
	id INT PRIMARY KEY AUTO_INCREMENT,
	role_id INT NOT NULL,
	group_id INT,
	module_id INT,
	user_id INT NOT NULL,
	/*admin_id INT DEFAULT -1,*/
	create_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_ts TIMESTAMP AS CURRENT_TIMESTAMP,
	FOREIGN KEY (role_id) REFERENCES roles(id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (group_id) REFERENCES groups(id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (module_id) REFERENCES modules(id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tasks (
	id INT PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(200) NOT NULL,
	owner_id INT NOT NULL,
	publisher_id INT NOT NULL,
	days_to_complete INT NOT NULL,
	reverse_task BOOLEAN DEFAULT 0,
	reverse_id INT DEFAULT -1,
	is_deleted BOOLEAN DEFAULT 0,
	admin_id INT DEFAULT -1,
	create_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_ts TIMESTAMP AS CURRENT_TIMESTAMP,
	delete_ts TIMESTAMP DEFAULT NULL,
	FOREIGN KEY (owner_id) REFERENCES users(id),
	FOREIGN KEY (publisher_id) REFERENCES users(id)
);

CREATE TABLE task_mappings (
	id INT PRIMARY KEY AUTO_INCREMENT,
	group_id INT NOT NULL,
	module_id INT NOT NULL,
	task_id INT NOT NULL,
	is_deleted BOOLEAN DEFAULT 0,
	/*admin_id INT DEFAULT -1,*/
	create_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_ts TIMESTAMP AS CURRENT_TIMESTAMP,
	FOREIGN KEY (group_id) REFERENCES groups(id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (module_id) REFERENCES modules(id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (task_id) REFERENCES tasks(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE task_status (
	id INT PRIMARY KEY AUTO_INCREMENT,
	task_id INT NOT NULL,
	user_id INT NOT NULL,
	status INT DEFAULT 0,
	admin_id INT DEFAULT -1,
	create_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_ts TIMESTAMP AS CURRENT_TIMESTAMP,
	FOREIGN KEY (task_id) REFERENCES tasks(id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE project_structure (
	structure_id INT PRIMARY KEY AUTO_INCREMENT,
	account VARCHAR(50) NOT NULL,
	project_type VARCHAR(50) NOT NULL,
	modules VARCHAR(50) ,
	is_deleted BOOLEAN DEFAULT 0
);


----------------------------------------------------------------------------------------------------------------
--------------------------------------create role table
CREATE TABLE role (
id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50)  NOT NULL
);

----------------------create account table
CREATE TABLE account (
 id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(50) NOT NULL
);

----------------------create PROJECT table
CREATE TABLE project (
 id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(50) NOT NULL,
 account_id INT NOT NULL,
 FOREIGN KEY (account_id) REFERENCES account(id) ON UPDATE CASCADE ON DELETE CASCADE
);

----------------------create MODULE table
CREATE TABLE module (
 id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(50) NOT NULL,
  project_id INT NOT NULL,
 FOREIGN KEY (project_id) REFERENCES project(id) ON UPDATE CASCADE ON DELETE CASCADE
);

---------------------------create release table
CREATE TABLE RELEASE (
    module_id INT NOT NULL,	
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	release_actual_id VARCHAR(50) NOT NULL,
	description VARCHAR(50),
	DDD_delivery  VARCHAR(50),
	CUT_End_date VARCHAR(50),
	QA_start_date VARCHAR(50) ,
	QA_end_date VARCHAR(50) ,
	UAT_start_date VARCHAR(50) ,
	UAT_end_date VARCHAR(50),
	preview_Release VARCHAR(50),	
	prod_Release VARCHAR(50),
	is_deleted BOOLEAN DEFAULT 0,
	Type VARCHAR(50),
	
 FOREIGN KEY (module_id) REFERENCES module(id) ON UPDATE CASCADE ON DELETE CASCADE	
);

--------------------------------------create resource table
CREATE TABLE resources (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(50) DEFAULT 'password',
	access_code INT NOT NULL,
	is_deleted BOOLEAN DEFAULT 0,
	tag_id INT,
	UNIQUE (email)	
);


--------------------------------------create allocation table
CREATE TABLE allocation (
    id INT PRIMARY KEY AUTO_INCREMENT,
	tag_id INT NOT NULL,
	release_id INT  NOT NULL,
	role_id INT NOT NULL,
FOREIGN KEY (release_id) REFERENCES release(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE	
);
--------------------------------------create risk table

Create table risk(
id int primary key auto_increment,
risk_actual_id VARCHAR(50)  ,
     release_actual_id VARCHAR(50),
   description VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    raised_date VARCHAR(50) NOT NULL,
	resolution_date VARCHAR(50),
	remarks VARCHAR(50),
FOREIGN KEY (release_actual_id) REFERENCES release(release_actual_id) ON UPDATE CASCADE ON DELETE CASCADE

);


Create table defect(
id int primary key auto_increment,
defect_actual_id VARCHAR(50)  ,
    release_actual_id VARCHAR(50),
    description VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    raised_date VARCHAR(50) NOT NULL,
	resolution_date VARCHAR(50),
	remarks VARCHAR(50),
FOREIGN KEY (release_actual_id) REFERENCES release(release_actual_id) ON UPDATE CASCADE ON DELETE CASCADE
);








