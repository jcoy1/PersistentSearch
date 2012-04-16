package edu.ycp.cs320.persistentsearch.model;

/**
 * Indicates that a search failed.
 * @author jcoy1
 */
public class SearchException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * @param msg message explaining the reason for the exception
	 */
	public SearchException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor.
	 * @param msg message explaining the reason for the exception
	 * @param cause the exception that caused this exception
	 */
	public SearchException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
