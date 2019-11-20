package com.xpo.numberdistribution;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.Test;


public class NumberDistributionTest
{
	
	/**
	 * Test to see if the distribution numbers match expected values.
	 */
	@Test
	public void testNuberDistribution()
	{
		NumberDistributionApp numberDistributionApp;
		try
		{
			numberDistributionApp = new NumberDistributionApp("./config/number_distribution.properties");
			Map <Integer, DistributionCount> distMap = numberDistributionApp.start();
			
			assertNotNull(distMap);
			
			Set <Integer> keySet = distMap.keySet();
			
			// check to see if all distributes match expected.
			for(Integer key : keySet)
			{
    			assertTrue(distMap.containsKey(key));
    			DistributionCount distCount = distMap.get(key);
    			assertTrue(key+" expected="+distCount.getExpected()+
    				" actual="+distCount.getActual(), 
    				distCount.getExpected() == distCount.getActual());
			}
		} 
		catch (Exception e)
		{
			
			e.printStackTrace();
		}
		
	}
}
