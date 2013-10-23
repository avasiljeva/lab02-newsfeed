package lv.lu.newsfeed.impl;

import java.util.Collection;
import java.util.List;

import lv.lu.newsfeed.interfaces.JpaDAO;
import lv.lu.newsfeed.model.jpa.PersistentEntity;
import lv.lu.newsfeed.model.jpa.PersistentEntityType;

import org.springframework.transaction.annotation.Transactional;

/**
 * JPA (Java Persistence API) DAO implementation.
 * For JavaDocs see JpaDAO interface.
 */

@Transactional
public class JpaDAOImpl extends AbstractDAOImpl implements JpaDAO {
	
	public void save(PersistentEntity object) {
		if (object.getId() == null) {
			getEntityManager().persist(object);
		} else {
			getEntityManager().merge(object);
		}
	}
	
	public void saveAll(Collection<? extends PersistentEntity> objects) {
		for (PersistentEntity object: objects){
			save(object);
		}
	}

	public <T extends PersistentEntity> T getById(Class<T> clazz, Long id) {
		return getEntityManager().find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends PersistentEntity> List<T> findAll(Class<T> clazz) {
		String entity = clazz.getSimpleName();
		return getEntityManager().createQuery("select e FROM " + entity + " e").getResultList();
	}

	public void delete(PersistentEntity object) {
		getEntityManager().remove(object);
	}
	
	public void cleanupDB(){
		// delete everything from USER table
		List<? extends PersistentEntity> objects = findAll(PersistentEntityType.USER.getObjectClass());
		for (PersistentEntity o: objects){
			delete(o);
		}
		
		// TODO [task]: all domain model objects have to be deleted from database
		// Your task is to make all domain model objects persistent entities.
		// Do not forget to adjust this method to cleanup tables for all persistent entities.
		// [Hint]: try to make the following code work:
//		for (PersistentEntityType clazz: PersistentEntityType.values()){
//			List<? extends PersistentEntity> objects = findAll(clazz.getObjectClass());
//			for (PersistentEntity o: objects){
//				delete(o);
//			}
//		}
		System.out.println("Database is cleaned");
	}
}
