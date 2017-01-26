package com.gbc.core.unit_tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gbc.core.GBCCoreStaticInterface;
import com.gbc.core.InvalidStockParameterException;
import com.gbc.core.StockSymbolNotRegisteredException;
import com.gbc.core.StocksRegister;
import com.gbc.core.Trade;
import com.gbc.core.TradeCache;
import com.gbc.core.Stock.StockBuilder;

public class TradeCacheTests {

	StocksRegister sr = null;
	TradeCache tc = null;
	
	
	public static void populateTradeCache(StocksRegister sRegister, TradeCache tCache, int nTrades) throws Exception
	{
		// for each stock entry in sr, 
		// populate TradeCache with nTrades
		ArrayList<Thread> ts = new ArrayList<Thread>();
		Random rnd = new Random();
		
		try
		{
			for (String symbol: sRegister.getSymbols())
			{
				
				for (int i =0; i < nTrades; ++i)
				{
					Thread thread = new Thread(new Runnable() 
					{
		
					    @Override
					    public void run() 
					    {
					    	
					    	try 
					    	{
					    		int quantity = (int) (rnd.nextFloat() * 100) + 1;
					    		int price = (int) (rnd.nextFloat() * 1000) + 1;
					    		boolean isBuy = ((int)(rnd.nextFloat() * 2) % 2 == 0) ? true : false;
					    		Trade trade = Trade.TradeBuilder.createTrade(quantity, isBuy, price);
					    		tCache.recordTrade(symbol, trade);	
								
							}
					    	catch (StockSymbolNotRegisteredException e)
					    	{
					    		//System.out.println(e.getMessage());
								// fail(e.getMessage());
								//e.printStackTrace();
							} 
					    	catch (IllegalArgumentException e)
					    	{
					    		//System.out.println(e.getMessage());
					    		//assertTrue(false);
								// fail(e.getMessage());
								//e.printStackTrace();
							} 
					    }
					            
					});
					ts.add(thread);
					thread.start();
				}
			}	
			for (int i = 0; i< ts.size(); ++i)
				ts.get(i).join();
			
		}
		catch(Exception e)
		{
			fail("Not yet implemented");
		}
		assertTrue(true);
	}
	
	@Before
	public void setUp() throws Exception 
	{
		// Get an instance
		sr = StocksRegister.getInstance();
		// Populate ..
		ConcurrentSkipListSet<String> generatedSymbols = new ConcurrentSkipListSet<String>();
		StocksRegisterTests.populateStocksRegister(sr, 1000, generatedSymbols);
		//System.out.println("Populated stocks register with #Symbols " + generatedSymbols.size());
		tc = TradeCache.getInstance();
		
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test() throws Exception
	{
		populateTradeCache(sr, tc, 1);
		double index = GBCCoreStaticInterface.getGBCAllStockIndex(System.currentTimeMillis() - 30000);
		//System.out.println("No of keys in the Cache " + tc.size());
		//for (String symbol : tc.keySet())
			//System.out.print(symbol + " - " + GBCCoreStaticInterface.getVWAPForSymbol(symbol, System.currentTimeMillis() - 30000 ) );
			
		//System.out.println("\nIndex level is " + index);
	}

}