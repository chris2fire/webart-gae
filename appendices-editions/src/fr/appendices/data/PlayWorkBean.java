package fr.appendices.data;

import com.google.appengine.api.datastore.Key;

/**
 * This class defines bean representing a work to be played (with an id, a play time and a work entity key).
 */

public class PlayWorkBean {

    private Long time;
    
    private Key workKey;

    public PlayWorkBean(Long time, Key workKey) {
	super();
	this.time = time;
	this.workKey = workKey;
    }
    
    /**
     * @return the time
     */
    public Long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * @return the workKey
     */
    public Key getWorkKey() {
        return workKey;
    }

    /**
     * @param workKey the workKey to set
     */
    public void setWorkKey(Key workKey) {
        this.workKey = workKey;
    }

}
