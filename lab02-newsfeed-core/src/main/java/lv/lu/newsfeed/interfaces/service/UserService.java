package lv.lu.newsfeed.interfaces.service;

import java.util.List;

import lv.lu.newsfeed.model.jpa.User;

/**
 * Service for operations related to users.
 */
public interface UserService {

    public User getByUsername(String username);
    
    /**
     * Returns all users if 'excludeUsername' parameter is null.
     * Otherwise, excludes user with username equal to 'excludeUsername' parameter value from the result list.
     * 
     * @param excludeUsername - username, which should be excluded from result list
     */
    public List<User> getAllUsers(String excludeUsername);
}
