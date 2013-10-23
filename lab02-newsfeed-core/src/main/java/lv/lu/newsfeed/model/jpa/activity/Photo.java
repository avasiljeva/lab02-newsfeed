package lv.lu.newsfeed.model.jpa.activity;

/**
 * Business entity representing a photo loaded by a user
 */
public class Photo extends Activity  {

    /* Image source */
    private String src;
    
    /* Author comment */
    private String comment;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    } 
}
