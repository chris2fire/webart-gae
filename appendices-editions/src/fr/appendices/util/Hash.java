package fr.appendices.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Properties;

/**
 * Provides various hash methods.
 */
public final class Hash {

	private static final String PROP_FILE = "/util.properties";

	// Set default algo and provider
	private final transient String defaultProvider;

	private final transient String defaultAlgorithm;

	private final transient String sha1Provider;

	private final transient String sha1Algorithm;

	private final transient String md5Provider;

	private final transient String md5Algorithm;

	// For singleton pattern
	private static Hash instance;

	/**
	 * Public access so it is easy to know the default algorithm.
	 * 
	 * @return String
	 */
	public String getDefaultAlgorithm() {
		return this.defaultAlgorithm;
	}

	/**
	 * Constructor (initialize properties).
	 * 
	 * @throws IOException
	 */
	private Hash() throws IOException {
		final Properties prop = new Properties();
		prop.load(Hash.class.getResourceAsStream(PROP_FILE));
		this.defaultProvider = prop.getProperty("Hash.defaultProvider");
		this.defaultAlgorithm = prop.getProperty("Hash.defaultAlgorithm");
		this.sha1Provider = prop.getProperty("Hash.sha1Provider");
		this.sha1Algorithm = prop.getProperty("Hash.sha1Algorithm");
		this.md5Provider = prop.getProperty("Hash.md5Provider");
		this.md5Algorithm = prop.getProperty("Hash.md5Algorithm");
	}

	/**
	 * Return the Hash Instance.
	 * 
	 * @return Hash
	 * @throws IOException
	 *             IOException
	 */
	public static Hash getInstance() throws IOException {
		if (Hash.instance == null)
			synchronized (Hash.class) {
				newInstance();
			}
		return Hash.instance;
	}

	private static void newInstance() throws IOException {
		if (Hash.instance == null)
			Hash.instance = new Hash();
	}

	/**
	 * We do not want our singleton to have clones.
	 * 
	 * @return Object
	 * @throws CloneNotSupportedException
	 *             Clone is not allowed
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone is not allowed.");
	}

	/**
	 * The method used to hash a message (using SHA-2 512 bits algorithm).
	 * 
	 * @param message
	 *            .
	 * @return The digest .
	 * @throws NoSuchProviderException .
	 * @throws NoSuchAlgorithmException .
	 */

	public byte[] digest(final String message) throws NoSuchAlgorithmException,
			NoSuchProviderException {
		if (message == null)
			return null;
		// Get messageDigest instance
		final MessageDigest messageDigest = MessageDigest.getInstance(
				this.defaultAlgorithm, this.defaultProvider);
		// Hash the message
		return messageDigest.digest(message.getBytes());
	}

	/**
	 * Use this method if you really need SHA1 hash WARNING : SHA11 is
	 * considered to have security flaws, if possible you should use the
	 * digest() method which uses the default strong algorithm.
	 * 
	 * @param message
	 *            .
	 * @return The digest .
	 * @throws NoSuchProviderException .
	 * @throws NoSuchAlgorithmException .
	 */

	public byte[] sha1Digest(final String message)
			throws NoSuchAlgorithmException, NoSuchProviderException {
		if (message == null)
			return null;
		// Get messageDigest instance
		final MessageDigest messageDigest = MessageDigest.getInstance(
				this.sha1Algorithm, this.sha1Provider);
		// Hash the message
		return messageDigest.digest(message.getBytes());

	}

	/**
	 * Use this method if you really need MD5 hash WARNING : SHA11 is considered
	 * to be INSECURED, if possible you should use the digest() method which
	 * uses the default strong algorithm.
	 * 
	 * @param message
	 *            .
	 * @return The digest .
	 * @throws NoSuchProviderException .
	 * @throws NoSuchAlgorithmException .
	 */

	public byte[] md5Digest(final String message)
			throws NoSuchAlgorithmException, NoSuchProviderException {
		if (message == null)
			return null;
		// Get messageDigest instance
		final MessageDigest messageDigest = MessageDigest.getInstance(
				this.md5Algorithm, this.md5Provider);
		// Hash the message
		return messageDigest.digest(message.getBytes());
	}

}
