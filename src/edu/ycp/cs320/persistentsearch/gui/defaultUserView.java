package edu.ycp.cs320.persistentsearch.gui;

import edu.ycp.cs320.persistentsearch.model.User;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class defaultUserView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private User model;
	private JButton addNewSearchButton;
	private JButton listOfSearchButton;
	
	public defaultUserView() {
		setLayout(null);
		
		addNewSearchButton = new JButton("Add New Search");
		addNewSearchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleNewSearch();
			}
		});
		addNewSearchButton.setBounds(32, 131, 134, 23);
		addNewSearchButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		add(addNewSearchButton);
		
		listOfSearchButton = new JButton("List of Searches");
		listOfSearchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleListOfSearches();
			}
		});
		listOfSearchButton.setBounds(230, 131, 141, 23);
		add(listOfSearchButton);
		
		setPreferredSize(new Dimension(425, 293));
	}
	
	public void setModel(User model) 
	{
		this.model = model;
		
		//register as an observer
		model.addObserver(this);
	}
	
	protected void handleNewSearch()
	{
		model.addNewSearch();
		userApp.getInstance().switchView(userApp.NEW_SEARCH_VIEW_NAME);
	}
	
	protected void handleListOfSearches()
	{
		userApp.getInstance().switchView(userApp.LIST_OF_SEARCHES_NAME);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
