package lv.lu.newsfeed.impl.load.user;

import java.util.ArrayList;
import java.util.List;

import lv.lu.newsfeed.model.jpa.Gender;
import lv.lu.newsfeed.model.jpa.User;


import lv.lu.newsfeed.interfaces.UserDataLoader;

/**
 * Mock users data loader implementation, returning fixed list of mock user profiles.
 * Just for demonstration.
 * 
 * TODO [task] your task is to implement real loader, fetching data from Google+.
 * and configure it in Spring context for execution.
 */
public class MockUserDataLoader implements UserDataLoader {

    @Override
    public List<User> loadUsers() {
        
        List<User> users = new ArrayList<User>(5);
        
        User user1 = new User();
        user1.setFirstName("Nancy");
        user1.setLastName("Glad");
        user1.setGender(Gender.FEMALE);
        user1.setUsername("nancy_glad");
        user1.setPassword("nancy_glad");
        users.add(user1);
        
        User user2 = new User();
        user2.setFirstName("Arthur");
        user2.setLastName("Wicket");
        user2.setGender(Gender.MALE);
        user2.setUsername("arthur_wicket");
        user2.setPassword("arthur_wicket");
        users.add(user2);
        
        User user3 = new User();
        user3.setFirstName("Ben");
        user3.setLastName("Armstrong");
        user3.setGender(Gender.MALE);
        user3.setUsername("ben_armstrong");
        user3.setPassword("ben_armstrong");
        users.add(user3);
        
        User user4 = new User();
        user4.setFirstName("Ann");
        user4.setLastName("Kein");
        user4.setGender(Gender.FEMALE);
        user4.setUsername("ann_kein");
        user4.setPassword("ann_kein");
        users.add(user4);
        
        User user5 = new User();
        user5.setFirstName("Michael");
        user5.setLastName("Lait");
        user5.setGender(Gender.MALE);
        user5.setUsername("michael_lait");
        user5.setPassword("michael_lait");
        users.add(user5);
        
        return users;
    }
}
