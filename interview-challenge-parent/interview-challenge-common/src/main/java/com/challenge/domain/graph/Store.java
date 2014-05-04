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
 * Represents a store in the graph database
 * 
 * @author nickk
 */
@NodeEntity
@XmlRootElement(namespace = "http://com.challenge/1.0")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Store extends BaseEntity implements Comparable<Store> {

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "storeName")
	private String storeName;

	@RelatedTo(type = RelationshipTypes.PRODUCT_BELONGS_TO, direction = Direction.BOTH)
	private Set<Product> products;

	@RelatedTo(type = RelationshipTypes.BELONGS_TO, direction = Direction.BOTH)
	private Set<User> users;

	public Store() {
		users = new TreeSet<User>();
		products = new TreeSet<Product>();
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Override
	public String toString() {
		return "Store [storeName=" + storeName + "]";
	}

	@Override
	public int compareTo(Store other) {
		if (other == null) {
			return 0;
		}
		if (this.storeName == null) {
			throw new IllegalStateException("Null storeName found");
		}
		return this.storeName.compareTo(other.getStoreName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((storeName == null) ? 0 : storeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

}
