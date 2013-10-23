package lv.lu.newsfeed.portal.mvc.tmp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lv.lu.newsfeed.model.jpa.activity.Photo;
import lv.lu.newsfeed.model.jpa.activity.Post;

/**
 * Temporary in-memory data storage created JUST FOR DEMO purpose.
 * 
 * TODO: completely remove this class and store data in database instead!
 */
public class InMemoryTmpStorage {
    
    private static final InMemoryTmpStorage INSTANCE = new InMemoryTmpStorage();
    
    private InMemoryTmpStorage(){}
    
    // list of posts by user
    private Map<Long, List<Post>> posts = new HashMap<Long, List<Post>>();
    
    // list of photos by user
    private Map<Long, List<Photo>> photos = new HashMap<Long, List<Photo>>();
    
    public static InMemoryTmpStorage getInstance(){
        return INSTANCE;
    }
    
    public List<Post> getPosts(Long userId){
        return posts.get(userId);
    }
    
    public void addPost(Post post){
        Long authorId = post.getAuthor().getId();
        List<Post> userPosts = posts.get(authorId);
        if (userPosts == null){
            userPosts = new LinkedList<Post>();
            posts.put(authorId, userPosts);
        }
        userPosts.add(0, post);
    }
    
    public List<Photo> getPhotos(Long userId){
        return photos.get(userId);
    }
    
    public void addPhoto(Photo photo){
        Long authorId = photo.getAuthor().getId();
        List<Photo> userPhotos = photos.get(authorId);
        if (userPhotos == null){
            userPhotos = new LinkedList<Photo>();
            photos.put(authorId, userPhotos);
        }
        userPhotos.add(0, photo);
    }
}
