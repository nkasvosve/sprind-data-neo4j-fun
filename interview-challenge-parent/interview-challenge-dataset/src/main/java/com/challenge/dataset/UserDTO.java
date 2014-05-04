package com.challenge.dataset;

import java.util.ArrayList;
import java.util.List;

import com.challenge.domain.graph.User;

/**
 * Used for transferring data from parsed XML to actual domain object
 * 
 * @author nickk
 */
public class UserDTO {

	private String firstName;
	private String lastName;
	private String currency;
	private List<String> productNames = new ArrayList<String>();
	private List<String> friendNames = new ArrayList<String>();

	/**
	 * To user.
	 * 
	 * @return the user
	 */
	public User toUser() {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setCurrency(currency);
		return user;
	}

	/**
	 * Gets the product names.
	 * 
	 * @return the product names
	 */
	public List<String> getProductNames() {
		return productNames;
	}

	/**
	 * Adds the product name.
	 * 
	 * @param productName
	 *            the product name
	 */
	public void addProductName(String productName) {
		productNames.add(productName);
	}

	/**
	 * Adds the friend name.
	 * 
	 * @param name
	 *            the name
	 */
	public void addFriendName(String name) {
		friendNames.add(name);
	}

	/**
	 * Gets the first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the friend names.
	 * 
	 * @return the friend names
	 */
	public List<String> getFriendNames() {
		return friendNames;
	}

	/**
	 * Sets the first name.
	 * 
	 * @param firstName
	 *            the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param lastName
	 *            the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the currency.
	 * 
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets the currency.
	 * 
	 * @param currency
	 *            the new currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
