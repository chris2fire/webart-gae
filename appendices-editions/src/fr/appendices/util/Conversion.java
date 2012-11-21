package fr.appendices.util;

/**
 * Utility class used for various conversions.
 */
public final class Conversion {

	private static final int MASK = 0xFF;

	/**
	 * Do not instantiate.
	 */
	private Conversion() {
	}

	/**
	 * Return the Hash String corresponding to the given byte[].
	 * 
	 * @param hexBytes
	 *            The bytes we want to convert.
	 * @return The string representation of the hexBytes
	 */
	public static String hex2String(final byte[] hexBytes) {
		if (hexBytes == null)
			return null;
		final StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hexBytes.length; i++)
			hexString.append(Integer.toHexString(MASK & hexBytes[i]));
		return hexString.toString();
	}

}
