package lv.lu.newsfeed.portal.mvc.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lv.lu.newsfeed.portal.mvc.tmp.InMemoryTmpStorage;

import lv.lu.newsfeed.model.jpa.User;
import lv.lu.newsfeed.model.jpa.activity.Post;
import lv.lu.newsfeed.portal.mvc.Constants;

/**
 * Controller for handling user posts on a tab "Posts" of Home page.
 * See HomePageController.
 */
@Controller
public class PostController {
    
    private static final String VIEW = "home/posts";
    
    /**
     * Load data for Posts tab and display it
     */
    @RequestMapping(value = "/" + VIEW, method = RequestMethod.GET)
    public String displayPosts(ModelMap model, HttpSession session) {
        
        User user = (User)session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        
        // TODO: this is temporary data storage (just for demo), replace InMemoryTmpStorage by real persistence
        List<Post> posts = InMemoryTmpStorage.getInstance().getPosts(user.getId());    
        model.addAttribute("posts", posts);
        
        model.addAttribute(HomePageController.ATTR_SELECTED_TAB, "posts");
        return HomePageController.HOME_VIEW;
    }
    
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String addPost(ModelMap model, HttpSession session, @RequestParam("text") String text) {
        
        // get current user from session
        User user = (User)session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        
        Post post = new Post();
        post.setText(text);
        post.setAuthor(user);
        post.setCreated(new Date());
        
        // TODO: temporary (just for demo) store posts in in-memory storage
        // it has to be replaced by persisting data in relational database
        InMemoryTmpStorage.getInstance().addPost(post);
        
        return "redirect:" + VIEW;
    }
}
