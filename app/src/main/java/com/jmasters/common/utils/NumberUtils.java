/**
 * 
 */
package com.jmasters.common.utils;

import java.util.Random;

/**
 * @author alexb
 * 
 */
public class NumberUtils {

	public static int getRandomIntegerInRange(int min, int max) {
		Random random = new Random();
		if (min > max) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) max - (long) min + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * random.nextDouble());
		int randomNumber = (int) (fraction + min);
		return randomNumber;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getRandomIntegerInRange(0, 10));
		}
		System.exit(0);
	}

}
