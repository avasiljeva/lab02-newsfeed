package lv.lu.newsfeed.impl.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.data.redis.support.collections.RedisList;

import lv.lu.newsfeed.interfaces.FeedRedisDAO;
import lv.lu.newsfeed.model.redis.Feed;

/**
 * Implementation of basic operations with user feeds stored in Redis DB
 */
public class FeedRedisDAOImpl implements FeedRedisDAO {

    /* Store feeds only for the last 50 activities */
    private int maxSize = 50;
    
    /* Internal Spring template for Redis operations */
	private RedisTemplate<String, Feed> template;
	
	@Override
	public void addFeed(Long userId, Feed feed){
	    RedisList<Feed> feeds = feeds(userId);
	    feeds.add(feed);
	}
	
	@Override
	public void addFeeds(Long userId, List<Feed> feeds){
	    RedisList<Feed> feedList = feeds(userId);
	    feedList.addAll(feeds);
	}

	@Override
	public List<Feed> getFeeds(Long userId){
	    RedisList<Feed> feeds = feeds(userId);
	    Feed[] feedArray = feeds.toArray(new Feed[feeds.size()]);
	    return new ArrayList<Feed>(Arrays.asList(feedArray));
	}
	
	/**
	 * Internal method for getting a list of feeds from Redis DB
	 */
	private RedisList<Feed> feeds(Long userId) {
	    DefaultRedisList<Feed> feeds = new DefaultRedisList<Feed>(RedisKeys.feeds(userId.toString()), template);
	    feeds.setMaxSize(maxSize);
	    return feeds;
	}

    public void setTemplate(RedisTemplate<String, Feed> template) {
        this.template = template;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
