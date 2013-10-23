package lv.lu.newsfeed.impl.load.user.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lv.lu.newsfeed.interfaces.ActivityDataLoader;
import lv.lu.newsfeed.model.jpa.User;
import lv.lu.newsfeed.model.jpa.activity.Activity;
import lv.lu.newsfeed.model.jpa.activity.Photo;
import lv.lu.newsfeed.model.jpa.activity.Post;
import lv.lu.newsfeed.model.jpa.activity.Share;

/**
 * Mock user activity data loader implementation, returning a fixed list of mock activities.
 * Just for demonstration.
 * 
 * TODO [task] your task is to implement real loader {@link GooglePlusActivityLoader}, fetching data from Google+
 * and configure it in Spring context for execution.
 */
public class MockActivityDataLoader implements ActivityDataLoader {

    @Override
    public List<Activity> loadActivities(User user) {
        List<Activity> activities = new ArrayList<Activity>(3);
        
        Post post1 = new Post();
        post1.setCreated(new Date());
        post1.setText("Hi! This is my first post!");
        post1.setAuthor(user);
        activities.add(post1);
        
        Share share1 = new Share();
        share1.setCreated(new Date());
        share1.setContent("http://www.lu.lv/df");
        share1.setComment("My faculty");
        share1.setAuthor(user);
        activities.add(share1);
        
        Photo photo1 = new Photo();
        photo1.setCreated(new Date());
        photo1.setSrc("https://lh4.googleusercontent.com/-6nlzP1PlCN4/AAAAAAAAAAI/AAAAAAAAADw/ptH8NMu2gBo/s250-c-k/photo.jpg");
        photo1.setComment("This is my first photo!");
        photo1.setAuthor(user);
        activities.add(photo1);
        
        return activities;
    }   
}
