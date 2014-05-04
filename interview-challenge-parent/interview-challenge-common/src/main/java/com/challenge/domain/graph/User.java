package com.challenge.domain.graph;

import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

/**
 * Represents a user in the graph database
 * 
 * @author nickk
 */
@NodeEntity
@XmlRootElement(namespace = "http://com.challenge/1.0")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class User extends BaseEntity implements Comparable<User> {

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "firstName")
	private String firstName;

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "lastName")
	private String lastName;

	private String currency;

	@RelatedTo(type = RelationshipTypes.IS_FRIEND_OF, direction = Direction.BOTH)
	private Set<User> friends;

	@RelatedTo(type = RelationshipTypes.BOUGHT_PRODUCT, direction = Direction.BOTH)
	private Set<Product> products;

	@RelatedTo(type = RelationshipTypes.BELONGS_TO, direction = Direction.BOTH)
	private Store store;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	/**
	 * Instantiates a new user.
	 */
	public User() {
		friends = new TreeSet<User>();
		products = new TreeSet<Product>();
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

	/**
	 * Gets the first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
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
	 * Gets the friends.
	 * 
	 * @return the friends
	 */
	public Set<User> getFriends() {
		return friends;
	}

	/**
	 * Sets the friends.
	 * 
	 * @param friends
	 *            the new friends
	 */
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	/**
	 * Gets the products.
	 * 
	 * @return the products
	 */
	public Set<Product> getProducts() {
		return products;
	}

	/**
	 * Sets the products.
	 * 
	 * @param products
	 *            the new products
	 */
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(User other) {
		if (other == null) {
			return 0;
		}
		return this.lastName.compareTo(other.getLastName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.domain.graph.BaseEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.domain.graph.BaseEntity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.domain.graph.BaseEntity#toString()
	 */
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName
				+ ", currency=" + currency + ", toString()=" + super.toString()
				+ "]";
	}

	private static final long serialVersionUID = 1L;

}
