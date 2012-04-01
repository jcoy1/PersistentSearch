package edu.ycp.cs320.persistentsearch.model;

public class SearchTerm {
	String terms;
	
	public SearchTerm(String s)
	{
		terms = s;
	}
	
	public void setTerms(String s)
	{
		terms = s;
	}
	
	public String getTerms()
	{
		return terms;
	}

}
