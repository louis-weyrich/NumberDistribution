package com.xpo.numberdistribution;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class NumberDistributionApp 
{
	private int min;
	private int max;
	private int count;
	private FileOutputStream fos;
	
	/**
	 * This constructor starts with the default properties file.
	 * 
	 * @throws Exception
	 */
	public NumberDistributionApp()throws Exception
	{
		this("./config/number_distribution.properties");
	}
	/**
	 * The constructor loads a user specific properties file.
	 * 
	 * <b>Properties</b><br>
	 * ------------<br>
	 * <code>
	 * min (The starting number of the distribution)
	 * max (The largest number in the distribution)
	 * count (how many time the distribution numbers are iterated)
	 * outputfile (the name of the file to out put result to)
	 * </code>
	 * 
	 * @param propertiesFileString - the location and name of the properties file
	 * @throws Exception is thrown if the properties file can not be found or
	 * 		the outputfile can not be created or deleted.
	 */
	public NumberDistributionApp(String propertiesFileString)
	throws Exception
	{	
		Properties properties = new Properties();
		File propertiesFile = new File(propertiesFileString);
		
		if(propertiesFile.exists())
		{
			properties.load(new FileInputStream(propertiesFile));
			
			if(properties.containsKey("min"))
			{
				this.min = Integer.valueOf(properties.getProperty("min"));
			}
			else
			{
				throw new IllegalArgumentException("[min] property was not found");
			}
			
			if(properties.containsKey("max"))
			{
				this.max = Integer.valueOf(properties.getProperty("max"));
			}
			else
			{
				throw new IllegalArgumentException("[max] property was not found");
			}
			
			if(properties.containsKey("count"))
			{
				this.count = Integer.valueOf(properties.getProperty("count"));
			}
			else
			{
				throw new IllegalArgumentException("[count] property was not found");
			}
			
			if(properties.containsKey("outputfile"))
			{
				File outputFile = new File(properties.getProperty("outputfile"));
				
				if(outputFile.exists())
				{
					if(!outputFile.delete())
					{
						throw new IOException("cound not delete existing outputfile");
					}
				}
				
				fos = new FileOutputStream(outputFile);
			}
			else
			{
				throw new IllegalArgumentException("[outputfile] property was not found");
			}
		}	
		else
		{
			throw new IOException("Properties file not found. ["+
					propertiesFileString+"]");
		}
	}
	
	/**
	 * The start method begins the creation of the number distribution.
	 * 
	 * @return the statistics of the outcome.
	 * @throws IOException is throw if 
	 */
	public Map <Integer, DistributionCount> start()throws IOException
	{
		Map <Integer, DistributionCount> distMap = initializeMap();
		RandomRange rand = new RandomRange(min, max);
		int number = 0;
		
		for(int index = 0; index < count; index++)
		{
			number = getNextRandomNumber(distMap, rand, number);
			
			if(number < 1)
			{
				break;
			}
			
			if(number == max)
			{
				System.out.println(number);
			}
			
			fos.write(new String(number+"\n").getBytes());
		}
		
		fos.flush();
		
		System.out.println("Finished!");
		
		fos.close();
		
		return distMap;
	}
	
	/**
	 * 
	 * @param distMap - A map containing the distribution results
	 * @param rand - is used to create a range of random numbers.
	 * @param previousNumber - the previous number used in the distribution
	 * @return the map of distributions
	 */
	protected int getNextRandomNumber(
	Map <Integer, DistributionCount> distMap, RandomRange rand, int previousNumber)
	{
		int number = rand.getNextRandomInt();
		if(number == previousNumber)
		{
			number = rand.getNextRandomInt();
		}

		if(!distMap.get(number).hasReachedLimit())
		{
			distMap.get(number).incrementActual();
			return number;
		}
		else
		{
			return getNextRandomNumber(distMap, rand, previousNumber);
		}
	}
	
	/**
	 * this method initializes the distribution map
	 * 
	 * @return an initialized distribution map
	 */
	protected Map <Integer, DistributionCount> initializeMap()
	{
		Map <Integer, DistributionCount> distMap = 
				new HashMap <Integer, DistributionCount> ();
		
		distMap.put(new Integer(1), new DistributionCount(83000));
		distMap.put(new Integer(2), new DistributionCount(83000));
		distMap.put(new Integer(3), new DistributionCount(83000));
		distMap.put(new Integer(4), new DistributionCount(83000));
		distMap.put(new Integer(5), new DistributionCount(83000));
		distMap.put(new Integer(6), new DistributionCount(83000));
		distMap.put(new Integer(7), new DistributionCount(83000));
		distMap.put(new Integer(8), new DistributionCount(83000));
		distMap.put(new Integer(9), new DistributionCount(83000));
		distMap.put(new Integer(10), new DistributionCount(83000));
		distMap.put(new Integer(11), new DistributionCount(83000));
		distMap.put(new Integer(12), new DistributionCount(83000));
		distMap.put(new Integer(13), new DistributionCount(1000));
		distMap.put(new Integer(14), new DistributionCount(500));
		distMap.put(new Integer(15), new DistributionCount(250));
		distMap.put(new Integer(16), new DistributionCount(100));
		distMap.put(new Integer(17), new DistributionCount(50));
		distMap.put(new Integer(18), new DistributionCount(25));
		distMap.put(new Integer(19), new DistributionCount(10));
		distMap.put(new Integer(20), new DistributionCount(5));
		
		return distMap;
	}
	
    public static void main( String[] args )
    {
    	String propertiesFileString;
    	
    	if(args.length == 1)
    	{
    		propertiesFileString = args[0];
    	}
    	else
    	{
    		propertiesFileString = "./config/number_distribution.properties";
    	}
    	
    	try
		{
    		new NumberDistributionApp(propertiesFileString).start();
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
		}
    	
    }
}
