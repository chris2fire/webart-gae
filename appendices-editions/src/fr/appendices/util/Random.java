package fr.appendices.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Properties;

import fr.appendices.util.OSDetection;

/**
 * Provides various methods for accessing random data.
 */
public final class Random {

    private static final String PROP_FILE = "/util.properties";

    // Max value exclusive
    private static final int PSEUDO_RANDOM_MAX = 9;

    // Set default algo and provider :
    private final transient String defaultProvider;

    private final transient String defaultAlgorithm;

    // For singleton pattern
    private static Random instance;

    /**
     * Public access so it is easy to know the default algorithm.
     * @return String
     */
    public String getDefaultAlgorithm() {
	return this.defaultAlgorithm;
    }

    /**
     * Constructor (initialize properties).
     * @throws IOException
     */
    private Random() throws IOException {
	final Properties prop = new Properties();
	prop.load(Random.class.getResourceAsStream(PROP_FILE));

	if (OSDetection.isWindows()) {
	    this.defaultProvider = prop.getProperty("Random.defaultProvider.windows");
	    this.defaultAlgorithm = prop.getProperty("Random.defaultAlgorithm.windows");
	} else {
	    this.defaultProvider = prop.getProperty("Random.defaultProvider.linux");
	    this.defaultAlgorithm = prop.getProperty("Random.defaultAlgorithm.linux");
	}

    }

    /**
     * Return the HashService Instance.
     * @return Random
     * @throws IOException IOException
     */
    public static Random getInstance() throws IOException {
	if (Random.instance == null)
	    synchronized (Random.class) {
		newInstance();
	    }
	return Random.instance;
    }

    private static void newInstance() throws IOException {
	if (Random.instance == null)
	    Random.instance = new Random();
    }

    /**
     * We do not want our singleton to have clones.
     * @return Object
     * @throws CloneNotSupportedException Clone is not allowed
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
	throw new CloneNotSupportedException("Clone is not allowed.");
    }

    /**
     * Return a random generator.
     * @return SecureRandom
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     */
    private SecureRandom getRandomGenerator() throws NoSuchAlgorithmException, NoSuchProviderException {
	return SecureRandom.getInstance(this.defaultAlgorithm, this.defaultProvider);
    }

    /**
     * Return a string containing 'stringLenght' random integers.
     * @param stringLength int
     * @return String
     * @throws IllegalArgumentException stringLength should be a positive integer
     * @throws NoSuchProviderException Provider does not exist
     * @throws NoSuchAlgorithmException Algorithm does not exist
     */
    public String getNumericRandomString(final int stringLength) throws NoSuchAlgorithmException, NoSuchProviderException, IllegalArgumentException {
	final StringBuffer numericRandom = new StringBuffer();
	if (stringLength < 1)
	    throw new IllegalArgumentException("stringLength should be a positive integer");

	final SecureRandom randomGenerator = this.getRandomGenerator();

	for (int i = 0; i < stringLength; i++)
	    numericRandom.append(String.valueOf(randomGenerator.nextInt(PSEUDO_RANDOM_MAX)));
	return numericRandom.toString();

    }

    /**
     * Return a random int from 0 to max-1 values.
     * @param maxValue int
     * @return int
     * @throws IllegalArgumentException stringLength should be a positive integer
     * @throws NoSuchProviderException Provider does not exist
     * @throws NoSuchAlgorithmException Algorithm does not exist
     */
    public int getNumericRandomInt(final int maxValue) throws NoSuchAlgorithmException, NoSuchProviderException, IllegalArgumentException {
	if (maxValue < 1)
	    throw new IllegalArgumentException("maxValue should be a positive integer");
	final SecureRandom randomGenerator = this.getRandomGenerator();
	return randomGenerator.nextInt(maxValue);
    }

    /**
     * @return a 64 digits unique String (48 random + timestamp).
     * @throws IllegalArgumentException stringLength should be a positive integer
     * @throws NoSuchProviderException Provider does not exist
     * @throws NoSuchAlgorithmException Algorithm does not exist
     */
    public String getUniqueSessionId() throws NoSuchAlgorithmException, NoSuchProviderException, IllegalArgumentException {
	final String timestamp = String.valueOf(System.currentTimeMillis());
	final int padding = 64 - timestamp.length();
	return getNumericRandomString(padding) + timestamp;
    }

}
