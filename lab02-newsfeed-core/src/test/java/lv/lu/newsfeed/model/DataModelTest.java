package lv.lu.newsfeed.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lv.lu.newsfeed.base.JpaBaseTest;
import lv.lu.newsfeed.model.jpa.User;
import lv.lu.newsfeed.model.jpa.activity.Activity;
import lv.lu.newsfeed.model.jpa.activity.Photo;
import lv.lu.newsfeed.model.jpa.activity.Post;
import lv.lu.newsfeed.model.jpa.activity.Share;

/**
 * Creates a full data structure with associations 
 * and then tests that data is correctly saved to and retrieved from database.
 * 
 * Only persistence and O/R mapping is tested there.
 */
public class DataModelTest extends JpaBaseTest{ 
    
	private static List<User> users;
	private static List<Activity> activities;
	private static Post post;
	private static Share share;
	private static Photo photo;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		JpaBaseTest.setUpBeforeClass();
		em.getTransaction().begin();
		
		users = createUsers();
		
		User user = users.get(0);
		activities = createActivities(user);
		
		/* flush changes to data store and clear persistence context */		
		flushAndClear();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		em.getTransaction().rollback();
		JpaBaseTest.tearDownAfterClass();
	}
	
	@Override @Before
	public void setUp() { /* nothing */ }
	
	@Override @After
	public void tearDown() {/* nothing */ }

	/**
	 * Data creation
	 */
	
	private static List<User> createUsers() {
		List<User> users = new ArrayList<User>(3);
		User user1 = createTestUser();
		users.add(user1);	
		User user2 = createTestUser();
		users.add(user2);
		User user3 = createTestUser();
		users.add(user3);		
		jpaDAO.saveAll(users);
		return users;
	}
	
	private static List<Activity> createActivities(User user) {
	    List<Activity> activities = new ArrayList<Activity>(3);
	    post = createTestPost(user);
	    activities.add(post);
	    share = createTestShare(user);
	    activities.add(share);
	    photo = createTestPhoto(user);
	    activities.add(photo);
	    jpaDAO.saveAll(activities);
	    return activities;
	}
	
	/**
	 * Test for users
	 */
	
	@Test
	public void testUsers(){			
		List<User> usersFromDB = jpaDAO.findAll(User.class);
		assertEquals("Wrong number of users", users.size(), usersFromDB.size());

		for (int i=0; i<users.size(); i++){
		    assertUsersEquals(users.get(i), usersFromDB.get(i));
		}
	}
	
	@Test
	public void testActivities(){            
	    List<Activity> activitiesFromDB = jpaDAO.findAll(Activity.class);
	    assertEquals("Wrong number of activities", activities.size(), activitiesFromDB.size());
	    for (Activity activity: activitiesFromDB){
	        if (activity instanceof Post){
	            assertPostsEquals(post, (Post)activity);
	        } else if (activity instanceof Share){
	            assertSharesEquals(share, (Share)activity);
	        } else if (activity instanceof Photo){
	            assertPhotosEquals(photo, (Photo)activity);
	        }	        
	    }
	}
	
}