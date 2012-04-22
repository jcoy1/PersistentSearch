package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BingTest{

	private Bing bing;
	private Bing bing1;
	private ResultCollection results;
	private SearchTerm st;
	
	@Before
	public void setUp()
	{
		bing = new Bing();
		bing1 = new Bing();
		results = new ResultCollection();
		st = new SearchTerm("Giants");
	}
	
	@Test
	public void testPerformSearch() throws Exception
	{
		assertEquals(0, results.getResults().size());
		results = bing.performSearch(st, results);
		assertFalse(results.getResults().size() == 0);
	}
	
	@Test
	public void testEquals() throws Exception
	{
		assertTrue(bing.equals(bing1));
		assertTrue(bing.equals(bing));
	}

}
