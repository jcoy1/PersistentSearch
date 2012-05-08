package edu.ycp.cs320.persistentsearch.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
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
import org.xml.sax.SAXException;

import edu.ycp.cs320.persistentsearch.model.*;
import edu.ycp.cs320.persistentsearch.xml.Convert;

public class Server {
	
	public static final String SEARCH_DIR = System.getProperty("user.home") + File.separator + "search";
	
	private static final int INTERVAL_MS = 30*1000; //wake up every 30 seconds, for 5 minutes use: 60*5*1000
	
	public static void main(String[] args) throws InterruptedException, SearchException, TransformerException 
	{
		while (true) {
			try {
				performSearches();
			} catch (IOException e) {
				throw new SearchException("IO exception performing search", e);
			}
			Thread.sleep(INTERVAL_MS);
		}
	}

	private static void performSearches() throws IOException, SearchException, TransformerException 
	{		
		File searchDir = new File(SEARCH_DIR);
		
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		Document doc;
		
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
					dbFactory = DocumentBuilderFactory.newInstance();
					dBuilder = dbFactory.newDocumentBuilder();
					try {
						doc = dBuilder.parse(f);
						
						search = Convert.convertSearchFromXML(doc.getDocumentElement());
						
					} catch (SAXException e) {
						throw new SearchException("SAX exception performing search", e);
					}
				} catch (ParserConfigurationException e) {
					throw new SearchException("Parser Configuration exception performing search", e);
				}
				
				if(!search.getCriteria().equals(null))
				{
					search.performSearch();
					FileWriter writer = new FileWriter(Server.SEARCH_DIR + "/" + search.getContentHash() + ".results");
					
					Document newDoc = dBuilder.newDocument();
					Element root = Convert.convertResultCollectionToXML(newDoc, search.getResults());
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
					try{
						writer.write(xmlString);
					}finally{
						writer.close();
					}
				}
			}//ends if for a .search file
		}//ends loop of going through files
		
		System.out.println("Performed search from server");
	}
}
