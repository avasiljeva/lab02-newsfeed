package lv.lu.newsfeed.interfaces;

import lv.lu.newsfeed.model.jpa.User;

/**
 * Processor for coordinating the import of user activities into database
 */
public interface UserActivityImportProcessor {

    public void importActivities(User user);
}
