package lv.lu.newsfeed.portal.mvc.bean;

/**
 * Form backing bean for Photo Upload.
 */
import org.springframework.web.multipart.MultipartFile;

public class FileUploadBean {

    private MultipartFile file;
    
    private String comment;

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
