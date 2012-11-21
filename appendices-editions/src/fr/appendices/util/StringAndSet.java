package fr.appendices.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Utility class with support of mapping of character array to set.
 */
public final class StringAndSet {

    /**
     * default item separator character.
     */
    private static final char DEFAULT_ITEM_SEPARATOR = '|';

    /**
     * default escape separator.
     */
    private static final char DEFAULT_ESCAPE_CHARACTER = '&';

    private StringAndSet() {

    }

    /**
     * Convert array of characters (string) to set. The individual set items separated by iSep. Escape character is used to specify iSep in character array.
     * E.g.: "value1|value2" will be converted to set of size 2 with content: ["value1", "value2"]
     * @param string the character array representing string to be converted
     * @param iSep item separator character
     * @param escape escape character
     * @return Set representing set mapping of given character array or empty set.
     */
    private static Set<String> toSet(final char[] string, final char iSep, final char escape) {
	final Set<String> retVal = new HashSet<String>();
	int ival1 = 0, ival2 = -1;
	boolean wasEscape = false;
	for (int i = 0; i < string.length; i++) {
	    if (string[i] == escape) {
		wasEscape = true; // remember there was at least on escape
		i++; // skip char that is being escaped
		continue;
	    }
	    if (string[i] == iSep)
		ival2 = i; // remember position of value separator
	    else if (i == string.length - 1)
		ival2 = string.length;
	    if (ival2 > ival1) { // we have found value
		final String value = new String(string, ival1, ival2 - ival1);
		if (wasEscape) {
		    removeCharacter(value, escape);
		    wasEscape = false;
		}
		ival1 = i + 1;
		ival2 = -1;
		retVal.add(value);
	    }
	}
	return retVal;
    }

    /**
     * Remove all occurences of (escape) character in the string. This helper method was inspired by http://www.rgagnon.com/javadetails/java-0030.html
     * @param s string to remove character from
     * @param c character to be removed from the string
     * @return the string with all occurences of the character removed
     */
    private static String removeCharacter(final String s, final char c) {
	final StringBuffer buffer = new StringBuffer();
	for (int i = 0; i < s.length(); i++) {
	    if (s.charAt(i) == c) {
		if (i == s.length() - 1)
		    break; // end of string
		i++; // skip escape, but preserve if double escape
	    }
	    buffer.append(s.charAt(i));
	}
	return buffer.toString();
    }

    /**
     * Convert array of characters (string) to map. The individual map items separated by defaultSeparator, key/value in each item is separated by
     * defaultKeySeparator. Escape character used is defaultEscape
     * @param chars array of characters (String) to be converted to map
     * @return Map mpa representation of the passed array of characters
     * @see StringAndSet#toMap(char[], char, char, char)
     */
    private static Set<String> toSet(final char[] chars) {
	return toSet(chars, DEFAULT_ITEM_SEPARATOR, DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     * Creates a Map from a String.
     * @param s String
     * @return Map<String, String>
     */
    public static Set<String> string2Set(final String s) {
	return toSet(s.toCharArray());
    }

    /**
     * Creates a String from a Set<String>.
     * @param s Set<String>
     * @param iSep char
     * @return String
     */
    private static String set2String(final Set<String> s, final char iSep) {

	if (s == null || s.isEmpty())
	    return null;

	final StringBuilder strBuilder = new StringBuilder();
	final Iterator<String> iter = s.iterator();

	int count = 0;

	while (iter.hasNext()) {
	    if (count > 0)
		strBuilder.append(iSep);
	    strBuilder.append(iter.next());
	    count++;
	}

	return strBuilder.toString();
    }

    /**
     * Creates a String from a Set<String>.
     * @param s Set<String>
     * @return String
     */
    public static String set2String(final Set<String> s) { // NO_UCD
	return set2String(s, DEFAULT_ITEM_SEPARATOR);
    }

}
