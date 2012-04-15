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
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;


public class defaultUserView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private User model;
	private JButton addNewSearchButton;
	private JButton listOfSearchButton;
	
	public defaultUserView() {
		setBackground(Color.BLUE);
		setForeground(Color.BLACK);
		setLayout(null);
		
		addNewSearchButton = new JButton("Add New Search");
		addNewSearchButton.setForeground(Color.BLUE);
		addNewSearchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleNewSearch();
			}
		});
		addNewSearchButton.setBounds(224, 266, 134, 23);
		addNewSearchButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		add(addNewSearchButton);
		
		listOfSearchButton = new JButton("List of Searches");
		listOfSearchButton.setForeground(Color.BLUE);
		listOfSearchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleListOfSearches();
			}
		});
		listOfSearchButton.setBounds(368, 266, 141, 23);
		add(listOfSearchButton);
		
		setPreferredSize(new Dimension(519, 300));
		
		JLabel lblPersistentSearch = new JLabel("PERSISTENT SEARCH");
		lblPersistentSearch.setForeground(Color.RED);
		lblPersistentSearch.setFont(new Font("Impact", Font.ITALIC, 60));
		lblPersistentSearch.setBounds(10, 11, 499, 108);
		add(lblPersistentSearch);
		
		JLabel lblPresentedBy = new JLabel("Presented By:");
		lblPresentedBy.setForeground(Color.WHITE);
		lblPresentedBy.setFont(new Font("Impact", Font.PLAIN, 24));
		lblPresentedBy.setBounds(185, 130, 150, 34);
		add(lblPresentedBy);
		
		JLabel lblCoyKendall = new JLabel("Kendall & Coy");
		lblCoyKendall.setForeground(Color.RED);
		lblCoyKendall.setBackground(Color.BLUE);
		lblCoyKendall.setFont(new Font("Impact", Font.ITALIC, 60));
		lblCoyKendall.setBounds(87, 171, 352, 84);
		add(lblCoyKendall);
	}
	
	public void setModel(User model) 
	{
		this.model = model;
		
		//register as an observer
		model.addObserver(this);
	}
	
	protected void handleNewSearch()
	{
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
