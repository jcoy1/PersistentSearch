package edu.ycp.cs320.persistentsearch.gui;

import java.util.Observable;
import java.util.Observer;

import edu.ycp.cs320.persistentsearch.model.Bing;
import edu.ycp.cs320.persistentsearch.model.Bloomberg;
import edu.ycp.cs320.persistentsearch.model.ESPN;
import edu.ycp.cs320.persistentsearch.model.NewYorkTimes;
import edu.ycp.cs320.persistentsearch.model.Search;
import edu.ycp.cs320.persistentsearch.model.SearchException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewSearchView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private Search model;
	private JButton saveButton;
	private JButton cancelButton;
	private JCheckBox bingCheckBox;
	private JCheckBox espnCheckBox;
	private JCheckBox newYorkTimesCheckBox;
	private JCheckBox bloombergCheckBox;
	private JTextField termsTextBox;
	
	private Bing bing;
	private ESPN espn;
	private NewYorkTimes newYorkTimes;
	private Bloomberg bloomberg;

	public NewSearchView() 
	{
		bing = new Bing();
		espn = new ESPN();
		newYorkTimes = new NewYorkTimes();
		bloomberg = new Bloomberg();
		
		setLayout(null);
		
		JLabel termsLabel = new JLabel("Search Terms:");
		termsLabel.setBounds(152, 58, 72, 14);
		add(termsLabel);
		
		termsTextBox = new JTextField();
		termsTextBox.setBounds(234, 55, 122, 20);
		add(termsTextBox);
		termsTextBox.setColumns(10);
		
		bingCheckBox = new JCheckBox("Bing");
		bingCheckBox.setSelected(true);
		bingCheckBox.setBounds(259, 91, 97, 23);
		add(bingCheckBox);
		
		espnCheckBox = new JCheckBox("ESPN");
		espnCheckBox.setBounds(259, 117, 97, 23);
		add(espnCheckBox);
		
		newYorkTimesCheckBox = new JCheckBox("New York Times");
		newYorkTimesCheckBox.setBounds(259, 143, 109, 23);
		add(newYorkTimesCheckBox);
		
		bloombergCheckBox = new JCheckBox("Bloomberg");
		bloombergCheckBox.setBounds(259, 169, 97, 23);
		add(bloombergCheckBox);
		
		JLabel lblWebsitesToSearch = new JLabel("Websites to Search:");
		lblWebsitesToSearch.setBounds(152, 95, 97, 14);
		add(lblWebsitesToSearch);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleCancel();
			}
		});
		cancelButton.setBounds(420, 266, 89, 23);
		add(cancelButton);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					handleSave();
				} catch (SearchException e) {
					e.printStackTrace();
				}
			}
		});
		saveButton.setBounds(321, 266, 89, 23);
		add(saveButton);
		
		setPreferredSize(new Dimension(519, 300));
	}
	
	protected void handleSave() throws SearchException
	{
		model = new Search(termsTextBox.getText());
		
		if(bingCheckBox.isEnabled())
		{
			model.addWebsite(bing);
		}
		if(espnCheckBox.isEnabled())
		{
			model.addWebsite(espn);
		}
		if(newYorkTimesCheckBox.isEnabled())
		{
			model.addWebsite(newYorkTimes);
		}
		if(bloombergCheckBox.isEnabled())
		{
			model.addWebsite(bloomberg);
		}
		
		if(model.getSites().size() > 0)
			model.getSites().get(0).performSearch(model.getCriteria(), model.getResults());
		
		for(int i = 0; i < model.getResults().getResults().size(); i++)
			System.out.println(model.getResults().getResults().get(i));
		
		userApp.getInstance().switchView(userApp.RESULT_COLLECTION_NAME);
	}
	
	protected void handleCancel()
	{
		userApp.getInstance().switchView(userApp.DEFAULT_VIEW_NAME);
	}
	
	public void setModel(Search model) 
	{
		this.model = model;
		
		//register as an observer
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
		
	}
}
