package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class UserTest{
	private User user;
	private Search search;
	private SearchTerm st;
	
	@Before
	protected void setUp()
	{
		user = new User();
		search = new Search("Giants");
		st = new SearchTerm("Mets");
	}
	
	@Test
	public void testAddNewSearch() throws Exception
	{
		assertEquals(0, user.getProfile().size());
		user.addNewSearch(st);
		assertEquals(1, user.getProfile().size());
	}
	
	@Test
	public void testEditSearch() throws Exception
	{
		user.editSearch(search, st);
		
		assertEquals("Mets", search.getCriteria());
	}
	
	@Test
	public void testDeleteSearch() throws Exception
	{
		assertEquals(1, user.getProfile().size());
	}
}
