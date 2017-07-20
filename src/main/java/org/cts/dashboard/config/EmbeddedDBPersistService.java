package org.cts.dashboard.config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.cts.dashboard.utility.Utils;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@DependsOn(value="h2Server")
@EnableScheduling
//@EnableTransactionManagement
public class EmbeddedDBPersistService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedDBPersistService.class);
	private String backUpTime;
	private static String BACKUP_FILE_LOC = "";
	private static final String PORTAL_DB_NAME = "portalDB";

	static {
		if(System.getProperty("os.name").startsWith("Windows")){
			BACKUP_FILE_LOC = "\\dashboard\\data\\dbsql.sql";
		}else {
			BACKUP_FILE_LOC = "/Users/Shared/dashboard/data/dbsql.sql";
		}
	}
	
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder().
				setType(EmbeddedDatabaseType.H2).
				setName(PORTAL_DB_NAME).
				setScriptEncoding("UTF-8");
		//check if backup file exists and is valid sql
		if(Files.exists(Paths.get(BACKUP_FILE_LOC))) {
			String[] restoreArgs = ("-url jdbc:h2:mem:" + PORTAL_DB_NAME + " -user sa -script " + BACKUP_FILE_LOC).split(" ");
			JdbcDataSource ds = null;
			try {
				org.h2.tools.RunScript.main(restoreArgs);
				LOGGER.info("DB restore Completed!");
				ds = new JdbcDataSource();
				ds.setURL("jdbc:h2:mem:" + PORTAL_DB_NAME);
				ds.setUser("sa");
			} catch (SQLException e) {
				LOGGER.error("Error Occurred - ", e);
				return defaultDBLoad(embeddedDatabaseBuilder).build();
			}
			return ds;
		} else {
			return defaultDBLoad(embeddedDatabaseBuilder).build();
		}
	}
	
	private EmbeddedDatabaseBuilder defaultDBLoad(EmbeddedDatabaseBuilder eDBBuilder) {
		LOGGER.info("Loading default DB setup for dashboard...");
		return eDBBuilder.addScripts("classpath:create-dashboard-db.sql","classpath:insert-dashboard-data.sql");
	}
	
	@Scheduled(fixedRate = 120000)
	private void backupDB() {
		
		try {
			String[] backupArgs = ("-url jdbc:h2:mem:" + PORTAL_DB_NAME + " -user sa -script " + BACKUP_FILE_LOC).split(" ");
			org.h2.tools.Script.main(backupArgs);
			LOGGER.info("Mem DB Backup Completed!");
			setBackUpTime("SUCCESS : " + Utils.getCurrentTimeStamp());
			
			/*String[] restoreArgs = "-url jdbc:h2:mem:dataSource -user sa -script \\dashboard\\data\\dbsql.zip -options compression zip".split(" ");
			org.h2.tools.RunScript.main(restoreArgs);
			System.out.println("DB restore Completed!");*/
			
		} catch(Exception ex) {
			setBackUpTime("FAIL : " + Utils.getCurrentTimeStamp());
			LOGGER.error("Error Occurred - ", ex);
		}
		
	}


	public String getBackUpTime() {
		return backUpTime;
	}

	private void setBackUpTime(String backUpTime) {
		this.backUpTime = backUpTime;
	}
	
}
