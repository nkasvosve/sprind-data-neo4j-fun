package com.challenge.domain.graph;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author nickk
 */

@XmlRootElement(namespace = "http://com.challenge/1.0")
public class RelationshipTypes {

	public static final String IS_FRIEND_OF = "IS_FRIEND_OF";
	public static final String BOUGHT_PRODUCT = "BOUGHT_PRODUCT";
	public static final String BELONGS_TO = "BELONGS_TO";
	public static final String PRODUCT_BELONGS_TO = "PRODUCT_BELONGS_TO";

}
