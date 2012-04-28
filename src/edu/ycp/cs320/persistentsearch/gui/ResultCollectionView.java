package edu.ycp.cs320.persistentsearch.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;

import edu.ycp.cs320.persistentsearch.model.ResultCollection;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultCollectionView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<String> resultListModel;
	private JButton defaultButton;
	private JList<String> resultList;
	private JTextField nameTextBox;
	private ResultCollection model;
	private JButton listOfSearchesButton;

	public ResultCollectionView() {
		setLayout(null);
		
		model = new ResultCollection();
		
		resultListModel = new DefaultListModel<String>();
		resultList = new JList<String>(resultListModel);
		resultList.setBounds(10, 42, 499, 213);
		add(resultList);
		
		nameTextBox = new JTextField();
		nameTextBox.setBounds(10, 11, 128, 20);
		add(nameTextBox);
		nameTextBox.setColumns(10);
		nameTextBox.setText(model.getName());
		
		listOfSearchesButton = new JButton("List of Searches");
		listOfSearchesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleListOfSearches();
			}
		});
		listOfSearchesButton.setBounds(230, 266, 128, 23);
		add(listOfSearchesButton);
		
		defaultButton = new JButton("Default View");
		defaultButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				handleDefaultView();
			}
		});
		
		defaultButton.setBounds(368, 266, 141, 23);
		add(defaultButton);
		
		setPreferredSize(new Dimension(519, 300));
	}
	
	public void setModel(ResultCollection model) 
	{
		this.model = model;
		
		//register as an observer
		model.addObserver(this);
	}
	
	public void handleDefaultView()
	{
		userApp.getInstance().switchView(userApp.DEFAULT_VIEW_NAME);
	}
	
	public void handleListOfSearches()
	{
		userApp.getInstance().switchView(userApp.LIST_OF_SEARCHES_NAME);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		nameTextBox.setText(model.getName());
		
		//add each element to the list for the gui
		resultListModel.clear();
		for(String s : model.getResults())
		{
			resultListModel.addElement(s);
		}
	}
}
