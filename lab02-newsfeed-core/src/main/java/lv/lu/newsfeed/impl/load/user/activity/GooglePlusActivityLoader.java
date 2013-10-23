package lv.lu.newsfeed.impl.load.user.activity;

import java.util.List;

import lv.lu.newsfeed.interfaces.ActivityDataLoader;
import lv.lu.newsfeed.model.jpa.User;
import lv.lu.newsfeed.model.jpa.activity.Activity;

/**
 * Implementation of user activities data loader from Google+.
 * 
 * TODO [task]: your task is to implement it and configure it in Spring context for execution.
 */
public class GooglePlusActivityLoader implements ActivityDataLoader {

    @Override
    public List<Activity> loadActivities(User user) {
        throw new RuntimeException("Not implemented yet");        
    }
}
