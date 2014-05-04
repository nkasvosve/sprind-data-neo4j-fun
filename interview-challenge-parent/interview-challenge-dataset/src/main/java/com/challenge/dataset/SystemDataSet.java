package com.challenge.dataset;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.challenge.domain.graph.Product;
import com.challenge.domain.graph.Store;
import com.challenge.domain.graph.User;
import com.challenge.service.graph.NodeService;

/**
 * DataSet for populating a database with data.
 * 
 * @author nickk
 */
public class SystemDataSet {

	private NodeService nodeService;

	/**
	 * Instantiates a new system data set.
	 * 
	 * @param nodeService
	 *            the node service
	 */
	public SystemDataSet(NodeService nodeService) {
		this.nodeService = nodeService;
		try {
			fetchOrCreateStore();
			create();
		} catch (FileNotFoundException e) {
			LOG.error("Error creating data", e);
		}
	}

	private void fetchOrCreateStore() {
		Store store = nodeService.fetchStore();
		if (store == null) {
			nodeService.createStore();
		}
	}

	/**
	 * Creates the.
	 * 
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public void create() throws FileNotFoundException {
		createProducts();
		createUsers();
	}

	/**
	 * Creates the users.
	 * 
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	private void createUsers() throws FileNotFoundException {
		InputStream stream = getClass().getResourceAsStream(USERS_XML_FILE);

		UserParser parser = new UserParser();
		parser.parse(stream);
		List<UserDTO> users = parser.Users();

		for (UserDTO dto : users) {
			User user = dto.toUser();
			User existing = nodeService.findUserByName(user.getLastName());
			if (existing == null) {

				List<String> productNames = dto.getProductNames();
				for (String sku : productNames) {

					Product product = nodeService.findProductByName(sku);
					if (product != null) {
						nodeService.addProduct(user, product);
					}
				}
				nodeService.save(user);
				nodeService.addUserToStore(user);
			}
		}
		for (UserDTO dto : users) {
			User friend1 = nodeService.findUserByName(dto.getLastName());
			List<String> friendNames = dto.getFriendNames();
			for (String friendName : friendNames) {
				User friend2 = nodeService.findUserByName(friendName);
				nodeService.addFriend(friend1, friend2);
			}
		}
	}

	/**
	 * Creates the products.
	 * 
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	private void createProducts() throws FileNotFoundException {
		InputStream stream = getClass().getResourceAsStream(PRODUCTS_XML_FILE);

		ProductParser parser = new ProductParser();
		parser.parse(stream);
		List<ProductDTO> products = parser.getProducts();

		for (ProductDTO dto : products) {
			Product product = dto.toProduct();
			Product existing = nodeService.findProductByName(dto.getSku());
			if (existing == null) {
				nodeService.save(product);
				nodeService.addProductToStore(product);
			}
		}
	}

	private static final String USERS_XML_FILE = "/sample_users.xml";
	private static final String PRODUCTS_XML_FILE = "/sample_products.xml";
	private static final Logger LOG = LoggerFactory
			.getLogger(SystemDataSet.class);
}
