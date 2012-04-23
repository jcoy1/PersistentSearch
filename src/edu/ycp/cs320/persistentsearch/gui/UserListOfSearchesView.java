package edu.ycp.cs320.persistentsearch.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListModel;

import edu.ycp.cs320.persistentsearch.model.*;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserListOfSearchesView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<Search> searchesListModel;
	private JList<Search> searchesList;
	private JButton viewResultsButton;
	private JButton editSearchButton;
	private JButton defaultViewButton;
	private User model;
	private JButton btnDeleteSearch;
	
	public UserListOfSearchesView() {
		setLayout(null);
		
		model = userApp.userModel;
		
		JLabel lblNewLabel = new JLabel("List of Searches");
		lblNewLabel.setBounds(10, 11, 108, 14);
		add(lblNewLabel);
		
		searchesListModel = new DefaultListModel<Search>();
		searchesList = new JList<Search>(searchesListModel);
		searchesList.setBounds(10, 36, 499, 213);
		add(searchesList);
		
		
		defaultViewButton = new JButton("Default View");
		defaultViewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleDefaultView();
			}
		});
		
		defaultViewButton.setBounds(379, 266, 130, 23);
		add(defaultViewButton);
		
		editSearchButton = new JButton("Edit Search");
		editSearchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleEditSearch();
			}
		});
		editSearchButton.setBounds(128, 266, 108, 23);
		add(editSearchButton);
		
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
		btnDeleteSearch.setBounds(246, 266, 123, 23);
		add(btnDeleteSearch);
		
		setPreferredSize(new Dimension(519, 300));
	}
	
	public void setModel(User model) 
	{
		this.model = model;
		
		//register as an observer
		model.addObserver(this);
	}

	public void handleDefaultView()
	{
		userApp.getInstance().switchView(userApp.DEFAULT_VIEW_NAME);
	}
	
	public void handleEditSearch()
	{
		//searchesList.getSelectedValuesList();
		userApp.getInstance().switchView(userApp.EDIT_SEARCH_VIEW_NAME);
	}
	
	public void handleViewResults()
	{
		userApp.getInstance().switchView(userApp.RESULT_COLLECTION_NAME);
	}
	
	public void handleDeleteSearch()
	{
		userApp.userModel.deleteSearch(searchesList.getSelectedValue());
		searchesList.remove(searchesList.getSelectedIndex());
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		for(Search s : userApp.userModel.getProfile())
		{
			searchesListModel.addElement(s);
		}
	}
}
