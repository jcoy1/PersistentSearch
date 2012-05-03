package edu.ycp.cs320.persistentsearch.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.ycp.cs320.persistentsearch.model.*;

public class Server {
	
	public static final String SEARCH_DIR = System.getProperty("user.home") + File.separator + "search";
	
	private static final int INTERVAL_MS = 60*5*1000; // wake up every 5 minutes
	
	public static void main(String[] args) throws InterruptedException 
	{
		while (true) {
			try {
				performSearches();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Thread.sleep(INTERVAL_MS);
		}
	}

	private static void performSearches() throws IOException 
	{
		File searchDir = new File(SEARCH_DIR);
		
		if (!searchDir.exists() || !searchDir.isDirectory()) 
		{
			throw new IllegalStateException("Search directory doesn't exist");
		}
		
		File[] contents = searchDir.listFiles();
		for (File f : contents) 
		{
			if (!f.isDirectory() && f.getName().endsWith(".search")) 
			{
				// This is a search: load it and perform the search, saving the results in a .results file
				Search search = new Search("");
				try {
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					try {
						Document doc = dBuilder.parse(f);
						
						NodeList nList = doc.getElementsByTagName("search");
						for (int i = 0; i < nList.getLength(); i++) 
						{
							if(nList.item(i).getNodeName().equals("criteria"))
							{
								search.setCriteria(nList.item(i).getTextContent());
							}
							else if(nList.item(i).getNodeName().equals("website"))
							{
								if(nList.item(i).getTextContent().contains("Bing"))
								{
									search.getSites().add((new Bing()));
								}
								if(nList.item(i).getTextContent().contains("ESPN"))
								{
									search.getSites().add((new ESPN()));
								}
								if(nList.item(i).getTextContent().contains("NewYorkTimes"))
								{
									search.getSites().add((new NewYorkTimes()));
								}
								if(nList.item(i).getTextContent().contains("Bloomberg"))
								{
									search.getSites().add((new Bloomberg()));
								}
							}
						}
						
					} catch (SAXException e) {
						e.printStackTrace();
					}
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//ends if for a .search file
		}//ends loop of going through files
	}
}
