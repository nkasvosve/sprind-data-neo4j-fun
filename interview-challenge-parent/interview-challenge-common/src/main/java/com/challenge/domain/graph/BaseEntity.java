package com.challenge.domain.graph;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.neo4j.annotation.GraphId;

/**
 * Base class for Neo4j entities
 * 
 * @author nickk
 */

@XmlRootElement(namespace = "http://challenge.com/1.0")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class BaseEntity implements Serializable {

	@GraphId
	protected Long id;

	protected Boolean online;

	protected Boolean deleted;

	/**
	 * Instantiates a new base entity.
	 */
	public BaseEntity() {
		online = Boolean.TRUE;
		deleted = Boolean.FALSE;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the online.
	 * 
	 * @return the online
	 */
	public Boolean getOnline() {
		return online;
	}

	/**
	 * Checks if is online.
	 * 
	 * @return the boolean
	 */
	public Boolean isOnline() {
		return online;
	}

	/**
	 * Sets the online.
	 * 
	 * @param online
	 *            the new online
	 */
	public void setOnline(Boolean online) {
		this.online = online;
	}

	/**
	 * Gets the deleted.
	 * 
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * Sets the deleted.
	 * 
	 * @param deleted
	 *            the new deleted
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((online == null) ? 0 : online.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (online == null) {
			if (other.online != null)
				return false;
		} else if (!online.equals(other.online))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", online=" + online + ", deleted="
				+ deleted + "]";
	}

	private static final long serialVersionUID = 1L;
}
