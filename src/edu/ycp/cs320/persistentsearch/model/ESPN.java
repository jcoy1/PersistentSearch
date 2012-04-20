package edu.ycp.cs320.persistentsearch.model;

public class ESPN implements Website {

	@Override
	public ResultCollection performSearch(SearchTerm sT, ResultCollection resultsToUpdate) 
	{
		return null;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass()) 
		{
			return false;
		}
		return true;
	}

}
