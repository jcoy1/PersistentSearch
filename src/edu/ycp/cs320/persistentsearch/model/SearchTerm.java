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
	
	public String formatTerms()
	{
		String formatedTerms = terms;
		formatedTerms = terms.replaceAll(" ", "+");
		return formatedTerms;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) 
		{
			return false;
		}
		SearchTerm other = (SearchTerm) obj;
		return this.terms.equals(other.terms);
	}

}
