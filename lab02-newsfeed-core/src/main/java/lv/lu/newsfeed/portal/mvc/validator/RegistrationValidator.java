package lv.lu.newsfeed.portal.mvc.validator;

import lv.lu.newsfeed.interfaces.service.UserService;
import lv.lu.newsfeed.model.jpa.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for "Registration" form.
 * 
 * This bean is defined in Spring MVC configuration file: 
 * \lab02-newsfeed\lab02-newsfeed-portal\src\main\webapp\WEB-INF\newsfeed-servlet.xml
 */
public class RegistrationValidator implements Validator{

    private UserService userService;
    
    @Autowired
    public RegistrationValidator(UserService userService) {
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
        if (user.getUsername().trim().equals("")){
            errors.rejectValue("username", "error.empty", "Empty username!");
            return;
        }
        if (user.getPassword().trim().equals("")){
            errors.rejectValue("password", "error.empty", "Empty password!");
            return;
        }
        if (user.getPasswordConfirm().trim().equals("")){
            errors.rejectValue("passwordConfirm", "error.empty", "Empty password confirm!");
            return;
        }
        if (user.getFirstName().trim().equals("")){
            errors.rejectValue("firstName", "error.empty", "Empty first name!");
            return;
        }
        if (user.getLastName().trim().equals("")){
            errors.rejectValue("lastName", "error.empty", "Empty last name!");
            return;
        }
                    
        // check if username is free
        User userFromDB = userService.getByUsername(user.getUsername());                
        if (userFromDB != null){            
            errors.rejectValue("username", "error.empty", "User with this username already exists!");
        }       
    }
}