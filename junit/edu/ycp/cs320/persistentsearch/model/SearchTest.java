package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class SearchTest {
	
	private Search search;
	private Bing bing;
	private ESPN espn;
	
	@Before
	protected void setUp()
	{
		search = new Search("Giants");
		bing = new Bing();
		espn = new ESPN();
	}
	
	@Test
	public void testAddWebsite() throws Exception
	{
		search.addWebsite(bing);
		assertTrue(search.getSites().contains(bing));
	}
	
	@Test
	public void testPerformSearch() throws Exception
	{
		
	}
	
	@Test
	public void testGetCriteria() throws Exception
	{
		search.setCriteria("Mets");
		assertEquals("Mets", search.getCriteria());
	}
	
	@Test
	public void testGetSites() throws Exception
	{
		search.addWebsite(espn);
		assertTrue(search.getSites().contains(bing));
		assertTrue(search.getSites().contains(espn));
	}
	
	@Test
	public void testGetResults() throws Exception
	{
		search.getResults().addNewResult("espn.com");
		assertEquals(1, search.getResults().getResults().size());
		assertTrue(search.getResults().getResults().contains("espn.com"));
	}

}
