package com.ft.utils;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import java.util.Random;

/**
 * 随机数生成
 * 
 */
public class RandomNum {
	private Long randomnum = null;

	private Float randomfloat = null;

	private boolean floatvalue = false;

	private long upper = 100;

	private long lower = 0;

	private String algorithm = null;

	private String provider = null;

	private boolean secure = false;

	private Random random = null;

	private SecureRandom secrandom = null;

	/**
	 * 随机的下一个浮点型数
	 * 
	 * @return
	 */
	private final float getFloat() {
		if (random == null) {
			return secrandom.nextFloat();
		} else {
			return random.nextFloat();
		}
	}

	/**
	 * 产生随机数的对象
	 * 
	 * @throws Exception
	 */
	public final void generateRandomObject() throws Exception {
		// check to see if the object is a SecureRandom object
		if (secure) {
			try {
				// get an instance of a SecureRandom object
				if (provider != null) {
					// search for algorithm in package provider
					secrandom = SecureRandom.getInstance(algorithm, provider);
				} else {
					secrandom = SecureRandom.getInstance(algorithm);
				}
			} catch (NoSuchAlgorithmException ne) {
				throw new Exception(ne.getMessage());
			} catch (NoSuchProviderException pe) {
				throw new Exception(pe.getMessage());
			}
		} else {
			random = new Random();
		}
	}

	/**
	 * 产生随机数
	 */
	private final void generaterandom() {
		// check to see if float value is expected
		if (floatvalue) {
			randomfloat = new Float(getFloat());
		} else {
			randomnum = new Long(lower
					+ (long) ((getFloat() * (upper - lower))));
		}
	}

	/**
	 * 得到随机数字
	 * 
	 * @return
	 */
	public final Number getRandom() {
		try {
			if (secure) {
				if (secrandom == null) {
					generateRandomObject();
				}
			} else {
				if (random == null) {
					generateRandomObject();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		generaterandom();

		if (floatvalue) {
			return randomfloat;
		} else {
			return randomnum;
		}
	}

	/**
	 * 设置随机数的范围值
	 * 
	 * @param low
	 * @param up
	 */
	public final void setRange(long low, long up) {
		// set the upper and lower bound of the range
		lower = low;
		upper = up;

		// check to see if a float value is expected
		if ((lower == 0) && (upper == 1)) {
			floatvalue = true;
		}
	}

	/**
	 * set the algorithm name
	 * 
	 * @param value
	 *            name of the algorithm to use for a SecureRandom object
	 */
	public final void setAlgorithm(String value) {
		algorithm = value;
		secure = true; // a SecureRandom object is to be used
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param value
	 *            DOCUMENT ME!
	 */
	public final void setProvider(String value) {
		provider = value;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param value
	 *            DOCUMENT ME!
	 * 
	 * @throws Exception
	 *             DOCUMENT ME!
	 */
	public final void setRange(String value) throws Exception {
		try {
			upper = new Integer(value.substring(value.indexOf('-') + 1))
					.longValue();
		} catch (Exception ex) {
			throw new Exception("upper attribute could not be"
					+ " turned into an Integer default value was used");
		}

		try {
			lower = new Integer(value.substring(0, value.indexOf('-')))
					.longValue();
		} catch (Exception ex) {
			throw new Exception("lower attribute could not be"
					+ " turned into an Integer default value was used");
		}

		if ((lower == 0) && (upper == 1)) {
			floatvalue = true;
		}

		if (upper < lower) {
			throw new Exception("You can't have a range where the lowerbound"
					+ " is higher than the upperbound.");
		}
	}
}
