package edu.ycp.cs320.persistentsearch.model;

public interface Website {
	
	public ResultCollection performSearch(Search s, ResultCollection resultsToUpdate);
}
