package lv.lu.newsfeed.model.redis;

import java.io.Serializable;
import java.util.Date;

/**
 * User feed (stored in Redis DB), representing a user activity
 */
public class Feed implements Serializable {

    private static final long serialVersionUID = 1L;

    /* Activity type represented by this feed */
    private FeedType feedType;
    
    /* Date, when activity was generated by the user */
    private Date date;
    
    /* 
     * Reference to a business entity (e.g. {@link Post}, {@link Share}, {@link Photo}).
     * Namely, business object id in the scope of relational (e.g. MySQL) database.
     */
    private Long referenceId;

    public FeedType getFeedType() {
        return feedType;
    }

    public void setFeedType(FeedType feedType) {
        this.feedType = feedType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    @Override
    public String toString() {
        return "Feed [feedType=" + feedType + ", date=" + date + ", referenceId=" + referenceId + "]";
    }
}