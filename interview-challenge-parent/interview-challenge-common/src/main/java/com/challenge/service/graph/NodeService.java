package com.challenge.service.graph;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.neo4j.core.GraphDatabase;

import com.challenge.domain.graph.Product;
import com.challenge.domain.graph.Store;
import com.challenge.domain.graph.User;

/**
 * 
 * @author nickk
 */

public interface NodeService {

	/**
	 * Gets the graph database.
	 * 
	 * @return the graph database
	 */
	GraphDatabase getGraphDatabase();

	/**
	 * Gets the all users.
	 * 
	 * @return the all users
	 */
	Collection<User> getAllUsers();

	/**
	 * Gets the all products.
	 * 
	 * @return the all products
	 */
	Collection<Product> getAllProducts();

	/**
	 * Save.
	 * 
	 * @param object
	 *            the object
	 */
	void save(Object object);

	/**
	 * Gets the all users as xml.
	 * 
	 * @return the all users as xml
	 */
	String getAllUsersAsXML();

	/**
	 * Gets the all product as xml.
	 * 
	 * @return the all product as xml
	 */
	String getAllProductAsXML();

	/**
	 * Find one.
	 * 
	 * @param id
	 *            the id
	 * @param clazz
	 *            the clazz
	 * @return the object
	 */
	Object findOne(Long id, Class<?> clazz);

	/**
	 * Gets the user count.
	 * 
	 * @return the user count
	 */
	long getUserCount();

	/**
	 * Gets the product count.
	 * 
	 * @return the product count
	 */
	long getProductCount();

	/**
	 * Fetch.
	 * 
	 * @param object
	 *            the object
	 */
	void fetch(Object object);

	/**
	 * Find user by name.
	 * 
	 * @param name
	 *            the name
	 * @return the user
	 */
	User findUserByName(String name);

	/**
	 * Find product by name.
	 * 
	 * @param name
	 *            the name
	 * @return the product
	 */
	Product findProductByName(String name);

	/**
	 * Gets the user friends.
	 * 
	 * @param id
	 *            the id
	 * @return the user friends
	 */
	Set<User> getUserFriends(Long id);

	/**
	 * Gets the purchases for user.
	 * 
	 * @param id
	 *            the id
	 * @return the purchases for user
	 */
	Set<Product> getPurchasesForUser(Long id);

	/**
	 * Gets the users for product.
	 * 
	 * @param id
	 *            the id
	 * @return the users for product
	 */
	Set<User> getUsersForProduct(Long id);

	/**
	 * Adds the product.
	 * 
	 * @param user
	 *            the user
	 * @param product
	 *            the product
	 */
	void addProduct(User user, Product product);

	/**
	 * Adds the friend.
	 * 
	 * @param user
	 *            the user
	 * @param friend
	 *            the friend
	 */
	void addFriend(User user, User friend);

	void addUserToStore(User user);

	void addProductToStore(Product product);

	String getStoreName();

	Store fetchStore();

	void createStore();
}
