package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchTermTest {
	SearchTerm term;
	SearchTerm term2;
	
	@Before
	protected void setUp()
	{
		term = new SearchTerm("Nationals");
		term2 = new SearchTerm("Giants");
	}
	
	@Test
	public void testGetTerms() throws Exception
	{
		assertEquals("Nationals", term.getTerms());
		assertEquals("Giants", term2.getTerms());
	}
	
	@Test
	public void testSetTerms() throws Exception
	{
		term.setTerms("Mets");
		term2.setTerms("Ravens");
		assertEquals("Mets", term.getTerms());
		assertEquals("Ravens", term2.getTerms());
	}
}
