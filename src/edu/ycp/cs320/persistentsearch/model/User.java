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
	
	public void addNewSearch(SearchTerm term)
	{
		profile.add(new Search(term.getTerms()));
	}
	
	public void editSearch(Search s, SearchTerm st)
	{
		s.setCriteria(st.getTerms());
	}
	
	public void deleteSearch(Search s)
	{
		profile.remove(s);
	}
}
