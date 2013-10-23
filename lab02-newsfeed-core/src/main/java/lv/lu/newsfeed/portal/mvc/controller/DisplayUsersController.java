package lv.lu.newsfeed.portal.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import lv.lu.newsfeed.interfaces.service.UserService;
import lv.lu.newsfeed.model.jpa.User;
import lv.lu.newsfeed.portal.mvc.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for displaying list of portal users.
 * 
 * Annotation-based controller.
 */
@Controller
public class DisplayUsersController {

    private static final String VIEW_NAME = "display_users";
    
    private static final String ATTR_USERS = "users";

    private UserService userService;
    
    @Autowired
    public DisplayUsersController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String displayStartPage(ModelMap model, HttpSession session) {
        // get current user from session
        User user = (User)session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        
        // load all users except current
        List<User> users = userService.getAllUsers(user.getUsername());
        model.addAttribute(ATTR_USERS, users);
        return VIEW_NAME;
    }
}
