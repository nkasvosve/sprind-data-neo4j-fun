package com.challenge.dataset;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Parses an XML file and produces a collection of users.
 * 
 * 
 * @author nickk
 */
public class UserParser extends DefaultHandler {

	private static final String BOUGHT_PRODUCT = "bought-product";

	private List<UserDTO> users;

	private UserDTO user;

	/**
	 * Instantiates a new user parser.
	 */
	public UserParser() {
	}

	/**
	 * Users.
	 * 
	 * @return the list
	 */
	public List<UserDTO> Users() {
		return users;
	}

	/**
	 * Parses the.
	 * 
	 * @param stream
	 *            the stream
	 */
	public void parse(InputStream stream) {
		if (stream == null) {
			throw new IllegalArgumentException(
					"Null input stream found for parsing users");
		}
		users = new ArrayList<UserDTO>();
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser sp = spf.newSAXParser();

			sp.parse(stream, this);

		} catch (ParserConfigurationException e) {
			log.error("ParserConfigurationException - Error parsing document : "
					+ e);
		} catch (SAXException e) {
			log.error("SAXException - Error parsing document : " + e);
		} catch (IOException e) {
			log.error("IOException - Error parsing document : " + e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String nsURI, String strippedName, String tagName,
			Attributes attributes) throws SAXException {
		if (tagName.equalsIgnoreCase(USER)) {
			String firstName = attributes.getValue(FIRST_NAME);
			String lastName = attributes.getValue(LAST_NAME);
			String currency = attributes.getValue(CURRENCY);
			user = new UserDTO();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setCurrency(currency);
			users.add(user);
		} else if (tagName.equalsIgnoreCase(BOUGHT_PRODUCT)) {
			String productName = attributes.getValue(NAME);
			user.addProductName(productName);
		} else if (tagName.equalsIgnoreCase(FRIENDS_WITH)) {
			String name = attributes.getValue(LAST_NAME);
			user.addFriendName(name);
		}
	}

	private static final String USER = "user";

	private static final String FIRST_NAME = "firstName";

	private static final String NAME = "name";

	private static final String LAST_NAME = "lastName";

	private static final String CURRENCY = "currency";

	private static final String FRIENDS_WITH = "friends-with";

	private static final Logger log = LoggerFactory.getLogger(UserParser.class);

}
