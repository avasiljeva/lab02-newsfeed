package lv.lu.newsfeed.model.jpa;

import lv.lu.newsfeed.model.jpa.activity.Photo;
import lv.lu.newsfeed.model.jpa.activity.Post;
import lv.lu.newsfeed.model.jpa.activity.Share;

/**
 * This is a registry of persistent (in scope SQL database) objects in a system.
 * It is used in:
 *  	- JpsDAOImpl.cleanupDB() to delete everything from DB.
 *  	- JpsDAOImplTest to create instances of persistent objects
 */
public enum PersistentEntityType 
{
    POST(Post.class, null),
    SHARE(Share.class, null),
    PHOTO(Photo.class, null),
    USER(User.class, null);	
	
	PersistentEntityType(Class<? extends PersistentEntity> clazz, Class<? extends PersistentEntity>[] subClasses){
		this.clazz = clazz;
		this.subClasses = subClasses;
	}
	
	private Class<? extends PersistentEntity> clazz;
	
	public Class<? extends PersistentEntity> getObjectClass(){
		return this.clazz;
	}
	
	private Class<? extends PersistentEntity>[] subClasses;
	
	public Class<? extends PersistentEntity>[] getSubClasses() {
		return subClasses;
	}
}
