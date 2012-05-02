package edu.ycp.cs320.persistentsearch.server;

import java.io.File;

import edu.ycp.cs320.persistentsearch.model.Bing;
import edu.ycp.cs320.persistentsearch.model.Search;

public class TestClient {
	public static void main(String[] args) {
		Search search = new Search("giants");
		search.addWebsite(new Bing());
		
		String hash = search.getContentHash();
		
		String resultsFileName = Server.SEARCH_DIR + "/" + hash + ".results";
		
		System.out.println("Results filename should be " + resultsFileName);
		
		File resultsFile = new File(resultsFileName);
		if (resultsFile.exists()) {
			// should now be able to load saved search results from the file
		}
	}
}
