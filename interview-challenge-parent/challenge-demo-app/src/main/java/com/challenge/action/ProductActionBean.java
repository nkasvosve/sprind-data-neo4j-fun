package com.challenge.action;

import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;

import com.challenge.domain.graph.Product;

/**
 * The handles the presentation of products on a web page.
 * 
 * @author nickk
 */

@UrlBinding("/challenge/products/{_eventName}/{product}")
public class ProductActionBean extends BaseActionBean {

	private Collection<Product> products;

	/** The product. */
	@Validate(required = true, on = DETAILS, converter = ProductConverter.class)
	private Product product;

	/**
	 * View.
	 * 
	 * @return the resolution
	 * @throws Exception
	 *             the exception
	 */
	@DefaultHandler
	public Resolution view() throws Exception {
		products = nodeService.getAllProducts();
		return new ForwardResolution(PRODUCTS_JSP);
	}

	/**
	 * Details.
	 * 
	 * @return the resolution
	 * @throws Exception
	 *             the exception
	 */
	@HandlesEvent(DETAILS)
	public Resolution details() throws Exception {
		return new ForwardResolution(PRODUCT_DETAILS_JSP);
	}

	/**
	 * Gets the products.
	 * 
	 * @return the products
	 */
	public Collection<Product> getProducts() {
		return products;
	}

	/**
	 * Gets the product.
	 * 
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 * 
	 * @param product
	 *            the new product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	private static final String PRODUCTS_JSP = "/WEB-INF/jsp/products.jsp";

	private static final String DETAILS = "details";

	private static final String PRODUCT_DETAILS_JSP = "/WEB-INF/jsp/product-details.jsp";

}
