package edu.ycp.cs320.persistentsearch.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchContentHashTest {
	private Search giants;
	private Search mets;
	
	@Before
	public void setUp() {
		giants = new Search("giants");
		giants.addWebsite(new Bing());
		
		mets = new Search("mets");
		mets.addWebsite(new Bing());
	}
	
	@Test
	public void testContentHashesAreDifferent() throws Exception {
		String h1 = mets.getContentHash();
		String h2 = giants.getContentHash();
		
		assertFalse(h1.equals(h2));
	}
}
