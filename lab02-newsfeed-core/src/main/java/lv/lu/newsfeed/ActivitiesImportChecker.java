package lv.lu.newsfeed;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessResourceFailureException;

import lv.lu.newsfeed.interfaces.FeedRedisDAO;
import lv.lu.newsfeed.model.redis.Feed;

/**
 * Application for checking Redis database content, namely, user feeds (activities).
 * Redis database should be up and running.
 */
public class ActivitiesImportChecker {

    // specify user id to check
    private static final Long USER_ID = 1L;
    
	public static final String SPRING_CONFIG_FILE = "/applicationContext.xml";
	private static final String SPRING_BEAN_ID = "feedRedisDAO";
	
	public static void main(String[] args) 
	{
		System.out.println("[ActivitiesImportChecker] Check is started");
		
		// initialize Spring application context from configuration file
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
		FeedRedisDAO feedRedisDAO = (FeedRedisDAO)appCtxt.getBean(SPRING_BEAN_ID);
		
		// load feeds from Redis DB
		List<Feed> feeds = null;
        try {
            feeds = feedRedisDAO.getFeeds(USER_ID);
        } catch (DataAccessResourceFailureException e) {
            System.err.println("Unable to connect to Redis DB");
            e.printStackTrace();
            System.exit(1);
        }
        
        // check retrieved data
		if (feeds == null){
		    System.out.println("There are no feeds in Redis DB for user id: " + USER_ID);
		}
		else{		
		    System.out.println("In total " + feeds.size() + " feeds are stored in Redis DB for user id: " + USER_ID);
		    for (Feed feed: feeds){
		        System.out.println(feed.toString() + "\n");
		    }
		}
	
		System.out.println("[ActivitiesImportChecker] Check is completed");
		System.exit(0);
	}
}
