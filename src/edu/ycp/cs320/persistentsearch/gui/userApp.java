package edu.ycp.cs320.persistentsearch.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import edu.ycp.cs320.persistentsearch.model.User;
//import edu.ycp.cs320.persistentsearch.model.Search;
//import edu.ycp.cs320.persistentsearch.model.ResultCollection;

public class userApp {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// create the model object
				User userModel = new User();
				
				// create the view and set it in the view
				defaultUserView defaultView = new defaultUserView();
				defaultView.setModel(userModel);

				// create a frame for the view, and display it
				JFrame frame = new JFrame();
				frame.setTitle("User App");
				frame.add(defaultView);
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
