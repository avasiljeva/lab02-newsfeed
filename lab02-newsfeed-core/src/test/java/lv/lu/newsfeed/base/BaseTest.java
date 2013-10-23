package lv.lu.newsfeed.base;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Random;

import lv.lu.newsfeed.model.jpa.Gender;
import lv.lu.newsfeed.model.jpa.User;
import lv.lu.newsfeed.model.jpa.activity.Photo;
import lv.lu.newsfeed.model.jpa.activity.Post;
import lv.lu.newsfeed.model.jpa.activity.Share;

/**
 * Base test containing the most common functions.
 */
public abstract class BaseTest {

	protected static User createTestUser(){
		User user = new User();
		user.setUsername(generateRandomString());
		user.setPassword(generateRandomString());
		user.setFirstName(generateRandomString());
		user.setLastName(generateRandomString());
		user.setGender(Gender.MALE);
		user.setGoogleId(generateRandomString());
		return user;
	}
	
	protected static Post createTestPost(User user){
	    Post post = new Post();
	    post.setText(generateRandomString());
	    post.setCreated(new Date());
	    post.setAuthor(user);
	    return post;
	}
	
	protected static Share createTestShare(User user){
	    Share share = new Share();
	    share.setContent(generateRandomString());
	    share.setComment(generateRandomString());
	    share.setCreated(new Date());
	    share.setAuthor(user);
	    return share;
	}
	
	protected static Photo createTestPhoto(User user){
	    Photo photo = new Photo();
	    photo.setSrc(generateRandomString());
	    photo.setCreated(new Date());
	    photo.setAuthor(user);
	    return photo;
	}
		
    private static String generateRandomString(){
        Random r = new Random();
        String random = Long.toString(Math.abs(r.nextLong()), 36);
        return random.substring(0,8);
    }
    
    protected void assertUsersEquals(User expected, User actual){
        assertEquals("User 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("User 'username' is wrong", expected.getUsername(), actual.getUsername());
        assertEquals("User 'password' is wrong", expected.getPassword(), actual.getPassword());
        assertEquals("User 'first name' is wrong", expected.getFirstName(), actual.getFirstName());
        assertEquals("User 'last name' is wrong", expected.getLastName(), actual.getLastName());
        assertEquals("User 'gender' is wrong", expected.getGender(), actual.getGender());
        assertEquals("User 'facebook id' is wrong", expected.getGoogleId(), actual.getGoogleId());
    }
    
    protected void assertPostsEquals(Post expected, Post actual){
        assertEquals("Post 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("Post 'text' is wrong", expected.getText(), actual.getText());
        assertEquals("Post 'created' is wrong", expected.getCreated(), actual.getCreated());
        assertUsersEquals(expected.getAuthor(), actual.getAuthor());
    }
    
    protected void assertSharesEquals(Share expected, Share actual){
        assertEquals("Share 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("Share 'content' is wrong", expected.getContent(), actual.getContent());
        assertEquals("Share 'comment' is wrong", expected.getComment(), actual.getComment());
        assertEquals("Share 'created' is wrong", expected.getCreated(), actual.getCreated());
        assertUsersEquals(expected.getAuthor(), actual.getAuthor());
    }
    
    protected void assertPhotosEquals(Photo expected, Photo actual){
        assertEquals("Photo 'id' is wrong", expected.getId(), actual.getId());
        assertEquals("Photo 'src' is wrong", expected.getSrc(), actual.getSrc());
        assertEquals("Photo 'comment' is wrong", expected.getComment(), actual.getComment());
        assertEquals("Photo 'created' is wrong", expected.getCreated(), actual.getCreated());
        assertUsersEquals(expected.getAuthor(), actual.getAuthor());
    }
}
