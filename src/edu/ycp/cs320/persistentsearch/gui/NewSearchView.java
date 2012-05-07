package edu.ycp.cs320.persistentsearch.gui;

import java.util.Observable;
import java.util.Observer;

import edu.ycp.cs320.persistentsearch.model.Bing;
import edu.ycp.cs320.persistentsearch.model.Bloomberg;
import edu.ycp.cs320.persistentsearch.model.ESPN;
import edu.ycp.cs320.persistentsearch.model.NewYorkTimes;
import edu.ycp.cs320.persistentsearch.model.ResultCollection;
import edu.ycp.cs320.persistentsearch.model.Search;
import edu.ycp.cs320.persistentsearch.model.SearchException;
import edu.ycp.cs320.persistentsearch.server.Server;
import edu.ycp.cs320.persistentsearch.xml.Convert;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class NewSearchView extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	public interface NewSearchCallback 
	{
		public void onNewSearch(Search search);
	}
	
	public interface NewResultCollectionCallback
	{
		public void onNewResultCollection(ResultCollection resultCollection);
	}
	
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
	
	private NewSearchCallback newSearchCallback;
	
	public NewSearchView() 
	{
		bing = new Bing();
		espn = new ESPN();
		newYorkTimes = new NewYorkTimes();
		bloomberg = new Bloomberg();
		
		setLayout(null);
		
		JLabel termsLabel = new JLabel("Search Terms:");
		termsLabel.setBounds(121, 58, 103, 14);
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
		lblWebsitesToSearch.setBounds(121, 95, 128, 14);
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
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				}
			}
		});
		saveButton.setBounds(321, 266, 89, 23);
		add(saveButton);
		
		setPreferredSize(new Dimension(519, 300));
	}
	
	public void setNewSearchCallback(NewSearchCallback newSearchCallback) 
	{
		this.newSearchCallback = newSearchCallback;
	}
	
//	public void setNewResultCollectionCallback(NewResultCollectionCallback newResultCollectionCallback)
//	{
//		this.newResultCollectionCallback = newResultCollectionCallback;
//	}
	
	protected void handleSave() throws SearchException, IOException, ParserConfigurationException, TransformerException
	{
		model = new Search(termsTextBox.getText());
		
		if(bingCheckBox.isSelected())
		{
			model.addWebsite(bing);
		}
		if(espnCheckBox.isSelected())
		{
			model.addWebsite(espn);
		}
		if(newYorkTimesCheckBox.isSelected())
		{
			model.addWebsite(newYorkTimes);
		}
		if(bloombergCheckBox.isSelected())
		{
			model.addWebsite(bloomberg);
		}
		
		for(int i = 0; i < model.getResults().getResults().size(); i++)
		{
			System.out.println(model.getResults().getResults().get(i));
		}
		
		//add the search model to the user on the callback
		if (newSearchCallback != null) {
			newSearchCallback.onNewSearch(model);
		}
		
		//create file in search folder
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document newDoc = dBuilder.newDocument();
		Element root = Convert.convertSearchToXML(newDoc, model);
		newDoc.appendChild(root);
		
		TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
		
		StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(newDoc);
        trans.transform(source, result);
        String xmlString = sw.toString();
        
		//***** File Writer will create the file
		FileWriter writer = new FileWriter(Server.SEARCH_DIR + "/" + model.getContentHash() + ".search");
		try {
			writer.write(xmlString);
		} finally {
			writer.close();
		}
		
		
		/////////////////////////////////////////////////
		
		termsTextBox.setText("");
		userApp.getInstance().switchView(userApp.RESULT_COLLECTION_NAME);
	}
	
	protected void handleCancel()
	{
		termsTextBox.setText("");
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
