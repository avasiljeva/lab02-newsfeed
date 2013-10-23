package lv.lu.newsfeed.portal.mvc.validator;

import lv.lu.newsfeed.interfaces.service.UserService;
import lv.lu.newsfeed.model.jpa.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for "Login" form.
 * 
 * This bean is defined in Spring MVC configuration file: 
 * \lab02-newsfeed\lab02-newsfeed-portal\src\main\webapp\WEB-INF\newsfeed-servlet.xml
 */
public class LoginValidator implements Validator{

    private UserService userService;
    
    @Autowired
    public LoginValidator(UserService userService) {
        this.userService = userService;
    }
    
    @SuppressWarnings("rawtypes")
    public boolean supports(Class givenClass){
        return givenClass.equals(User.class);
    }

    public void validate(Object obj, Errors errors){
        User user = (User)obj;
        if (user == null){
            errors.reject("error.nullpointer", "Null data received");
            return;
        }
        if (user.getUsername().equals("")){
            errors.rejectValue("username", "error.empty", "Empty username!");
            return;
        }
        if (user.getPassword().equals("")){
            errors.rejectValue("password", "error.empty", "Empty password!");
            return;
        }
        
        // try to authenticate user
        User userFromDB = userService.getByUsername(user.getUsername());                
        if(userFromDB != null){
            // check that password matches provided value
            if (user.getPassword().equals(userFromDB.getPassword())){
                // success
                return;
            }
            else{
                errors.rejectValue("password", "error.wrong_password", "Wrong password!");
            }
        } else {
            errors.rejectValue("username", "error.wrong_username", "Wrong username!");
        }
    }
}