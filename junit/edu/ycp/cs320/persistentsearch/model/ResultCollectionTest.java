package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResultCollectionTest {
	private ResultCollection rc;
	private String mets;
	private String giants;
	private ResultCollection other;
	
	@Before
	public void setUp()
	{
		rc = new ResultCollection();
		other = new ResultCollection();
		mets = "Mets.com";
		giants = "Giants.com";
	}
	
	@Test
	public void testAddNewResult() throws Exception
	{
		assertEquals(0, rc.getResults().size());
		rc.addNewResult(mets);
		assertEquals(1, rc.getResults().size());
		rc.addNewResult(giants);
		assertEquals(2, rc.getResults().size());
	}
	
	@Test
	public void testGetResult() throws Exception
	{
		assertEquals(0, rc.getResults().size());
		rc.addNewResult(mets);
		assertEquals(1, rc.getResults().size());
		rc.addNewResult(giants);
		assertEquals(2, rc.getResults().size());
		assertTrue(rc.getResults().contains(mets));
		assertTrue(rc.getResults().contains(giants));
	}
	
	@Test
	public void testSetName() throws Exception
	{
		rc.setName("mets");
		assertEquals("mets", rc.getName());
	}
	
	@Test
	public void testGetName() throws Exception
	{
		rc.setName("mets");
		assertEquals("mets", rc.getName());
	}
	
	@Test
	public void testReplaceResult() throws Exception
	{
		rc.addNewResult(mets);
		other.addNewResult(giants);
		rc.replaceResults(other);
		assertEquals("Giants.com", rc.getResults().get(0));
	}
}
