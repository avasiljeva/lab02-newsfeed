package lv.lu.newsfeed.model.jpa.activity;

/**
 * Business entity representing a content shared by a user
 */
public class Share extends Activity {

    /* Shared content */
    private String content;
    
    /* Author comment */
    private String comment;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
