package fr.appendices.data;

import com.google.appengine.api.datastore.Entity;

import fr.appendices.util.Util;

/**
 * This class handles CRUD operations related to Sequence entity.
 */

public class Sequence {

    /**
     * Create or update Sequence for a particular future user.
     * @return
     */
    public static Entity createSequence(String token, int id, Long time, DisplayTypeEnum type, Entity work) {
	Entity sequence = new Entity("Sequence");
	sequence.setProperty("token", token);
	sequence.setProperty("id", id);
	sequence.setProperty("seqTime", time);
	sequence.setProperty("seqType", type.name());
	sequence.setProperty("fk", work.getKey());
	Util.persistEntity(sequence);
	return sequence;
    }

    /**
     * get All the sequences in the list
     * @param kind : sequence kind
     * @return all the sequences
     */
    public static Iterable<Entity> getAllSequences() {
	Iterable<Entity> entities = Util.listEntities("Sequence", null, null, null, null, null);
	return entities;
    }

    /**
     * Get the sequence by isNewsletter, return an Iterable
     * @param isNewsletter : isNewsletter
     * @return Sequence Entity
     */
    public static Iterable<Entity> getSequencesByToken(String token) {
	Iterable<Entity> entities = Util.listEntities("Sequence", "token", token, null, null, "id");
	return entities;
    }

}
