package lv.lu.newsfeed.impl.service;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import lv.lu.newsfeed.impl.AbstractDAOImpl;
import lv.lu.newsfeed.interfaces.JpaDAO;
import lv.lu.newsfeed.interfaces.service.UserService;
import lv.lu.newsfeed.model.jpa.JPQConst;
import lv.lu.newsfeed.model.jpa.User;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service for operations related to users.
 */
public class UserServiceImpl extends AbstractDAOImpl implements UserService {

    private JpaDAO jpaDAO;

    @Autowired
    public UserServiceImpl(JpaDAO jpaDAO){
        this.jpaDAO = jpaDAO;
    }

    public User getByUsername(String username) {
        if (username == null){
            return null;
        }

        User user = null;
        try {
            Query query = getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_USER_BY_USERNAME);
            query.setParameter("username", username);
            user = (User) query.getSingleResult();
        } 
        catch (NoResultException nre) {
            // no such user
            System.out.println("User with username '" + username + "' does not exist");
        } 
        catch (NonUniqueResultException nure) {
            // multiple users with such name
            System.out.println("There are multiple users with username '" + username + "' in database");
        }
        return user;
    }

    /**
     * Returns all users if 'excludeUsername' parameter is null.
     * Otherwise, excludes user with username equal to 'excludeUsername' parameter value from the result list.
     * 
     * @param excludeUsername - username, which should be excluded from result list
     */
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers(String excludeUsername) {
        List<User> users;
        if (excludeUsername == null){
            users = jpaDAO.findAll(User.class);            
        }
        else{
            Query query = getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_ALL_EXCLUDE_USERNAME);
            query.setParameter("username", excludeUsername);
            users = query.getResultList();
        }
        Collections.sort(users);
        return users;
    }
}
