package edu.ycp.cs320.persistentsearch.model;

import java.util.ArrayList;

public class ResultCollection {
	ArrayList<URL> storedResults = new ArrayList<URL>();
	ArrayList<URL> newResults = new ArrayList<URL>();
	
	public ResultCollection()
	{
		
	}
	
	public ArrayList<URL> getNewResults()
	{
		return newResults;
	}
	
	public ArrayList<URL> getStoredResults()
	{
		return storedResults;
	}
	
	public void addNewResult(URL url)
	{
		
	}
	
	public void changeAllNewResultsToStored()
	{
		
	}
	
	public void changeStoredResultsToNew()
	{
		
	}
}
