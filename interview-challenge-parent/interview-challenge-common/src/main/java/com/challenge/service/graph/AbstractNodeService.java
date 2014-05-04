package com.challenge.service.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.domain.graph.Product;
import com.challenge.domain.graph.Store;
import com.challenge.domain.graph.User;
import com.challenge.util.JaxBUtil;

/**
 * 
 * @author nickk
 * 
 *         A base class for accessing Neo4J database through Spring Data Neo4j.
 */
@Transactional
public abstract class AbstractNodeService implements NodeService {

	protected Neo4jTemplate template;

	protected GraphDatabase graphDatabase;

	protected GraphDatabaseService graphDatabaseService;

	protected GraphRepository<User> userRepository;

	protected GraphRepository<Product> productRepository;

	protected GraphRepository<Store> storeRepository;

	private String storeName;

	private Store store;

	/**
	 * Instantiates a new abstract node service.
	 * 
	 * @param graphDatabaseService
	 *            the graph database service
	 */
	public AbstractNodeService(GraphDatabaseService graphDatabaseService,
			String storeName) {
		this.graphDatabaseService = graphDatabaseService;
		this.storeName = storeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.service.graph.NodeService#getGraphDatabase()
	 */
	public GraphDatabase getGraphDatabase() {
		return graphDatabase;
	}

	/**
	 * Creates the repositories.
	 */
	protected void createRepositories() {
		userRepository = template.repositoryFor(User.class);
		productRepository = template.repositoryFor(Product.class);
		storeRepository = template.repositoryFor(Store.class);
	}

	public void addUserToStore(User user) {
		if (user == null) {
			throw new IllegalStateException(
					"Null user passed in as to add to store");
		}
		if (store == null) {
			throw new IllegalStateException("Null store found");
		}
		user.setStore(store);
		store.getUsers().add(user);
		save(user);
		save(store);
	}

	public void addProductToStore(Product product) {
		if (product == null) {
			throw new IllegalStateException(
					"Null product passed in as to add to store");
		}
		if (store == null) {
			throw new IllegalStateException("Null store found");
		}

		product.setStore(store);
		store.getProducts().add(product);
		save(product);
		save(store);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.service.graph.NodeService#getAllUsers()
	 */
	@Override
	public Collection<User> getAllUsers() {
		EndResult<User> result = (EndResult<User>) template.findAll(User.class);
		Iterator<User> it = result.iterator();

		Collection<User> users = new ArrayList<User>();
		while (it.hasNext()) {
			User user = it.next();
			users.add(user);
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.challenge.service.graph.NodeService#addProduct(com.challenge.domain
	 * .graph.User, com.challenge.domain.graph.Product)
	 */
	@Transactional
	public void addProduct(User user, Product product) {

		if (user.getProducts() == null) {
			throw new IllegalStateException("Null user products passed in");
		}
		if (product == null) {
			throw new IllegalStateException("Null product passed in");
		}
		if (product.getUsers() == null) {
			throw new IllegalStateException("Null product users passed in");
		}

		user.getProducts().add(product);
		product.getUsers().add(user);
		save(user);
		save(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.challenge.service.graph.NodeService#addFriend(com.challenge.domain
	 * .graph.User, com.challenge.domain.graph.User)
	 */
	@Transactional
	public void addFriend(User user, User friend) {

		if (user == null) {
			throw new IllegalStateException(
					"Null User passed in as to add friend");
		}
		if (friend == null) {
			throw new IllegalStateException("Null friend passed in as friend");
		}

		if (user.getFriends() == null) {
			throw new IllegalStateException("Null user friends passed in");
		}
		if (friend.getFriends() == null) {
			throw new IllegalStateException("Null friend friends passed in");
		}

		user.getFriends().add(friend);
		friend.getFriends().add(user);
		save(user);
		save(friend);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.service.graph.NodeService#getAllProducts()
	 */
	@Override
	public Collection<Product> getAllProducts() {
		EndResult<Product> result = (EndResult<Product>) template
				.findAll(Product.class);

		Iterator<Product> it = result.iterator();

		Collection<Product> products = new ArrayList<Product>();
		while (it.hasNext()) {
			Product product = it.next();
			products.add(product);
		}
		return products;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.service.graph.NodeService#save(java.lang.Object)
	 */
	public void save(Object object) {
		try {
			template.save(object);
		} catch (Exception e) {
			LOG.error(
					String.format("Error saving %s",
							ToStringBuilder.reflectionToString(object)), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.service.graph.NodeService#getAllUsersAsXML()
	 */
	@Override
	public String getAllUsersAsXML() {

		StringBuilder builder = new StringBuilder(DECLARATION)
				.append("\n<user>");

		Collection<User> users = getAllUsers();

		Map<String, Object> props = new HashMap<String, Object>();
		props.put("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);

		for (User user : users) {
			try {
				builder.append(JaxBUtil.INSTANCE.toXML(user, props));
			} catch (Exception e) {
				LOG.error(e);
			}
		}
		builder.append("</user>");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.service.graph.NodeService#getAllProductAsXML()
	 */
	@Override
	public String getAllProductAsXML() {

		StringBuilder builder = new StringBuilder(DECLARATION)
				.append("\n<product>");

		Collection<Product> products = getAllProducts();

		Map<String, Object> props = new HashMap<String, Object>();
		props.put("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);

		for (Product product : products) {
			try {
				builder.append(JaxBUtil.INSTANCE.toXML(product, props));
			} catch (Exception e) {
				LOG.error(e);
			}
		}
		builder.append("</product>");
		return builder.toString();
	}

	/**
	 * Run query.
	 * 
	 * @param query
	 *            the query
	 * @param params
	 *            the params
	 * @return the result
	 */
	@SuppressWarnings("unused")
	private Result<Map<String, Object>> runQuery(String query,
			Map<String, Object> params) {
		try {
			return template.query(query, new HashMap<String, Object>());
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.challenge.service.graph.NodeService#findUserByName(java.lang.String)
	 */
	@Override
	public User findUserByName(String lastName) {
		return userRepository.findByPropertyValue(LAST_NAME, lastName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.challenge.service.graph.NodeService#findProductByName(java.lang.String
	 * )
	 */
	@Override
	public Product findProductByName(String productName) {
		return productRepository.findByPropertyValue(SKU, productName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.challenge.service.graph.NodeService#getUserFriends(java.lang.Long)
	 */
	@Override
	public Set<User> getUserFriends(Long id) {
		try {
			User user = template.findOne(id, User.class);
			template.fetch(user.getFriends());
			return user.getFriends();
		} catch (Exception e) {
			LOG.error("Error", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.challenge.service.graph.NodeService#getUsersForProduct(java.lang.
	 * Long)
	 */
	@Override
	public Set<User> getUsersForProduct(Long id) {
		try {
			Product product = template.findOne(id, Product.class);
			template.fetch(product.getUsers());
			return product.getUsers();
		} catch (Exception e) {
			LOG.error("Error", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.challenge.service.graph.NodeService#getPurchasesForUser(java.lang
	 * .Long)
	 */
	@Override
	public Set<Product> getPurchasesForUser(Long id) {
		try {
			User user = template.findOne(id, User.class);
			template.fetch(user.getFriends());
			return user.getProducts();
		} catch (Exception e) {
			LOG.error("Error", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.service.graph.NodeService#getUserCount()
	 */
	@Override
	public long getUserCount() {
		return template.count(User.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.service.graph.NodeService#getProductCount()
	 */
	public long getProductCount() {
		return template.count(Product.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.service.graph.NodeService#findOne(java.lang.Long,
	 * java.lang.Class)
	 */
	@Override
	public Object findOne(Long id, Class<?> clazz) {
		return template.findOne(id, clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.challenge.service.graph.NodeService#fetch(java.lang.Object)
	 */
	@Override
	public void fetch(Object object) {
		template.fetch(object);
	}

	@Override
	public Store fetchStore() {
		if (store == null) {
			store = storeRepository.findByPropertyValue(STORE_NAME, storeName);
		}
		return store;
	}

	@Override
	public void createStore() {
		this.store = new Store();
		store.setStoreName(storeName);
		storeRepository.save(store);
	}

	@Override
	public String getStoreName() {
		return storeName;
	}

	protected static final String LAST_NAME = "lastName";

	protected static final String SKU = "sku";
	private static final String STORE_NAME = "storeName";
	final String DECLARATION = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";

	private static final Logger LOG = Logger
			.getLogger(AbstractNodeService.class);

}
