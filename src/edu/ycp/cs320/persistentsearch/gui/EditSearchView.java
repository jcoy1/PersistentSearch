package edu.ycp.cs320.persistentsearch.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import edu.ycp.cs320.persistentsearch.model.Bloomberg;
import edu.ycp.cs320.persistentsearch.model.ESPN;
import edu.ycp.cs320.persistentsearch.model.Google;
import edu.ycp.cs320.persistentsearch.model.NewYorkTimes;
import edu.ycp.cs320.persistentsearch.model.Search;
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
	private JCheckBox googleCheckBox;
	private JCheckBox espnCheckBox;
	private JCheckBox newYorkTimesCheckBox;
	private JCheckBox bloombergCheckBox;
	private JButton saveButton;
	private JButton cancelButton;
	private Search model;
	
	private Google google;
	private ESPN espn;
	private NewYorkTimes newYorkTimes;
	private Bloomberg bloomberg;
	
	public EditSearchView(Search s) 
	{
		setLayout(null);
		
		model = s;
		
		JLabel termsLabel = new JLabel("Search Terms:");
		termsLabel.setBounds(43, 14, 72, 14);
		add(termsLabel);
		
		termsTextBox = new JTextField();
		termsTextBox.setColumns(10);
		termsTextBox.setBounds(125, 11, 122, 20);
		add(termsTextBox);
		termsTextBox.setText(model.getCriteria());
		
		JLabel websitesLabel = new JLabel("Websites to Search:");
		websitesLabel.setBounds(43, 51, 97, 14);
		add(websitesLabel);
		
		googleCheckBox = new JCheckBox("Google");
		googleCheckBox.setBounds(150, 47, 97, 23);
		add(googleCheckBox);
		if(model.getSites().contains(google))
		{
			googleCheckBox.setSelected(true);
		}
		
		espnCheckBox = new JCheckBox("ESPN");
		espnCheckBox.setBounds(150, 73, 97, 23);
		add(espnCheckBox);
		if(model.getSites().contains(espn))
		{
			espnCheckBox.setSelected(true);
		}
		
		newYorkTimesCheckBox = new JCheckBox("New York Times");
		newYorkTimesCheckBox.setBounds(150, 99, 109, 23);
		add(newYorkTimesCheckBox);
		if(model.getSites().contains(newYorkTimes))
		{
			newYorkTimesCheckBox.setSelected(true);
		}
		
		bloombergCheckBox = new JCheckBox("Bloomberg");
		bloombergCheckBox.setBounds(150, 125, 97, 23);
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
				handleSave();
			}
		});
		saveButton.setBounds(131, 189, 89, 23);
		add(saveButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				handleCancel();
			}
		});
		cancelButton.setBounds(230, 189, 89, 23);
		add(cancelButton);
		
		setPreferredSize(new Dimension(371, 244));
	}

	protected void handleSave()
	{
		model.setCriteria(termsTextBox.getSelectedText());
		
		if(googleCheckBox.equals(true))
		{
			model.addWebsite(google);
		}
		else
		{
			if(model.getSites().contains(google))
			{
				model.getSites().remove(google);
			}
		}
		if(espnCheckBox.equals(true))
		{
			model.addWebsite(espn);
		}
		else
		{
			if(model.getSites().contains(espn))
			{
				model.getSites().remove(espn);
			}
		}
		if(newYorkTimesCheckBox.equals(true))
		{
			model.addWebsite(newYorkTimes);
		}
		else
		{
			if(model.getSites().contains(newYorkTimes))
			{
				model.getSites().remove(newYorkTimes);
			}
		}
		if(bloombergCheckBox.equals(true))
		{
			model.addWebsite(bloomberg);
		}
		else
		{
			if(model.getSites().contains(bloomberg))
			{
				model.getSites().remove(bloomberg);
			}
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
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
