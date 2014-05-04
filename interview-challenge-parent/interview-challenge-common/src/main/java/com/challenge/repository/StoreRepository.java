package com.challenge.repository;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;

import com.challenge.domain.graph.Store;

/**
 * @author nickk
 * 
 *         Used to access stores in neo4J database
 */

@Configurable
public interface StoreRepository extends GraphRepository<Store>,
		NamedIndexRepository<Store> {

	/**
	 * 
	 * @param name
	 * @return a store
	 */
	@Query("START store=node:Store(storeName={0}) RETURN store")
	Store findStoreByName(String name);

}
