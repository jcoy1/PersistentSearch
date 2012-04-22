package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class SearchTest {
	
	private Search search;
	private SearchTerm st;
	private Bing bing;
	private ESPN espn;
	private ResultCollection rc;
	private Search other;
	
	@Before
	public void setUp()
	{
		search = new Search("Giants");
		st = new SearchTerm("Mets");
		bing = new Bing();
		espn = new ESPN();
		rc = new ResultCollection();
		other = new Search("Giants");
		
	}
	
	@Test
	public void testAddWebsite() throws Exception
	{
		search.addWebsite(bing);
		assertTrue(search.getSites().contains(bing));
		assertEquals(1, search.getSites().size());
	}
	
	@Test
	public void testPerformSearch() throws Exception
	{
		assertEquals(0, rc.getResults().size());
		search.addWebsite(bing);
		rc = search.getSites().get(0).performSearch(new SearchTerm("giants"), rc);
		assertFalse(rc.getResults().size() == 0);
	}
	
	@Test
	public void testGetCriteria() throws Exception
	{
		search.setCriteria(st.getTerms());
		assertEquals(st.getTerms(), search.getCriteria().getTerms());
	}
	
	@Test
	public void testGetSites() throws Exception
	{
		search.addWebsite(espn);
		search.addWebsite(bing);
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
	
	@Test
	public void testEquals() throws Exception
	{
		assertTrue(search.equals(search));
		search.addWebsite(bing);
		other.addWebsite(bing);
		assertTrue(search.equals(other));
	}

}
