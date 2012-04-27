package edu.ycp.cs320.persistentsearch.model;

import java.util.ArrayList;
import java.util.Observable;

public class ResultCollection extends Observable {
	ArrayList<String> results;
	String name;
	
	public ResultCollection()
	{
		results = new ArrayList<String>();
		name = "";
	}
	
	public ArrayList<String> getResults()
	{
		return results;
	}
	
	public void setName(String s)
	{
		name = s;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addNewResult(String s)
	{
		results.add(s);
		setChanged();
		notifyObservers();
	}
	
	public void replaceResults(ResultCollection other)
	{
		results.clear();
		results.addAll(other.results);
		setChanged();
		notifyObservers();
	}
}
