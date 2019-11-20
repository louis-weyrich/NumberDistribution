package com.xpo.numberdistribution;

/**
 * This is used to determine the distribution of numbers.
 * 
 * @author lweyri
 *
 */
public class DistributionCount
{
	// The expected distribution count
	private int expected;
	
	// The actual distribution count
	private int actual;

	/**
	 * This constructor initializes the expected distribution count.
	 * @param expected
	 */
	public DistributionCount(int expected)
	{
		this.expected = expected;
	}
	
	public int getExpected()
	{
		return this.expected;
	}

	public int getActual()
	{
		return actual;
	}

	public void incrementActual()
	{
		++this.actual;
	}
	
	public boolean hasReachedLimit()
	{
		return actual > (expected - 1);
	}
	
	
}
