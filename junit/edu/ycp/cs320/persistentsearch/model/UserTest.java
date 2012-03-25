package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class UserTest{
	private User user;
	
	@Before
	protected void setUp()
	{
		user = new User();
	}
	
	@Test
	public void testAddNewSearch() throws Exception
	{
		assertEquals(0, user.getProfile().size());
		user.addNewSearch();
		assertEquals(1, user.getProfile().size());
	}
	
	@Test
	public void testEditSearch() throws Exception
	{
		
	}
	
	@Test
	public void testDeleteSearch() throws Exception
	{
		assertEquals(1, user.getProfile().size());
	}
}
