package lv.lu.newsfeed.portal.mvc.controller;

import javax.servlet.http.HttpSession;

import lv.lu.newsfeed.interfaces.JpaDAO;
import lv.lu.newsfeed.model.jpa.User;
import lv.lu.newsfeed.portal.mvc.Constants;
import lv.lu.newsfeed.portal.mvc.validator.RegistrationValidator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Controller for the registration process.
 * 
 * Annotation-based controller.
 */
@Controller
public class RegistrationController {
    
    private static final Logger log = Logger.getLogger(RegistrationController.class);
    
    /*
     * Logical name of the following physical view:
     * \lab02-newsfeed\lab02-newsfeed-portal\src\main\webapp\WEB-INF\jsp\register.jsp
     */
    private static final String FORM_VIEW = "register";
    
    private static final String SUCCESS_VIEW = "redirect:home";
    
    private static final String MODEL_ATTRIBUTE = "user";
    
    /* 
     * Next beans are defined in Spring MVC configuration file: 
     * \lab02-newsfeed\lab02-newsfeed-portal\src\main\webapp\WEB-INF\newsfeed-servlet.xml 
     */
    private RegistrationValidator registrationValidator;

    private JpaDAO jpaDAO;

    @Autowired
    public RegistrationController(RegistrationValidator registrationValidator, JpaDAO jpaDAO) {
        this.registrationValidator = registrationValidator;
        this.jpaDAO = jpaDAO;
    }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String setupForm(ModelMap model) {
        /* Empty form setup */
        model.addAttribute(MODEL_ATTRIBUTE, new User());
        return FORM_VIEW;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processSubmit(HttpSession session, @ModelAttribute(MODEL_ATTRIBUTE) User user, BindingResult result, SessionStatus status) {

        // validate form data
        registrationValidator.validate(user, result);
        
        if (result.hasErrors()) {
            // display form with validation errors
            return FORM_VIEW;
        }

        /* Save new user in database */
        jpaDAO.save(user);        
        
        /* Log user in - save info in HTTP session */
        session.setAttribute(Constants.SessionAttribute.SESSION_USER, user);
        status.setComplete();   
        
        log.debug("User '" + user.getUsername() + "' successfully registered");
        return SUCCESS_VIEW;
    }
}