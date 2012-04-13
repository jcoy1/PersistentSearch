package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class SearchTest {
	
	private Search search;
	private Google google;
	private ESPN espn;
	
	@Before
	protected void setUp()
	{
		search = new Search("Giants");
		google = new Google();
		espn = new ESPN();
	}
	
	@Test
	public void testAddWebsite() throws Exception
	{
		search.addWebsite(google);
		assertTrue(search.getSites().contains(google));
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
		assertTrue(search.getSites().contains(google));
		assertTrue(search.getSites().contains(espn));
	}
	
	@Test
	public void testGetResults() throws Exception
	{
		
	}

}
