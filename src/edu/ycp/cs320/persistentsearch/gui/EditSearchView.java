package edu.ycp.cs320.persistentsearch.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import edu.ycp.cs320.persistentsearch.model.Bing;
import edu.ycp.cs320.persistentsearch.model.Bloomberg;
import edu.ycp.cs320.persistentsearch.model.ESPN;
import edu.ycp.cs320.persistentsearch.model.NewYorkTimes;
import edu.ycp.cs320.persistentsearch.model.Search;
import edu.ycp.cs320.persistentsearch.model.SearchException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditSearchView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private JTextField termsTextBox;
	private JCheckBox bingCheckBox;
	private JCheckBox espnCheckBox;
	private JCheckBox newYorkTimesCheckBox;
	private JCheckBox bloombergCheckBox;
	private JButton saveButton;
	private JButton cancelButton;
	private Search model;
	
	private Bing bing;
	private ESPN espn;
	private NewYorkTimes newYorkTimes;
	private Bloomberg bloomberg;
	
	public EditSearchView(Search s) 
	{
		setLayout(null);
		
		model = s;
		
		JLabel termsLabel = new JLabel("Search Terms:");
		termsLabel.setBounds(167, 58, 72, 14);
		add(termsLabel);
		
		termsTextBox = new JTextField();
		termsTextBox.setColumns(10);
		termsTextBox.setBounds(249, 55, 122, 20);
		add(termsTextBox);
		termsTextBox.setText(model.getCriteria().getTerms());
		
		JLabel websitesLabel = new JLabel("Websites to Search:");
		websitesLabel.setBounds(167, 95, 97, 14);
		add(websitesLabel);
		
		bingCheckBox = new JCheckBox("Bing");
		bingCheckBox.setBounds(274, 91, 97, 23);
		add(bingCheckBox);
		if(model.getSites().contains(bing))
		{
			bingCheckBox.setSelected(true);
		}
		
		espnCheckBox = new JCheckBox("ESPN");
		espnCheckBox.setBounds(274, 117, 97, 23);
		add(espnCheckBox);
		if(model.getSites().contains(espn))
		{
			espnCheckBox.setSelected(true);
		}
		
		newYorkTimesCheckBox = new JCheckBox("New York Times");
		newYorkTimesCheckBox.setBounds(274, 143, 109, 23);
		add(newYorkTimesCheckBox);
		if(model.getSites().contains(newYorkTimes))
		{
			newYorkTimesCheckBox.setSelected(true);
		}
		
		bloombergCheckBox = new JCheckBox("Bloomberg");
		bloombergCheckBox.setBounds(274, 169, 97, 23);
		add(bloombergCheckBox);
		if(model.getSites().contains(bloomberg))
		{
			bloombergCheckBox.setSelected(true);
		}
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					handleSave();
				} catch (SearchException e1) {
					e1.printStackTrace();
				}
			}
		});
		saveButton.setBounds(321, 266, 89, 23);
		add(saveButton);
		
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
		
		setPreferredSize(new Dimension(519, 300));
	}

	protected void handleSave() throws SearchException
	{
		model.setCriteria(termsTextBox.getText());
		
		model.getSites().removeAll(model.getSites());
		
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
		
		userApp.getInstance().switchView(userApp.RESULT_COLLECTION_NAME);
	}
	
	protected void handleCancel()
	{
		userApp.getInstance().switchView(userApp.LIST_OF_SEARCHES_NAME);
	}
	
	public void setModel(Search model) 
	{
		this.model = model;
		
		//register as an observer
		model.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
