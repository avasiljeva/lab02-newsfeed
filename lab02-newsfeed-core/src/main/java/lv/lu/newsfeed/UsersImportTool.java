package lv.lu.newsfeed;

import lv.lu.newsfeed.interfaces.DataImportProcessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Entry point into the users data importing application.
 * Run to import data into database.
 * 
 * You need to create a database schema before running this program.
 * See \lab01-newsfeed\src\main\resources\init.sql
 * Tables will be created automatically by JPA engine.
 */
public class UsersImportTool {

	public static final String SPRING_CONFIG_FILE = "/applicationContext.xml";
	private static final String SPRING_BEAN_ID = "usersImportProcessor";
	
	public static void main(String[] args) 
	{
		System.out.println("[UsersImportTool] Application started");
		
		// initialize Spring application context from configuration file
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
		DataImportProcessor importProcessor = (DataImportProcessor)appCtxt.getBean(SPRING_BEAN_ID);
		importProcessor.importData();
	
		System.out.println("[UsersImportTool] Application execution successfully completed");
		System.exit(0);
	}
}
