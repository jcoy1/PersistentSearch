package edu.ycp.cs320.persistentsearch.model;

import java.util.ArrayList;
import java.util.Observable;

public class ResultCollection extends Observable {
	ArrayList<String> results;
	
	public ResultCollection()
	{
		results = new ArrayList<String>();
	}
	
	public ArrayList<String> getResults()
	{
		return results;
	}
	
	public void addNewResult(String s)
	{
		
	}
}
