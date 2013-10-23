package lv.lu.newsfeed.interfaces;

import java.util.List;

import lv.lu.newsfeed.model.jpa.User;
import lv.lu.newsfeed.model.jpa.activity.Activity;

/**
 * Interface for the component for loading user activities from external sources
 */
public interface ActivityDataLoader {

	public List<Activity> loadActivities(User user);
}
