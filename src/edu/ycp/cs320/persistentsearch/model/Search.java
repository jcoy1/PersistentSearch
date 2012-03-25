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
}
