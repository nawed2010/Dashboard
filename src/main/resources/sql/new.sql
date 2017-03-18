
select a.id as account_id, a.name as account_name ,p.id as project_id,p.name as project_name ,m.id  as modules_id,m.name  as modules_name ,r.*  
from account a inner join project p on p.account_id=a.id inner join module m on m.project_id=p.id inner join  release r on m.id=r.module_id where
r.id in (select release_id from allocation al where tag_id in (select tag_id from resources re where id=1));



SELECT ID FROM ROLE WHERE id IN (SELECT MIN(id) from allocation where tag_id = 1);

SELECT * from RISK WHERE  release_actual_id='4664'
select * from risk;
select COUNT(*)  from RELEASE WHERE release_actual_id='4664';
select a.id as account_id, a.name as account_name, p.id as project_id, p.name as project_name, m.id  as modules_id, m.name  as modules_name, r.*, al.role_id from account a inner join project p on p.account_id=a.id inner join module m on m.project_id=p.id inner join  release r on m.id=r.module_id where r.id in (select al.release_id from allocation al where al.tag_id in (select re.tag_id from resources re where re.id = 1 ))