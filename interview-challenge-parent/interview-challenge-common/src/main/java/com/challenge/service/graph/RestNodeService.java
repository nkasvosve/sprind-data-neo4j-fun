package com.challenge.service.graph;

import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nickk
 * 
 *         Used for accessing Neo4J database through Spring Data Neo4j REST
 *         interface It specifically creates a SpringRestGraphDatabase object to
 *         do so
 */
@Transactional
public class RestNodeService extends AbstractNodeService {

	public RestNodeService(SpringRestGraphDatabase restGraphDatabaseService,
			String storeName) {
		super(restGraphDatabaseService, storeName);
		template = new Neo4jTemplate((GraphDatabase) restGraphDatabaseService);
		this.graphDatabase = restGraphDatabaseService;
		createRepositories();
	}
}
