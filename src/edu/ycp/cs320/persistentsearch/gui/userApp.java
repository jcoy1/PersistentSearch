package edu.ycp.cs320.persistentsearch.gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.ycp.cs320.persistentsearch.model.ResultCollection;
import edu.ycp.cs320.persistentsearch.model.Search;
import edu.ycp.cs320.persistentsearch.model.SearchException;
import edu.ycp.cs320.persistentsearch.model.User;

public class userApp extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// view names
	public static final String DEFAULT_VIEW_NAME = "default";
	public static final String NEW_SEARCH_VIEW_NAME = "new search";
	public static final String LIST_OF_SEARCHES_NAME = "list of searches";
	public static final String RESULT_COLLECTION_NAME = "result collection";
	public static final String EDIT_SEARCH_VIEW_NAME = "edit search";
	
	
	private JPanel panel;
	private CardLayout cardLayout;
	
	// create the model object
	private User userModel = new User();
	
	public userApp() {		
		// creates all the views and sets them in the view
		defaultUserView defaultView = new defaultUserView();
		defaultView.setModel(userModel);

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
		
		EditSearchView editSearchView = new EditSearchView(new Search(" "));
		editSearchView.setModel(new Search(""));
		
		UserListOfSearchesView listOfSearchesView = new UserListOfSearchesView();
		listOfSearchesView.setModel(userModel);
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
		panel.add(editSearchView, EDIT_SEARCH_VIEW_NAME);
		panel.add(listOfSearchesView, LIST_OF_SEARCHES_NAME);
		panel.add(resultCollectionView, RESULT_COLLECTION_NAME);
		
		this.setContentPane(panel);
		
		this.pack();
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
				userApp frame = new userApp();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
