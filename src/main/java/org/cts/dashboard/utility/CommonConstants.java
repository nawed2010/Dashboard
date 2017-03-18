package org.cts.dashboard.utility;

/**
 * Contains common constants, SQL queries etc.
 * @author Dhiman Mondal
 *
 */
public interface CommonConstants {

	//Error Messages
	String ERROR_MESSAGE_SQL_EXCEPTION = "Database exception occurred";
	String ERROR_MESSAGE_REQUIRED_REQUEST_PARAMETER_UNAVAILABLE = "Required request paramter is unavailable";
	String ERROR_MESSAGE_DUBLICATE_EMAIL ="User with the provided email address already exists";
	String ERROR_MESSAGE_RESTRICTED_DATABASE_OPERATION = "This transaction is restricted in the database";
	String ERROR_MESSAGE_REQUIRED_REQUEST_PARAMETER_ALREADY_AVAILABLE = "Required request paramter is already available in database";
	//Constants
	String APPLICATION_DISPLAY_DATE_FORMAT = "dd-MM-yyyy HH:mm";
	int DEFAULT_TASK_TO_COMPLETE_IN_DAYS = 10;
	
	//SQL login 
	String SELECT_RESOURCE_BY_CREDENTIALS = "SELECT * FROM resources WHERE email=:email AND password=:password";
	String SELECT_RESOURCE_BY_ID = "SELECT * FROM resources WHERE id=:id";
	// resource project details
	String SELECT_PROJECTS_BY_RESOURCE_ID =  "SELECT * FROM projects WHERE project_referance IN (SELECT project_referance FROM resource_project_mapping WHERE id=:id)";
	String SELECT_PROJECTDETAILS_BY_RESOURCE_ID ="select a.id as account_id, a.name as account_name, p.id as project_id, p.name as project_name, m.id  as modules_id, m.name  as modules_name, al.role_id as role, r.* from account a inner join project p on p.account_id=a.id inner join module m on m.project_id=p.id inner join release r on m.id=r.module_id inner join allocation al on r.id=al.release_id where al.tag_id=:id";
	String SELECT_HIGHEST_ROLE_BY_ID = "SELECT ID FROM ROLE WHERE id IN (SELECT MIN(id) from allocation where tag_id=:id)";
	String INSERT_INTO_ACCOUNT ="INSERT INTO account (name)VALUES(:name)";

	String SELECT_ACCOUNT_BY_NAME = "SELECT * from ACCOUNT WHERE name=:name" ;
	String SELECT_All_ACCOUNT = "SELECT * from ACCOUNT";
	String SELECT_All_PROJECT_FOR_ID = "SELECT * from PROJECT WHERE  account_id=:id";
	String INSERT_INTO_PROJECT = "INSERT INTO project (name,account_id) VALUES(:name, :account_id)";
	String SELECT_PROJECT_BY_NAME = "SELECT * from PROJECT WHERE name=:name";
	String FIND_INTO_ACCOUNT = "select count(*) from ACCOUNT where id=:account_id";
	
	// modules table 
	String SELECT_All_MODULE_FOR_ID = "SELECT * from MODULE WHERE  project_id=:id";
	String INSERT_INTO_MODULE = "INSERT INTO module (name,project_id) VALUES(:name, :project_id)";
	String SELECT_MODULE_BY_NAME = "SELECT * from MODULE WHERE name=:name";
	String FIND_INTO_PROJECT = "select count(*) from PROJECT where id=:project_id";
	
	/// releases table
	String SELECT_All_RELEASES_FOR_ID = "SELECT * from RELEASE WHERE  module_id=:id";
	String FIND_INTO_RELEASE = "select count(*) from RELEASE where module_id=:module_id";
	String INSERT_INTO_RELEASE = "	INSERT INTO RELEASE (module_id, name, release_actual_id, description, DDD_delivery, CUT_End_date, QA_start_date, QA_end_date, UAT_start_date, UAT_end_date, preview_Release, prod_Release, Type) VALUES(:module_id, :name, :release_actual_id, :description, :DDD_delivery, :CUT_End_date, :QA_start_date, :QA_end_date, :UAT_start_date, :UAT_end_date, :preview_Release, :prod_Release, :Type)";
	String SELECT_RELEASE_BY_ID = "SELECT * from RELEASE WHERE release_actual_id=:release_actual_id";
	
	// risk table
	String SELECT_RISK_BY_ID = "SELECT * from RISK WHERE  release_actual_id=:release_actual_id";
	String SELECT_ADDED_RISK="SELECT * from RISK WHERE  description=:description";
	String CHECK_ALREADY_PRESENT = "select COUNT(*) from RISK where description=:description";
    String UPDATE_RISK = "UPDATE risk SET description=:description, priority=:priority, raised_date=:raised_date,resolution_date=:resolution_date,remarks=:remarks  WHERE description =:description";
	String INSERT_RISK = "INSERT INTO RISK (risk_actual_id, release_actual_id, description, priority, raised_date, resolution_date, remarks) VALUES(:risk_actual_id, :release_actual_id, :description, :priority, :raised_date, :resolution_date, :remarks)";
	String RELEASE_PRESENT = "select COUNT(*) from RELEASE WHERE release_actual_id=:release_actual_id" ;
	
	//defect table
	String SELECT_DEFECT_BY_ID = "SELECT * from DEFECT WHERE  release_actual_id=:release_actual_id";
	String SELECT_ADDED_DEFECT="SELECT * from DEFECT WHERE  description=:description";
	String CHECK_ALREADY_PRESENT_DEFECT = "select COUNT(*) from DEFECT where description=:description";
    String UPDATE_DEFECT = "UPDATE DEFECT SET description=:description, priority=:priority, raised_date=:raised_date,resolution_date=:resolution_date,remarks=:remarks  WHERE description =:description";
    String INSERT_DEFECT = "INSERT INTO DEFECT (defect_actual_id, release_actual_id, description, priority, raised_date, resolution_date, remarks) VALUES(:defect_actual_id, :release_actual_id, :description, :priority, :raised_date, :resolution_date, :remarks)";

	
	
}
