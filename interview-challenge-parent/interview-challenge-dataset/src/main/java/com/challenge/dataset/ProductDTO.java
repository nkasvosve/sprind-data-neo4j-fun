package com.challenge.dataset;

import java.math.BigDecimal;

import com.challenge.domain.graph.Product;

/**
*
 * @author nickk
 * 
 * 
 * Used for transferring data from parsed XML to actual domain object
 */
public class ProductDTO {

	private String sku;
	private BigDecimal price;
	private String currency;

	/**
	 * To product.
	 *
	 * @return the product
	 */
	public Product toProduct() {
		Product product = new Product();
		product.setSku(sku);
		product.setPrice(price);
		product.setCurrency(currency);

		return product;
	}

	/**
	 * Gets the sku.
	 *
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * Sets the sku.
	 *
	 * @param sku the new sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Gets the currency.
	 *
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets the currency.
	 *
	 * @param currency the new currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
