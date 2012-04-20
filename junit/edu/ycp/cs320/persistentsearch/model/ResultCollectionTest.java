package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResultCollectionTest {
	private ResultCollection rc;
	private String mets;
	private String giants;
	
	@Before
	public void setUp()
	{
		rc = new ResultCollection();
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
}
