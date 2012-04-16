package edu.ycp.cs320.persistentsearch.model;

public interface Website {
	
	public ResultCollection performSearch(SearchTerm sT, ResultCollection resultsToUpdate) throws SearchException;
}
