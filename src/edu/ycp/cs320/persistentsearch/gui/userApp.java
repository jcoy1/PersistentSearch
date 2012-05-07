package edu.ycp.cs320.persistentsearch.gui;

import java.awt.CardLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import edu.ycp.cs320.persistentsearch.model.ResultCollection;
import edu.ycp.cs320.persistentsearch.model.Search;
import edu.ycp.cs320.persistentsearch.model.SearchException;
import edu.ycp.cs320.persistentsearch.model.User;
import edu.ycp.cs320.persistentsearch.server.Server;
import edu.ycp.cs320.persistentsearch.xml.Convert;

public class userApp extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// view names
	public static final String DEFAULT_VIEW_NAME = "default";
	public static final String NEW_SEARCH_VIEW_NAME = "new search";
	public static final String LIST_OF_SEARCHES_NAME = "list of searches";
	public static final String RESULT_COLLECTION_NAME = "result collection";
	
	
	private JPanel panel;
	private CardLayout cardLayout;
	
	// create the model object
	private User userModel = new User();
	
	public userApp() throws IOException {		
		
		
		// creates all the views and sets them in the view
		defaultUserView defaultView = new defaultUserView();
		defaultView.setModel(userModel); 
		//defaultView.setModel(userModel);

		final ResultCollection resultCollection = new ResultCollection();
		
		NewSearchView newSearchView = new NewSearchView();
		newSearchView.setModel(new Search(""));
		newSearchView.setNewSearchCallback(new NewSearchView.NewSearchCallback() {
			@Override
			public void onNewSearch(Search search)
			{
				userModel.addNewSearch(search);
				
				// perform the search
				try {
					ResultCollection newResultCollection = search.getSites().get(0).performSearch(search.getCriteria(), search.getResults());
					resultCollection.setName(search.getCriteria().getTerms());
					resultCollection.replaceResults(newResultCollection);
				} catch (SearchException e) {
					new SearchException("Exception Performing Search", e);
				}
			}
		});
		
		UserListOfSearchesView listOfSearchesView = new UserListOfSearchesView();
		listOfSearchesView.setModel(userModel);
		
		// scan search directory for previously-created searches
		populateUserSearches(userModel);
		
		listOfSearchesView.setViewResultsCallback(new UserListOfSearchesView.ViewResultsCallback() {
			@Override
			public void onViewResults(Search search)
			{
				resultCollection.setName(search.getCriteria().getTerms());
				resultCollection.replaceResults(search.getResults());
				switchView(RESULT_COLLECTION_NAME);
			}
		});
		
		ResultCollectionView resultCollectionView = new ResultCollectionView();
		resultCollectionView.setModel(resultCollection);

		// create a frame for the view, and display it
		instance = this;
		this.setTitle("User App");
		
		this.panel = new JPanel();
		this.cardLayout = new CardLayout(); 
		panel.setLayout(cardLayout);
		//adds all the view names to the panel so we can switch between them
		panel.add(defaultView, DEFAULT_VIEW_NAME);
		panel.add(newSearchView, NEW_SEARCH_VIEW_NAME);
		panel.add(listOfSearchesView, LIST_OF_SEARCHES_NAME);
		panel.add(resultCollectionView, RESULT_COLLECTION_NAME);
		
		this.setContentPane(panel);
		
		this.pack();
	}
	
	private void populateUserSearches(User model) throws IOException 
	{
		//clear list before adding new ones
		model.getProfile().clear();
		File searchDir = new File(Server.SEARCH_DIR);
		
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		Document doc;
		
		File[] contents = searchDir.listFiles();
		ArrayList<Search> tempSearches = new ArrayList<Search>();
		Search search = null;
				
		for (File f : contents) 
		{
			if (!f.isDirectory() && f.getName().endsWith(".search")) 
			{
				try {
					dbFactory = DocumentBuilderFactory.newInstance();
					dBuilder = dbFactory.newDocumentBuilder();
					try {
						doc = dBuilder.parse(f);
						
						search = Convert.convertSearchFromXML(doc.getDocumentElement());
						
						tempSearches.add(search);
						
					} catch (SAXException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}
			}
		}
		
		DocumentBuilder dBuilder1;
		//load the results from the list
		for(File f : contents)
		{
			if(!f.isDirectory() && f.getName().endsWith(".results"))
			{
				String resultHash = f.getName().substring(0, f.getName().length() - 8);
				
				for(int i = 0; i < tempSearches.size(); i++)
				{
					if(tempSearches.get(i).getContentHash().equals(resultHash))
					{
						try {
							dbFactory = DocumentBuilderFactory.newInstance();
							dBuilder1 = dbFactory.newDocumentBuilder();
							try {
								Document resultDoc = dBuilder1.parse(f);
								
								ResultCollection rc = Convert.convertResultCollectionFromXML(resultDoc.getDocumentElement());
								tempSearches.get(i).getResults().replaceResults(rc);
							} catch (SAXException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						} catch (ParserConfigurationException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		
		for(int j = 0; j < tempSearches.size(); j++)
		{
			model.addNewSearch(tempSearches.get(j));
		}
	}

	private static userApp instance;
	
	public static userApp getInstance() {
		return instance;
	}
	
	public void switchView(String viewName) {
		cardLayout.show(panel, viewName);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				userApp frame;
				try {
					frame = new userApp();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
