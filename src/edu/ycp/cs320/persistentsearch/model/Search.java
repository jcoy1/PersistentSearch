package edu.ycp.cs320.persistentsearch.model;

import java.util.ArrayList;
import java.util.Observable;

public class Search extends Observable {
	SearchTerm criteria;
	ArrayList<Website> sitesToVisit;
	ResultCollection results;
	
	public Search(String terms)
	{
		criteria = new SearchTerm(terms);
		sitesToVisit = new ArrayList<Website>();
		results = new ResultCollection();
	}
	
	public void addWebsite(Website w)
	{
		sitesToVisit.add(w);
	}
	
	public void performSearch()
	{
		for(int i = 0; i < sitesToVisit.size(); i++)
		{
			try {
				results = sitesToVisit.get(i).performSearch(criteria, results);
			} catch (SearchException e) {
				System.out.print("Exception from searching the website:" + sitesToVisit.get(i));
			}
		}
	}
	
	public void setCriteria(String terms)
	{
		criteria.setTerms(terms);
	}
	
	public SearchTerm getCriteria()
	{
		return criteria;
	}
	
	public ArrayList<Website> getSites()
	{
		return sitesToVisit;
	}
	
	public ResultCollection getResults()
	{
		return results;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) 
		{
			return false;
		}
		Search other = (Search) obj;
		
		return this.criteria.equals(other.criteria)
				&& this.sitesToVisit.equals(other.sitesToVisit);
	}
}
