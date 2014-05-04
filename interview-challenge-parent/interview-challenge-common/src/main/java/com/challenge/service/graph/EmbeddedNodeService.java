package com.challenge.service.graph;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.data.neo4j.support.DelegatingGraphDatabase;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nickk
 * 
 *         Used for ccessing Neo4J database through Spring Data
 *         Neo4j embedded interface. It specifically creates a
 *         DelegatingGraphDatabase object to do so
 */
@Transactional
public class EmbeddedNodeService extends AbstractNodeService {

	public EmbeddedNodeService(GraphDatabase g,
			GraphDatabaseService graphDatabaseService, String storeName) {
		super(graphDatabaseService,storeName);
		this.graphDatabase = g;
		this.template = new Neo4jTemplate(graphDatabase);
		createRepositories();
	}

	public EmbeddedNodeService(GraphDatabaseService graphDatabaseService, String storeName) {
		super(graphDatabaseService,storeName);
		this.graphDatabase = new DelegatingGraphDatabase(graphDatabaseService);
		this.template = new Neo4jTemplate(graphDatabase);
		createRepositories();
	}
}
