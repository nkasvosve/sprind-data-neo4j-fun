package com.challenge.domain.graph;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * Represents a product in the graph database
 * 
 * @author nickk
 */
@NodeEntity
@XmlRootElement(namespace = "http://com.challenge/1.0")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Product extends BaseEntity implements Comparable<Product> {

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "sku")
	private String sku;

	private BigDecimal price;

	private String currency;

	@RelatedTo(type = RelationshipTypes.BOUGHT_PRODUCT, direction = Direction.BOTH)
	private Set<User> users;

	@RelatedTo(type = RelationshipTypes.PRODUCT_BELONGS_TO, direction = Direction.BOTH)
	private Store store;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	/**
	 * Gets the sku.
	 * 
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * Sets the sku.
	 * 
	 * @param sku
	 *            the new sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * Gets the price.
	 * 
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 * 
	 * @param price
	 *            the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	 * Gets the users.
	 * 
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * Adds the user.
	 * 
	 * @param user
	 *            the user
	 */
	@Transactional
	public void addUser(User user) {
		if (users == null) {
			users = new TreeSet<User>();
		}

		if (user == null) {
			throw new IllegalStateException("Null user passed in");
		}
		users.add(user);
	}

	/**
	 * Sets the users.
	 * 
	 * @param users
	 *            the new users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Product other) {
		if (other == null) {
			return 0;
		}
		if (this.sku == null) {
			throw new IllegalStateException("Null sku found");
		}
		return this.sku.compareTo(other.getSku());
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
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Product other = (Product) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
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
		return "Product [name=" + sku + ", price=" + price + ", currency="
				+ currency + ", toString()=" + super.toString() + "]";
	}

	private static final long serialVersionUID = 1L;
}
