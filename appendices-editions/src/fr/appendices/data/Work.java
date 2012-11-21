package fr.appendices.data;

import java.io.IOException;
import java.util.Properties;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import fr.appendices.util.Hash;
import fr.appendices.util.Util;

/**
 * This class handles CRUD operations related to Work entity.
 */

public class Work {

    private static final String PROP_FILE = "/auth.properties";
    
    /**
     * Create or update Work.
     * @param title
     * @param author
     * @param type
     * @param value
     * @param time
     * @param authToken
     * @return
     */
    public static Entity createOrUpdateWork(String title, String author, WorkTypeEnum type, String value, Long time, String authToken) {

	Entity work = null;
	
	final Properties prop = new Properties();
	try {
	    prop.load(Hash.class.getResourceAsStream(PROP_FILE));
	} catch (IOException e) {
	    return null;
	}
	
	String validToken = prop.getProperty("work.authToken");
	
	// Not authorized to process create/update operation
	if (validToken != null && !validToken.equals(authToken))
	    return null;
	
	if (work == null) {
	    work = new Entity("Work");
	    work.setProperty("title", title);
	    work.setProperty("author", author);
	    work.setProperty("type", type.name());
	    work.setProperty("value", value);
	    work.setProperty("time", time);
	} else {
	    if (work != null) {
		if (title != null && !"".equals(title))
		    work.setProperty("title", title);
		if (value != null)
		    work.setProperty("value", value);
		if (time != null)
		    work.setProperty("time", time);
	    }
	}
	
	Util.persistEntity(work);
	return work;
    }

    /**
     * get All the works in the list
     * @param kind : work kind
     * @return all the works
     */
    public static Iterable<Entity> getAllWorks() {
	Iterable<Entity> entities = Util.listEntities("Work", null, null, null, null, null);
	return entities;
    }

    /**
     * get Work with work id
     * @param id : get id
     * @return work entity
     */
    public static Entity getWork(long id) {
	Key key = KeyFactory.createKey("Work", id);
	return Util.findEntity(key);
    }

    public static String deleteWork(long id) {
	Entity entity = getWork(id);
	if (entity != null) {
	    Util.deleteEntity(entity.getKey());
	    return ("Work deleted successfully.");
	} else
	    return ("Work not found");
    }
}
