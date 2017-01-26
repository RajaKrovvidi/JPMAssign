package com.gbc.core.unit_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gbc.core.Trade;
import com.gbc.core.*;

public class TradeClassTests 
{
	Trade b1, b2, s1, s2, b3, s3;
	
	@Before
	public void setUp() throws Exception 
	{
		b1 = Trade.TradeBuilder.createTrade(100, true, 100);
		s1 = Trade.TradeBuilder.createTrade(100, false, 100);
		b2 = Trade.TradeBuilder.createTrade(100, true, 100);
		s2 = Trade.TradeBuilder.createTrade(100, false, 100);
		b3 = Trade.TradeBuilder.createTrade(101, true, 100);
		s3 = Trade.TradeBuilder.createTrade(101, false, 100);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEquals()
	{
		//System.out.println("Equals b1 and b2");
		assertFalse(b1.equals(b2));
		//System.out.println("Equals s1 and s2");
		assertFalse(s1.equals(s2));
		assertFalse(b1.equals(s1));
		assertFalse(b1.equals(b3));
		assertFalse(s1.equals(s3));
	}
	
	@Test
	public void testHasCode() 
	{
		//System.out.println("Hashcode b1 and b2");
		assertFalse(b1.hashCode() == b2.hashCode());
		//System.out.println("Hashcode s1 and s2");
		assertFalse(s1.hashCode() == s2.hashCode());
		assertFalse(b1.hashCode() == s1.hashCode());
		assertFalse(b1.hashCode() == b3.hashCode());
		assertFalse(s1.hashCode() == s3.hashCode());
	}

	@Test
	public void testCompareTo()
	{
		//System.out.println("Compare b1 and b2");
		assertTrue(b1.compare(b1, b2) < 1);
		//System.out.println("Compare b2 and b1");
		assertTrue(b1.compare(b2, b1) > -1);
		assertTrue(b1.compare(b1, s1) < 1);
		assertTrue(b1.compare(s1, b1) > -1);
	}
}