package com.challenge.repository;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;

import com.challenge.domain.graph.Product;
import com.challenge.domain.graph.User;

/**
 * @author nickk
 * 
 *         Used to access users in neo4J database
 */
@Configurable
public interface UserRepository extends GraphRepository<User>,
		NamedIndexRepository<User> {

	/**
	 * 
	 * @param user
	 * @return a user
	 */

	@Query("START user=node:User(lastName={0}) RETURN user")
	User findUserByName(String name);

	/**
	 * 
	 * @param user
	 * @return a collection of users
	 */

	@Query("START user=node({0}) MATCH products-[:BOUGHT_PRODUCT] RETURN  nodes(products)")
	Iterable<Iterable<Product>> getUserProducts(User user);

	/**
	 * 
	 * @param user
	 * @return a collection of users
	 */
	@Query("START user=node({0}) MATCH friends-[:IS_FRIEND_OF] RETURN  nodes(friends)")
	Iterable<Iterable<User>> getUserFriends(User user);

}
