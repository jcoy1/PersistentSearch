package edu.ycp.cs320.persistentsearch.server;

import java.io.File;

public class Server {
	
	public static final String SEARCH_DIR = System.getProperty("user.home") + File.separator + "search";
	
	private static final int INTERVAL_MS = 60*5*1000; // wake up every 5 minutes
	
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			performSearches();
			Thread.sleep(INTERVAL_MS);
		}
	}

	private static void performSearches() {
		File searchDir = new File(SEARCH_DIR);
		
		if (!searchDir.exists() || !searchDir.isDirectory()) {
			throw new IllegalStateException("Search directory doesn't exist");
		}
		
		File[] contents = searchDir.listFiles();
		for (File f : contents) {
			if (!f.isDirectory() && f.getName().endsWith(".search")) {
				// This is a search: load it and perform the search, saving the results in a .results file
			}
		}
	}
}
