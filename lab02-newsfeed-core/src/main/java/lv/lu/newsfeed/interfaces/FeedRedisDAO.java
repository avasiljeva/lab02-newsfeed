package lv.lu.newsfeed.interfaces;

import java.util.List;

import lv.lu.newsfeed.model.redis.Feed;

/**
 * Interface for operations with Redis database related to user feeds
 */
public interface FeedRedisDAO {

    /**
     * Add single feed
     * 
     * @param userId
     * @param feed
     */
    public void addFeed(Long userId, Feed feed);

    /**
     * Add a list of feeds
     * 
     * @param userId
     * @param feeds
     */
    public void addFeeds(Long userId, List<Feed> feeds);
    
    /**
     * Get full list of user feeds
     * 
     * @param userId
     * @return
     */
    public List<Feed> getFeeds(Long userId);
}
