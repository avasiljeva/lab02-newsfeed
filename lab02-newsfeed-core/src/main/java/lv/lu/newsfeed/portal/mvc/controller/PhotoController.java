package lv.lu.newsfeed.portal.mvc.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import lv.lu.newsfeed.portal.mvc.bean.FileUploadBean;


import lv.lu.newsfeed.model.jpa.User;
import lv.lu.newsfeed.model.jpa.activity.Photo;
import lv.lu.newsfeed.portal.mvc.Constants;
import lv.lu.newsfeed.portal.mvc.tmp.InMemoryTmpStorage;

/**
 * Controller for handling user photos on a tab "Photos" of Home page.
 * See HomePageController.
 */
@Controller
public class PhotoController {
    
    private static final String VIEW = "home/photos";
    
    // it is not good practice to store photos in web app folder, just for demo
    private static final String PATH = System.getProperty("user.dir") + "/src/main/webapp/images";
    private static final String SEPARATOR = "/";
    
    private static final String MODEL_FILEUPLOAD = "fileUpload";
    
    /**
     * Load data for Photos tab and display it
     */
    @RequestMapping(value = "/" + VIEW, method = RequestMethod.GET)
    public String displayPhotos(ModelMap model, HttpSession session) {
        
        User user = (User)session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        
        // TODO: this is temporary data storage (just for demo), replace InMemoryTmpStorage by real persistence
        List<Photo> photos = InMemoryTmpStorage.getInstance().getPhotos(user.getId()); 
        model.addAttribute("photos", photos);
        
        model.addAttribute(MODEL_FILEUPLOAD, new FileUploadBean());
        model.addAttribute(HomePageController.ATTR_SELECTED_TAB, "photos");        
        return HomePageController.HOME_VIEW;
    }
    
    @RequestMapping(value = "/photo", method = RequestMethod.POST)
    public String uploadPhoto(HttpSession session, ModelMap model, 
            @ModelAttribute(MODEL_FILEUPLOAD) FileUploadBean fileUploadBean,
            BindingResult result) {

        User user = (User)session.getAttribute(Constants.SessionAttribute.SESSION_USER);
        
        boolean uploadSuccess = false;
        if (fileUploadBean != null){
            MultipartFile file = fileUploadBean.getFile();
            if (file != null && !file.isEmpty()) {
                try {
                    String filePath = user.getUsername() + SEPARATOR + generateFileName();
                    File destination = new File(PATH + SEPARATOR + filePath);
                    if (!destination.exists()){
                        destination.mkdirs();
                    }
                    file.transferTo(destination);
                    
                    Photo photo = new Photo();
                    photo.setAuthor(user);
                    photo.setComment(fileUploadBean.getComment());
                    photo.setCreated(new Date());
                    photo.setSrc(filePath);
                    
                    // TODO: temporary (just for demo) store posts in in-memory storage
                    // it has to be replaced by persisting data in relational database
                    InMemoryTmpStorage.getInstance().addPhoto(photo);
                    
                    uploadSuccess = true; 
                } 
                catch (Exception e) {
                    e.printStackTrace();                    
                }
            }
        }
        
        if (uploadSuccess) {            
            return "redirect:" + VIEW;
        } 
        else {
            result.rejectValue("file", "error.fileUploadError", "Cannot upload file!");
            model.addAttribute(HomePageController.ATTR_SELECTED_TAB, "photos");        
            return HomePageController.HOME_VIEW;
        }
    }
    
    private static String generateFileName(){
        Random r = new Random();
        String random = Long.toString(Math.abs(r.nextLong()), 36);
        return random.substring(0,8);
    }
}
