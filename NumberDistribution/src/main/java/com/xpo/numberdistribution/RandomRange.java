package com.xpo.numberdistribution;

import java.util.Random;

/**
 * generates a range of random integers.
 * 
 * @author lweyri
 *
 */
public class RandomRange
{
	// The minimum number to generate.
	private int min;
	
	// The maximum number to generate.
	private int max;
	
	// The random object used to generate the initial number.
	private Random random;

	/**
	 * initializes the min and max values.
	 * 
	 * @param min the minimum number to generate.
	 * @param max the maximum number to generate.
	 */
	public RandomRange(int min, int max)
	{
		this.min = min;
		this.max = max;
		random = new Random();
	}
	
	/**
	 * Generates a new random number based on min and max.
	 * 
	 * @return new number
	 */
	public int getNextRandomInt()
	{
		return random.nextInt((max - min)+1)+min;
	}

}
