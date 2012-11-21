package fr.appendices.data;

import java.util.List;

import com.google.appengine.api.datastore.Entity;

import fr.appendices.util.Util;

/**
 * This class handles CRUD operations related to Fragment entity. This class is used to improve Sequence writing operations.
 */

public class Fragment {

    public static final int fragmentMaxSize = 18; // optimized size sequenceTime / maxWorkTime
    
    /**
     * Create or update Fragment (including a List of Works to be played).
     * @return
     */
    public static Entity createFragment(String token, int id, DisplayTypeEnum type, List<PlayWorkBean> playWorkBeans) {
	Entity fragment = new Entity("Fragment");
	fragment.setProperty("token", token);
	fragment.setProperty("id", id);
	fragment.setProperty("seqType", type.name());
	int i = 0;
	for (PlayWorkBean playWorkBean : playWorkBeans) {
	    StringBuilder timeStr = new StringBuilder("seqTime");
	    timeStr.append(i);
	    fragment.setProperty(timeStr.toString(), playWorkBean.getTime());
	    StringBuilder fkStr = new StringBuilder("fk");
	    fkStr.append(i);	    
	    fragment.setProperty(fkStr.toString(), playWorkBean.getWorkKey());
	    i++;
	}
	
	Util.persistEntity(fragment);
	return fragment;
    }

    /**
     * Get the fragment by token, return an Iterable
     * @param token String
     * @return Fragment Entity
     */
    public static Iterable<Entity> getFragmentByToken(String token) {
	Iterable<Entity> entities = Util.listEntities("Fragment", "token", token, null, null, "id");
	return entities;
    }

}
