package fr.appendices.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;

import fr.appendices.data.Sequence;
import fr.appendices.util.Util;

/**
 * This servlet responds to the request corresponding to works. Play an existing Sequence
 */
@SuppressWarnings("serial")
public class PlayServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(PlayServlet.class.getCanonicalName());

    /**
     * Searches for the entity based on the search criteria and returns result in JSON format
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	super.doGet(req, resp);

	String s = req.getParameter("s");
	
	logger.log(Level.INFO, "Obtaining Sequence");
	
	if (s != null && s.length() > 0) {
	    Iterable<Entity> entities = Sequence.getSequencesByToken(s);
	    PrintWriter out = resp.getWriter();
	    out.println(Util.writeJSON(entities, "Work", "fk"));
	}

    }

}