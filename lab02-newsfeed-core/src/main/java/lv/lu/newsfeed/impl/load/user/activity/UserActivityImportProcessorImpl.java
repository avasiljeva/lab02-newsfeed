package lv.lu.newsfeed.impl.load.user.activity;

import java.util.ArrayList;
import java.util.List;

import lv.lu.newsfeed.interfaces.ActivityDataLoader;
import lv.lu.newsfeed.interfaces.FeedRedisDAO;
import lv.lu.newsfeed.interfaces.JpaDAO;
import lv.lu.newsfeed.interfaces.UserActivityImportProcessor;
import lv.lu.newsfeed.model.jpa.User;
import lv.lu.newsfeed.model.jpa.activity.Activity;
import lv.lu.newsfeed.model.redis.Feed;
import lv.lu.newsfeed.model.redis.FeedType;

/**
 * Processor implementation for coordinating import of user activities into database
 */
public class UserActivityImportProcessorImpl implements UserActivityImportProcessor {

    /*
     * List of user activity data loader components.
     * Should include a new component for data loading from Google+ and maybe something else if you wish.
     */
    private List<ActivityDataLoader> activityDataLoaderList;
    
    /* 
     * SQL database operations
     */
    private JpaDAO jpaDAO;
    
    /* 
     * Redis database operations
     */
    private FeedRedisDAO feedDAO;
    
    public void importActivities(User user) {
        
        // load activities
        List<Activity> activities = new ArrayList<Activity>();
        
        for (ActivityDataLoader loader: activityDataLoaderList){
            log("Started: " + loader.getClass().getSimpleName());            
            List<Activity> nextActivities = loader.loadActivities(user);
            
            if (nextActivities != null){
                log(nextActivities.size() + " activities loaded for user: " + user.getUsername());
                activities.addAll(nextActivities);
            }
        }
        
        // save activities to SQL database
        jpaDAO.saveAll(activities);  
        
        // convert activities to feeds
        List<Feed> feeds = new ArrayList<Feed>(activities.size());
        for (Activity activity: activities){            
            Feed feed = new Feed();
            feed.setDate(activity.getCreated());
            feed.setReferenceId(activity.getId());
            
            String activityClass = activity.getClass().getSimpleName().toUpperCase();
            try {                
                feed.setFeedType(FeedType.valueOf(activityClass));
            } catch (Exception e) {
                log("Unable to recognize FeedType from: " + activityClass + ". Skipping this activity...");
                continue;
            }
            
            feeds.add(feed);
        }        
        
        // save feeds to Redis database
        feedDAO.addFeeds(user.getId(), feeds);
    }
    
    private void log(String message){
        System.out.println("[" + this.getClass().getSimpleName() + "] " + message);
    }

    public void setActivityDataLoaderList(List<ActivityDataLoader> activityDataLoaderList) {
        this.activityDataLoaderList = activityDataLoaderList;
    }
    
    public void setJpaDAO(JpaDAO jpaDAO) {
        this.jpaDAO = jpaDAO;
    }

    public void setFeedDAO(FeedRedisDAO feedDAO) {
        this.feedDAO = feedDAO;
    }
}
