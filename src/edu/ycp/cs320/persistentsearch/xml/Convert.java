package edu.ycp.cs320.persistentsearch.xml;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.ycp.cs320.persistentsearch.model.Bing;
import edu.ycp.cs320.persistentsearch.model.ESPN;
import edu.ycp.cs320.persistentsearch.model.ResultCollection;
import edu.ycp.cs320.persistentsearch.model.Search;
import edu.ycp.cs320.persistentsearch.model.SearchTerm;
import edu.ycp.cs320.persistentsearch.model.Website;

public class Convert {
	/**
	 * Convert a {@link Search} object to an element in an XML document.
	 * 
	 * @param doc     the XML document
	 * @param search  the Search object to convert
	 * @return an element in the XML document containing the data for the search object
	 */
	public static Element convertSearchToXML(Document doc, Search search) 
	{

		////////////////////////
		//Creating the XML tree

		//create the root element and add it to the document
		Element root = doc.createElement("search");
		
		SearchTerm criteria = search.getCriteria();
		Element criteriaElt = convertSearchTermToXML(doc, criteria);
		root.appendChild(criteriaElt);
		
		for (Website website : search.getSites()) 
		{
			Element websiteElt = convertWebsiteToXML(doc, website);
			root.appendChild(websiteElt);
		}
		
		return root;
	}

	/**
	 * Convert a {@link Website} object to an element in an XML document.
	 * 
	 * @param doc     the XML document
	 * @param website the Website object to convert
	 * @return an element in the XML document containing the data for the website object
	 */
	private static Element convertWebsiteToXML(Document doc, Website website) 
	{
		String clsName = website.getClass().getName();
		Element websiteElt = doc.createElement("website");
		websiteElt.setTextContent(clsName);
		return websiteElt;
	}

	/**
	 * Convert a {@link SearchTerm} object to an element in an XML document.
	 * 
	 * @param doc     the XML document
	 * @param criteria the SearchTerm object to convert
	 * @return an element in the XML document containing the data for the SearchTerm object
	 */
	private static Element convertSearchTermToXML(Document doc, SearchTerm criteria) 
	{
		Element criteriaElt = doc.createElement("criteria");
		criteriaElt.setTextContent(criteria.getTerms());
		return criteriaElt;
	}
	
	/**
	 * Convert a {@link ResultCollection} object to an element in an XML document.
	 * 
	 * @param doc     the XML document
	 * @param resultCollection the ResultCollection object to convert
	 * @return an element in the XML document containing the data for the ResultCollection object
	 */
	public static Element convertResultCollectionToXML(Document doc, ResultCollection resultCollection) 
	{
		// TODO
		Element root = doc.createElement("resultCollection");
			
		String name = resultCollection.getName();
		Element nameElt = doc.createElement("name");
		nameElt.setTextContent(name);
		root.appendChild(nameElt);
		
		for (String s : resultCollection.getResults()) 
		{
			Element resultElt = doc.createElement("result");
			resultElt.setTextContent(s);
			root.appendChild(resultElt);
		}
		
		return root;
	}
	
	/**
	 * Convert an element in an XML document into a {@link ResultCollection} object.
	 * 
	 * @param resultCollectionElt an element in an XML document containing the data of a ResultCollection
	 * @return a ResultCollection
	 */
	public static ResultCollection convertResultCollectionFromXML(Element resultCollectionElt) 
	{
		// TODO
		ResultCollection resultCollection = new ResultCollection();
		
		NodeList childList = resultCollectionElt.getChildNodes();
		for (int j = 0; j < childList.getLength(); j++) 
		{
			Node child = childList.item(j);
			if(child.getNodeName().equals("name"))
			{
				resultCollection.setName(child.getTextContent());
			}
			if(child.getNodeName().equals("result"))
			{
				resultCollection.addNewResult(child.getTextContent());
			}
		}
		
		return resultCollection;
	}
	
	public static void main(String[] args) throws Exception 
	{
		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = fac.newDocumentBuilder();
		
//		Search search = new Search("giants");
//		search.addWebsite(new Bing());
//		search.addWebsite(new ESPN());
		
		ResultCollection resultCollection = new ResultCollection();
		resultCollection.setName("giants");
		resultCollection.addNewResult("giants.com");
		resultCollection.addNewResult("espn.com");
		
		// test content hash function for search
		//System.out.println("Search content hash is " + search.getContentHash());
		
		Document doc = builder.newDocument();
		Element root = convertResultCollectionToXML(doc, resultCollection);
		doc.appendChild(root);
		
		TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");

        //create string from xml tree
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc);
        trans.transform(source, result);
        String xmlString = sw.toString();

        //print xml
        System.out.println("Here's the xml:\n\n" + xmlString);
        
        //test going from xml to result collection
        ResultCollection resultCollectionFromXML = convertResultCollectionFromXML(root);
        System.out.println("Here is the result collection: ");
        System.out.println("The name is: " + resultCollectionFromXML.getName());
        for(int i = 0; i < resultCollectionFromXML.getResults().size(); i++)
        {
        	System.out.println("Result[" + i + "] is: " + resultCollectionFromXML.getResults().get(i));
        }
	}
}
