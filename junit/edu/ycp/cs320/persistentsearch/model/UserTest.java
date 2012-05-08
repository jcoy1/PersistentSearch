package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class UserTest{
	private User user;
	private Search search;
	private Search search2;
	
	@Before
	public void setUp()
	{
		user = new User();
		search = new Search("Giants");
		search2 = new Search("Mets");
	}
	
	@Test
	public void testAddNewSearch() throws Exception
	{
		assertEquals(0, user.getProfile().size());
		user.addNewSearch(search);
		assertEquals(1, user.getProfile().size());
		
		assertTrue(user.getProfile().contains(search));
	}
	
	@Test
	public void testDeleteSearch() throws Exception
	{
		user.addNewSearch(search);
		assertEquals(1, user.getProfile().size());
		user.addNewSearch(search2);
		assertEquals(2, user.getProfile().size());
		
		user.deleteSearch(search);
		assertEquals(1, user.getProfile().size());
	}
}
