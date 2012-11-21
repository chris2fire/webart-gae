package fr.appendices.util;

/**
 * Detect operating system (needs access to os.name system property).
 */
public final class OSDetection {

	/**
	 * Do not instantiate.
	 */
	private OSDetection() {

	}

	/**
	 * Is it a windows OS.
	 * 
	 * @return boolean
	 */
	public static boolean isWindows() {
		final String os = System.getProperty("os.name").toLowerCase();
		// windows
		return os.indexOf("win") >= 0;
	}

	/**
	 * Is it a MAC OS.
	 * 
	 * @return boolean
	 */
	public static boolean isMac() {
		final String os = System.getProperty("os.name").toLowerCase();
		// Mac
		return os.indexOf("mac") >= 0;
	}

	/**
	 * Is it a an Unix or Linux OS.
	 * 
	 * @return boolean
	 */
	public static boolean isUnix() {
		final String os = System.getProperty("os.name").toLowerCase();
		// linux or unix
		return os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0;

	}
}
