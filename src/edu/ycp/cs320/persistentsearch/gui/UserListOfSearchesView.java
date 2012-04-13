package edu.ycp.cs320.persistentsearch.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;

import edu.ycp.cs320.persistentsearch.model.User;

import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserListOfSearchesView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private JList<String> searchesList;
	private JButton viewResultsButton;
	private JButton editSearchButton;
	private JButton defaultViewButton;
	private User model;
	
	public UserListOfSearchesView() {
		setLayout(null);
		
		model = new User();
		
		JLabel lblNewLabel = new JLabel("List of Searches");
		lblNewLabel.setBounds(10, 11, 86, 14);
		add(lblNewLabel);
		
		searchesList = new JList<String>();
		searchesList.setBounds(10, 36, 430, 213);
		add(searchesList);
		
		defaultViewButton = new JButton("Default View");
		defaultViewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleDefaultView();
			}
		});
		
		defaultViewButton.setBounds(338, 266, 102, 23);
		add(defaultViewButton);
		
		editSearchButton = new JButton("Edit Search");
		editSearchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleEditSearch();
			}
		});
		editSearchButton.setBounds(239, 266, 89, 23);
		add(editSearchButton);
		
		viewResultsButton = new JButton("View Results");
		viewResultsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleViewResults();
			}
		});
		viewResultsButton.setBounds(127, 266, 102, 23);
		add(viewResultsButton);
		
		setPreferredSize(new Dimension(451, 300));
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

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
