package lv.lu.newsfeed.portal.mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for displaying application home page.
 * Supports four views: Main, Posts, Photos and Shares.
 * See PostController and PhotoController.
 */
@Controller
public class HomePageController {
    
	public static final String HOME_VIEW = "home";
	public static final String ATTR_SELECTED_TAB = "selected_tab";

	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String display(ModelMap model, HttpSession session) {
		// display Main tab by default
    	return displayUserMain(model, session);
    }
	
	/**
	 * Load data for Main tab and display it
	 */
	@RequestMapping(value = "/home/main", method = RequestMethod.GET)
    public String displayUserMain(ModelMap model, HttpSession session) {
        
		/* 
		 * TODO: load necessary data
		 */
        
        model.addAttribute(ATTR_SELECTED_TAB, "user_main");        
        return HOME_VIEW;
    }
	
	/**
	 * Load data for Shares tab and display it
	 * Move it to separate ShareController.
	 */
	@RequestMapping(value = "/home/shares", method = RequestMethod.GET)
    public String displayShares(ModelMap model, HttpSession session) {
		
		/* 
		 * TODO: load necessary data
		 */
		
        model.addAttribute(ATTR_SELECTED_TAB, "shares");        
        return HOME_VIEW;
    }
}