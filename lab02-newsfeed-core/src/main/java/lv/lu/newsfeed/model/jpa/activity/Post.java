package lv.lu.newsfeed.model.jpa.activity;

/**
 * Business entity representing a user post
 */
public class Post extends Activity {
    
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
