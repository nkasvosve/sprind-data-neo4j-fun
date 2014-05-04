package com.challenge.dataset;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Parses an XML file and produces a collection of products.
 * 
 *  @author nickk
 */
public class ProductParser extends DefaultHandler {

	private List<ProductDTO> products;

	/**
	 * Instantiates a new product parser.
	 */
	public ProductParser() {
	}

	/**
	 * Gets the products.
	 * 
	 * @return the products
	 */
	public List<ProductDTO> getProducts() {
		return products;
	}

	/**
	 * Parses the stream.
	 * 
	 * @param stream
	 *            the stream
	 */
	public void parse(InputStream stream) {
		if (stream == null) {
			throw new IllegalArgumentException(
					"Null input stream found for parsing products");
		}
		products = new ArrayList<ProductDTO>();
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser sp = spf.newSAXParser();

			sp.parse(stream, this);

		} catch (ParserConfigurationException e) {
			log.error(
					"ParserConfigurationException - Error parsing document : ",
					e);
		} catch (SAXException e) {
			log.error("SAXException - Error parsing document : ", e);
		} catch (IOException e) {
			log.error("IOException - Error parsing document : " + e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String nsURI, String strippedName, String tagName,
			Attributes attributes) throws SAXException {

		if (tagName.equalsIgnoreCase(PRODUCT)) {
			ProductDTO product = new ProductDTO();
			String sku = attributes.getValue(NAME);
			String price = attributes.getValue(PRICE);
			String currency = attributes.getValue(CURRENCY);
			product.setSku(sku);
			product.setPrice(new BigDecimal(price));
			product.setCurrency(currency);
			products.add(product);
		}
	}

	private static final String NAME = "name";
	private static final String PRICE = "price";
	private static final String PRODUCT = "product";
	private static final String CURRENCY = "currency";
	private static final Logger log = LoggerFactory
			.getLogger(ProductParser.class);

}
