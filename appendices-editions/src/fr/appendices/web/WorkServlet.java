package fr.appendices.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;

import fr.appendices.data.DisplayTypeEnum;
import fr.appendices.data.Sequence;
import fr.appendices.data.Work;
import fr.appendices.data.WorkTypeEnum;
import fr.appendices.util.Random;

/**
 * This servlet responds to the request corresponding to works. The class creates and manages the WorkEntity
 */
@SuppressWarnings("serial")
public class WorkServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(WorkServlet.class.getCanonicalName());

    private static final int sequenceTime = 120000; // 2 minutes in ms

    private static final int minWorkTime = 5000; // 5s in ms

    private static final int maxWorkTime = 15000; // 15s in ms

    /**
     * Searches for the entity based on the search criteria and returns the token linked to the result.
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	super.doGet(req, resp);

	String token = null;

	Iterable<Entity> iterable = Work.getAllWorks();

	Iterator<Entity> iter = iterable.iterator();

	List<Entity> sortedList = new LinkedList<Entity>();

	while (iter.hasNext()) {
	    sortedList.add(iter.next());
	}

	if (!sortedList.isEmpty()) {

	    long leftTime = sequenceTime; // in ms
	    int leftId = 0;

	    long rightTime = sequenceTime; // in ms
	    int rightId = 0;

	    try {

		// init sequence token
		token = Random.getInstance().getUniqueSessionId();
		logger.log(Level.INFO, "Generating a new Sequence with token[" + token + "]");

		while ((leftTime > 0 || rightTime > 0) && sortedList.size() > 1) {

		    if (rightTime > 0) {
			rightTime = rightTime - computeAndReturnTime(rightTime, sortedList, token, rightId, DisplayTypeEnum.R);
			rightId++;
		    }

		    if (leftTime > 0) {
			leftTime = leftTime - computeAndReturnTime(leftTime, sortedList, token, leftId, DisplayTypeEnum.L);
			leftId++;
		    }

		}

	    } catch (NoSuchAlgorithmException e) {
		logger.log(Level.WARNING, e.getMessage());
	    } catch (NoSuchProviderException e) {
		logger.log(Level.WARNING, e.getMessage());
	    } catch (IllegalArgumentException e) {
		logger.log(Level.WARNING, e.getMessage());
	    } catch (IOException e) {
		logger.log(Level.WARNING, e.getMessage());
	    }

	    PrintWriter out = resp.getWriter();
	    out.println(token);
	    return;
	}

	return;

    }

    /**
     * Retrieve
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     */
    private long computeAndReturnTime(long remainTime, List<Entity> works, String token, int id, DisplayTypeEnum type) throws NoSuchAlgorithmException,
	    NoSuchProviderException, IllegalArgumentException, IOException {

	int sortedSize = works.size();

	// randomize for getting work in the list
	int randomId = Random.getInstance().getNumericRandomInt(sortedSize);
	// get work
	Entity currentWork = works.remove(randomId);
	long randomWorkTime = 0;
	// music case
	if (WorkTypeEnum.MUSIC.name().equals(currentWork.getProperty("type"))) {
	    Long musicTime = (Long) currentWork.getProperty("time");
	    randomWorkTime = musicTime.longValue();
	} else {
	    // randomize for work time displaying
	    randomWorkTime = minWorkTime + Random.getInstance().getNumericRandomInt(maxWorkTime);
	}
	// avoid randomWorkTime to be superior at remain time
	if (remainTime < randomWorkTime) {
	    randomWorkTime = remainTime;
	}

	// save sequence
	Sequence.createSequence(token, id, Long.valueOf(randomWorkTime), type, currentWork);

	return randomWorkTime;

    }

    /**
     * Creates entity and persists the same
     */
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	logger.log(Level.INFO, "doPut not supported yet");
    }

    /**
     * Delete the entity from the datastore. Throws an exception if there are any orders associated with the item and ignores the delete action for it.
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	logger.log(Level.INFO, "doDelete not supported yet");
    }

    /**
     * Redirects to delete or insert entity based on the action in the HTTP request.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	logger.log(Level.INFO, "Creating Work");
	String title = req.getParameter("title");
	String author = req.getParameter("author");
	String typeStr = req.getParameter("type");
	String value = req.getParameter("value");
	String timeStr = req.getParameter("time");
	String authToken = req.getParameter("authToken");
	WorkTypeEnum type = null;
	Long time = null;
	try {
	    time = Long.valueOf(timeStr);
	    type = WorkTypeEnum.valueOf(typeStr);
	} catch (Exception e) {
	    // NOTHING
	    time = Long.valueOf(0);
	    type = WorkTypeEnum.IMAGE;
	}
	Work.createOrUpdateWork(title, author, type, value, time, authToken);

	resp.sendRedirect("add.html");
    }
}