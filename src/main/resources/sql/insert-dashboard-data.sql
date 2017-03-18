

--insert groups data
INSERT INTO groups (name) VALUES
('PSS'),
('QAS');

--insert modules data
INSERT INTO modules (name) VALUES
('CRS/CMOR'),
('Shopper'),
('Franchisee'),
('Distribution'),
('Middleware'),
('CWS'),
('Loyalty');

--inser roles data
INSERT INTO roles (name, access_index) VALUES
('Project Manager', 0),
('Team Lead', 1),
('Module Lead', 2),
('General', 2);

--inser users data
INSERT INTO users (name, email, access_code) VALUES
('Sayak Deb', 'sayak.deb@email.com', 1),
('Aman Mishra', 'aman.misra@mail.com', 0),
('Md Nawed', 'md.nawed@mail.com', 0),
('Arpan Pal', 'arpan.pal@mail.com', 0);

--inser user_roles data
INSERT INTO user_roles (role_id, group_id, module_id, user_id) VALUES
(1, null, null, 1),
(4, 1, 2, 2),
(3, 1, 6, 2);

--inser tasks data
INSERT INTO tasks (description, owner_id, publisher_id, days_to_complete) VALUES
('Task for Shopper QAS', 1, 1, 10),
('Task for Middleware PSS', 1, 1, 10),
('Task for CWS', 2, 1, 10);

--inser task_mappings data
INSERT INTO task_mappings (group_id, module_id, task_id) VALUES
(2, 2, 1),
(1, 5, 2),
(1, 6, 3);

--inser task_status data
INSERT INTO task_status (task_id, user_id) VALUES
(3, 2);

--------------------------------------------------------------------------------------------------------------

--insert project structure
INSERT INTO project_structure (account, project_type, modules) VALUES
('Wyndham Hotel Group', 'App Development', 'BWS'),
('Wyndham Hotel Group', ' Maintenacne', 'CMOR'),
('Wyndham Hotel Group', ' Maintenacne', 'CRS'),
('Wyndham Hotel Group', ' Maintenacne', 'BWS'),
('Wyndham Hotel Group', 'Production Support','BWS'),
('Starwoods', 'Development', 'CMOR');


-------------------------------------insert role
INSERT INTO role (name) VALUES
('Bussiness User'),
('Manager'),
('Team Lead'),
('Developer');





INSERT INTO account (name) VALUES
('WHG'),
('Starwood'),
('LQ');


INSERT INTO project (name, account_id) VALUES
('App development',1),
('development',2),
('lq development',3),
('lq maintenance',3),
('lq production',3),
('whg support',2);

INSERT INTO module (name, project_id) VALUES
('BWS',1),
('CRS',1),
('BWS',6),
('CRS',6),
('CMOR',1),
('RAAT',3),
('NVRAAT',5),
('STARDEV',2);



--insert release name


INSERT INTO release (module_id, name, release_actual_id, description, DDD_delivery, CUT_End_date, QA_start_date, QA_end_date, UAT_start_date, UAT_end_date, preview_Release, prod_Release, is_deleted, Type) VALUES
(1,'CUG', '6446', '','NA', '2016-07-26 00:00:00', '2016-09-16 00:00:00','2016-10-16 00:00:00','2016-10-26 00:00:00', '2016-10-30 00:00:00','2016-11-16 00:00:00', '2016-11-20 00:00:00', 0 ,'desktop' ),
(1, 'Auto Enroll', '4664', 'Auto Enrolling should be done on mobile sites','NA', '2016-07-26 00:00:00', '2016-09-16 00:00:00','2016-10-16 00:00:00','2016-10-26 00:00:00', '2016-10-30 00:00:00','2016-11-16 00:00:00', '2016-11-20 00:00:00', 0 ,'desktop' ),
(4, 'Endeca data fetch', 'E445', 'Hotels Id should be fetched at once', 'NA', '2016-07-26 00:00:00', '2016-09-16 00:00:00','2016-10-16 00:00:00','2016-10-26 00:00:00', '2016-10-30 00:00:00','2016-11-16 00:00:00', '2016-11-20 00:00:00', 0 ,'mobile' );



INSERT INTO allocation (tag_id, release_id, role_id)VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(2, 1, 4),
(1, 3, 4);



--inser resources data
INSERT INTO resources (name, email, access_code, tag_id) VALUES
('Sayak Deb', 'sayak.deb@email.com', 1, 1),
('Aman Mishra', 'aman.mishra@email.com', 1, 2),
('Md Nawed', 'md.nawed@email.com', 1, 3),
('Arpan Pal', 'arpan.pal@email.com', 0, 4);


---insert into risk table

INSERT INTO risk (risk_actual_id, release_actual_id, description, priority, raised_date, resolution_date, remarks) VALUES
('345','4664','hihhahahah',1,'2016-07-26 00:00:00', '2016-09-16 00:00:00', 'hohohoh'), 	 	  
('346','4664','second',2,'2016-07-26 00:00:00', '2016-09-16 00:00:00', 'second3');

------insert into defects

INSERT INTO defect (defect_actual_id, release_actual_id, description, priority, raised_date, resolution_date, remarks) VALUES
('345','4664','defect1',1,'2016-07-26 00:00:00', '2016-09-16 00:00:00', 'hohohoh'),
('346','4664','defects2',2,'2016-07-26 00:00:00', '2016-09-16 00:00:00', 'second3');



                                                                             


                                                                               







