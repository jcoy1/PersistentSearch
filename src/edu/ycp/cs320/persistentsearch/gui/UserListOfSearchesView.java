package edu.ycp.cs320.persistentsearch.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import edu.ycp.cs320.persistentsearch.model.*;
import edu.ycp.cs320.persistentsearch.server.Server;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class UserListOfSearchesView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	public interface ViewResultsCallback {
		public void onViewResults(Search search);
	}
	
	private DefaultListModel<Search> searchesListModel;
	private JList<Search> searchesList;
	private JButton viewResultsButton;
	private JButton defaultViewButton;
	private User model;
	private JButton btnDeleteSearch;
	
	private ViewResultsCallback viewResultsCallback;
	private JButton btnAddNewSearch;
	
	public UserListOfSearchesView() {
		setLayout(null);
		
		model = new User();
		
		JLabel lblNewLabel = new JLabel("List of Searches");
		lblNewLabel.setBounds(10, 11, 108, 14);
		add(lblNewLabel);
		
		searchesListModel = new DefaultListModel<Search>();
		searchesList = new JList<Search>(searchesListModel);
		searchesList.setBounds(10, 36, 499, 213);
		searchesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(searchesList);
		
		
		defaultViewButton = new JButton("Default View");
		defaultViewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleDefaultView();
			}
		});
		
		defaultViewButton.setBounds(394, 266, 115, 23);
		add(defaultViewButton);
		
		viewResultsButton = new JButton("View Results");
		viewResultsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleViewResults();
			}
		});
		viewResultsButton.setBounds(10, 266, 108, 23);
		add(viewResultsButton);
		
		btnDeleteSearch = new JButton("Delete Search");
		btnDeleteSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				handleDeleteSearch();
			}
		});
		btnDeleteSearch.setBounds(128, 266, 116, 23);
		add(btnDeleteSearch);
		
		setPreferredSize(new Dimension(519, 300));
		
		btnAddNewSearch = new JButton("Add New Search");
		btnAddNewSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleNewSearch();
			}
		});
		btnAddNewSearch.setBounds(254, 266, 130, 23);
		add(btnAddNewSearch);
	}
	
	public void setViewResultsCallback(ViewResultsCallback viewResultsCallback)
	{
		this.viewResultsCallback = viewResultsCallback;
	}
	
	public void setModel(User model) 
	{
		this.model = model;
		
		//register as an observer
		model.addObserver(this);
		
		update(model, null);
	}
	
	public void handleNewSearch()
	{
		userApp.getInstance().switchView(userApp.NEW_SEARCH_VIEW_NAME);
	}

	public void handleDefaultView()
	{
		userApp.getInstance().switchView(userApp.DEFAULT_VIEW_NAME);
	}
	
	public void handleViewResults()
	{
		if (viewResultsCallback != null)
		{
			Search selected = searchesList.getSelectedValue();
			if (selected != null)
			{
				viewResultsCallback.onViewResults(selected);
			}				
		}
	}
	
	public void handleDeleteSearch()
	{
		//delete the file with the search there
		String selectedHash = searchesList.getSelectedValue().getContentHash();
		
		String nameOfSearchFileToDelete = Server.SEARCH_DIR + "/" + selectedHash + ".search";
		File searchFileToDelete = new File(nameOfSearchFileToDelete);
		
		String nameOfResultsFileToDelete = Server.SEARCH_DIR + "/" + selectedHash + ".results";
		File resultFileToDelete = new File(nameOfResultsFileToDelete);
		
		searchFileToDelete.delete();
		resultFileToDelete.delete();
		
		//delete it from the list and the user profile
		model.deleteSearch(searchesList.getSelectedValue());
		searchesListModel.remove(searchesList.getSelectedIndex());
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		searchesListModel.clear();
		
		//add all of the searches in the user
		for(Search s : model.getProfile())
		{
			searchesListModel.addElement(s);
		}
	}
}
