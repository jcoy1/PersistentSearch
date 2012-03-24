package edu.ycp.cs320.persistentsearch.model;

import java.util.ArrayList;

public class Search {
	SearchTerm criteria = new SearchTerm();
	ArrayList<Website> sitesToVisit = new ArrayList<Website>();
	ResultCollection results = new ResultCollection();
	
	public Search()
	{
		
	}
}
