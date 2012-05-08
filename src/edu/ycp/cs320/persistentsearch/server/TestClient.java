package edu.ycp.cs320.persistentsearch.server;

import edu.ycp.cs320.persistentsearch.model.Bing;
import edu.ycp.cs320.persistentsearch.model.Search;

public class TestClient {
	//test to see what the file name should be for a search with the terms "giants" and the website of Bing
	public static void main(String[] args) {
		Search search = new Search("giants");
		search.addWebsite(new Bing());
		
		String hash = search.getContentHash();
		
		String resultsFileName = Server.SEARCH_DIR + "/" + hash + ".results";
		
		System.out.println("Results filename should be " + resultsFileName);
	}
}
