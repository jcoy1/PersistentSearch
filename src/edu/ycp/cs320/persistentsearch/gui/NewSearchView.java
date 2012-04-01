package edu.ycp.cs320.persistentsearch.gui;

import java.util.Observable;
import java.util.Observer;

import edu.ycp.cs320.persistentsearch.model.Bloomberg;
import edu.ycp.cs320.persistentsearch.model.ESPN;
import edu.ycp.cs320.persistentsearch.model.Google;
import edu.ycp.cs320.persistentsearch.model.NewYorkTimes;
import edu.ycp.cs320.persistentsearch.model.Search;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewSearchView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private Search model;
	private JButton saveButton;
	private JButton cancelButton;
	private JCheckBox googleCheckBox;
	private JCheckBox espnCheckBox;
	private JCheckBox newYorkTimesCheckBox;
	private JCheckBox bloombergCheckBox;
	private JTextField termsTextBox;
	
	private Google google;
	private ESPN espn;
	private NewYorkTimes newYorkTimes;
	private Bloomberg bloomberg;

	public NewSearchView() 
	{
		setLayout(null);
		
		JLabel termsLabel = new JLabel("Search Terms:");
		termsLabel.setBounds(72, 32, 72, 14);
		add(termsLabel);
		
		termsTextBox = new JTextField();
		termsTextBox.setBounds(154, 29, 122, 20);
		add(termsTextBox);
		termsTextBox.setColumns(10);
		
		googleCheckBox = new JCheckBox("Google");
		googleCheckBox.setSelected(true);
		googleCheckBox.setBounds(179, 65, 97, 23);
		add(googleCheckBox);
		
		espnCheckBox = new JCheckBox("ESPN");
		espnCheckBox.setBounds(179, 91, 97, 23);
		add(espnCheckBox);
		
		newYorkTimesCheckBox = new JCheckBox("New York Times");
		newYorkTimesCheckBox.setBounds(179, 117, 109, 23);
		add(newYorkTimesCheckBox);
		
		bloombergCheckBox = new JCheckBox("Bloomberg");
		bloombergCheckBox.setBounds(179, 143, 97, 23);
		add(bloombergCheckBox);
		
		JLabel lblWebsitesToSearch = new JLabel("Websites to Search:");
		lblWebsitesToSearch.setBounds(72, 69, 97, 14);
		add(lblWebsitesToSearch);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleCancel();
			}
		});
		cancelButton.setBounds(250, 203, 89, 23);
		add(cancelButton);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				handleSave();
			}
		});
		saveButton.setBounds(151, 203, 89, 23);
		add(saveButton);
	}
	
	protected void handleSave()
	{
		model = new Search(termsTextBox.getSelectedText());
		
		if(googleCheckBox.equals(true))
		{
			model.addWebsite(google);
		}
		if(espnCheckBox.equals(true))
		{
			model.addWebsite(espn);
		}
		if(newYorkTimesCheckBox.equals(true))
		{
			model.addWebsite(newYorkTimes);
		}
		if(bloombergCheckBox.equals(true))
		{
			model.addWebsite(bloomberg);
		}
	}
	
	protected void handleCancel()
	{
		
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
