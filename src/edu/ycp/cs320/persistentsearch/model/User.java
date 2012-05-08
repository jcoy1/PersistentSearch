package edu.ycp.cs320.persistentsearch.model;

import java.util.ArrayList;
import java.util.Observable;

public class User extends Observable {
	private ArrayList<Search> profile;
	
	public User()
	{
		profile = new ArrayList<Search>();
	}
	
	public ArrayList<Search> getProfile()
	{
		return profile;
	}
	
	public void addNewSearch(Search search)
	{
		profile.add(search);
		setChanged();
		notifyObservers();
	}
	
	public void deleteSearch(Search s)
	{
		if(profile.contains(s))
		{
			profile.remove(s);
		}
	}
}
