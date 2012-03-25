package edu.ycp.cs320.persistentsearch.model;

import java.util.ArrayList;
import java.util.Observable;

public class ResultCollection extends Observable {
	ArrayList<URL> storedResults;
	ArrayList<URL> newResults;
	
	public ResultCollection()
	{
		storedResults = new ArrayList<URL>();
		newResults = new ArrayList<URL>();
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
