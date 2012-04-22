package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchTermTest {
	SearchTerm term;
	SearchTerm term2;
	SearchTerm term3;
	
	@Before
	public void setUp()
	{
		term = new SearchTerm("Nationals");
		term2 = new SearchTerm("Giants");
		term3 = new SearchTerm("New York Giants");
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
	
	@Test
	public void testFormatTerms() throws Exception
	{
		assertEquals("New York Giants", term3.getTerms());
		assertEquals("New+York+Giants", term3.formatTerms());
	}
	
	@Test
	public void testEquals() throws Exception
	{
		assertTrue(term.equals(term));
		term2.setTerms("Nationals");
		assertTrue(term.equals(term2));
	}
}
