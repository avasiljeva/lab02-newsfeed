package lv.lu.newsfeed.impl.load.user;

import java.util.List;

import javax.persistence.Query;

import lv.lu.newsfeed.interfaces.DataImportProcessor;
import lv.lu.newsfeed.interfaces.JpaDAO;
import lv.lu.newsfeed.interfaces.UserActivityImportProcessor;
import lv.lu.newsfeed.interfaces.UserDataLoader;
import lv.lu.newsfeed.model.jpa.JPQConst;
import lv.lu.newsfeed.model.jpa.User;

/**
 * Users data import processor implementation.
 */
public class UsersDataImportProcessor implements DataImportProcessor {
	
    /*
     * List of users data loader components.
     * Should include a new component for data loading from Google+ and maybe something else if you wish.
     */
	private List<UserDataLoader> userDataLoaderList;
	
	/*
	 * Flag to import user activities or not
	 */
	private boolean importActivities = false;
	
	/*
	 * Component for user activity importing
	 */
	private UserActivityImportProcessor activityImportProcessor;
	
	/* 
	 * SQL database operations
	 */
	private JpaDAO jpaDAO;
	
	/* 
	 * Flag to delete everything from database before start
	 */
	private boolean cleanDB;
	
	/**
	 * Main method, which does all the processing
	 */
	public void importData() {
		log("Started...");
		
		if (cleanDB){
		    // clean all database tables if requested
			jpaDAO.cleanupDB();
		}
		
		// load user profiles from external sources
		for (UserDataLoader userDataLoader: userDataLoaderList){
		    
		    log("Started: " + userDataLoader.getClass().getSimpleName());
		    
		    List<User> users = userDataLoader.loadUsers();
		    if (users != null && !users.isEmpty()){
		        for (User user: users){

		            // check if user with such username already exists in database
		            User userDB = getUserFromDatabase(user.getUsername());
		            if (userDB == null){
		                // user does not exist, create new record
		                jpaDAO.save(user);
		                log("User [" + user.getUsername() + "] saved to database");
		            }
		            else{
		                user = userDB;
		                // user already exists
		                log("User [" + user.getUsername() + "] already exists in database");
		            }		            
		            
		            // import activities for that user
		            if (importActivities){
		                log("Starting to import user activities");
		                try {
                            activityImportProcessor.importActivities(user);
                        } catch (Exception e) {
                            System.err.println("Error while importing user activities");
                            e.printStackTrace();
                        }
		            }
		            else{
		                log("Import of user activities is turned off");
		            }
		        }	
		    }
		    else{
		        log("No users found");
		    }
		}
		
		log("Finished!");
	}
	
	/**
	 * Checks if user with provided username already exists in database
	 * @return true is user already exists, false otherwise
	 */
	@SuppressWarnings("unchecked")
    private User getUserFromDatabase(String username){
		Query query = jpaDAO.getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_USER_BY_USERNAME);
		query.setParameter("username", username);
		List<User> users = (List<User>)query.getResultList();
		return users.isEmpty() ? null : users.get(0);
	}	

    private void log(String message){
        System.out.println("[" + this.getClass().getSimpleName() + "] " + message);
    }

	public void setJpaDAO(JpaDAO jpaDAO) {
		this.jpaDAO = jpaDAO;
	}

	public void setCleanDB(boolean cleanDB) {
		this.cleanDB = cleanDB;
	}

    public void setUserDataLoaderList(List<UserDataLoader> userDataLoaderList) {
        this.userDataLoaderList = userDataLoaderList;
    }

    public void setImportActivities(boolean importActivities) {
        this.importActivities = importActivities;
    }

    public void setActivityImportProcessor(UserActivityImportProcessor activityImportProcessor) {
        this.activityImportProcessor = activityImportProcessor;
    }
}
