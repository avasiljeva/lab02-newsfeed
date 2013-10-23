package lv.lu.newsfeed.interfaces;

import java.util.List;

import lv.lu.newsfeed.model.jpa.User;

/**
 * Interface for the component for users data loading from external sources
 */
public interface UserDataLoader {

	public List<User> loadUsers();
}
