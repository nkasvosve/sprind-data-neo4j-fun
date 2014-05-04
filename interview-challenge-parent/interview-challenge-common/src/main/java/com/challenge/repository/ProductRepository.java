package com.challenge.repository;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;

import com.challenge.domain.graph.Product;


/**
 * @author nickk
 * 
 * Used to access products in neo4J database
 */

@Configurable
public interface ProductRepository extends GraphRepository<Product>,
		NamedIndexRepository<Product> {

	/**
	 * 
	 * @param name
	 * @return a product
	 */
	@Query("START product=node:Product(sku={0}) RETURN product")
	Product findProductByName(String name);
	
}
