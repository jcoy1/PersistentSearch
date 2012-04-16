package edu.ycp.cs320.persistentsearch.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Bing implements Website {

	private static final String BING_API_KEY = "F40389E08CFD87283AE75805E2CC220B0E7FD6C3";

	@Override
	public ResultCollection performSearch(SearchTerm sT, ResultCollection resultsToUpdate) throws SearchException 
	{
		String bingURL = "http://api.bing.net/xml.aspx?Appid=" + BING_API_KEY + "&query=" + sT.getTerms() + "&sources=web";
		
		try {
			URL url = new URL(bingURL);
			URLConnection urlConnection = url.openConnection();
			InputStream in = urlConnection.getInputStream();
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(in);
				
				NodeList nList = doc.getElementsByTagName("web:WebResult");
				for (int i = 0; i < nList.getLength(); i++) 
				{
					Node webResultElt = nList.item(i);
					//System.out.println("Found web:Result");
					
					// see if there is a Web:Url child
					NodeList childList = webResultElt.getChildNodes();
					for (int j = 0; j < childList.getLength(); j++) 
					{
						Node child = childList.item(j);
						//System.out.println("Found child: " + child.getNodeName());
						if (child.getNodeName().equals("web:Url")) 
						{
							// This is a URL
							String childUrl = child.getTextContent();
							//System.out.println("Url is " + childUrl);
							
							resultsToUpdate.addNewResult(childUrl);
						}
					}
				}
				
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new SearchException("IO exception performing search", e);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return resultsToUpdate;
	}
	
	public static void main(String[] args) {
		String terms = "giants";
		String bingURL = "http://api.bing.net/xml.aspx?Appid=" + BING_API_KEY + "&query=" + terms + "&sources=web";
		
		try {
			URL url = new URL(bingURL);
			URLConnection urlConnection = url.openConnection();
			InputStream in = urlConnection.getInputStream();
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(in);
				
				NodeList nList = doc.getElementsByTagName("web:WebResult");
				for (int i = 0; i < nList.getLength(); i++) {
					Node webResultElt = nList.item(i);
					System.out.println("Found web:Result");
					
					// see if there is a Web:Url child
					NodeList childList = webResultElt.getChildNodes();
					for (int j = 0; j < childList.getLength(); j++) {
						Node child = childList.item(j);
						//System.out.println("Found child: " + child.getNodeName());
						if (child.getNodeName().equals("web:Url")) {
							// This is a URL
							String childUrl = child.getTextContent();
							System.out.println("Url is " + childUrl);
						}
					}
				}
				
				/*
				while (true) {
					String line = reader.readLine();
					if (line == null) {
						break;
					}
					System.out.println(line);
				}
				*/
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

}