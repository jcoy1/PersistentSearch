package edu.ycp.cs320.persistentsearch.gui;

import edu.ycp.cs320.persistentsearch.model.User;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class defaultUserView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private User model;
	private JButton addNewSearchButton;
	private JButton editSearchButton;
	private JButton viewResultsButton;
	
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
		addNewSearchButton.setBounds(43, 114, 111, 23);
		addNewSearchButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		add(addNewSearchButton);
		
		editSearchButton = new JButton("Edit Search");
		editSearchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleEditSearch();
			}
		});
		editSearchButton.setBounds(279, 114, 111, 23);
		add(editSearchButton);
		
		viewResultsButton = new JButton("View Results");
		viewResultsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleViewResults();
			}
		});
		viewResultsButton.setBounds(164, 175, 111, 23);
		add(viewResultsButton);
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
	}
	
	protected void handleEditSearch()
	{
		
	}
	
	protected void handleViewResults()
	{
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
