package com.challenge.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Transforms an XML object into String representation ,and vice versa
 * 
 * @author nickk
 */
public enum JaxBUtil {

	INSTANCE;

	private static final Map<Class<?>, Unmarshaller> unMarshallers = new HashMap<Class<?>, Unmarshaller>();

	private static final Map<Class<?>, Marshaller> marshallers = new HashMap<Class<?>, Marshaller>();

	/**
	 * Gets the unmarshaller.
	 * 
	 * @param jaxbContext
	 *            the jaxb context
	 * @return the unmarshaller
	 */
	private Unmarshaller getUnmarshaller(final Class<?> jaxbContext) {

		Unmarshaller unmarshaller = unMarshallers.get(jaxbContext);

		if (unmarshaller != null)
			return unmarshaller;

		unmarshaller = newUnmarshallerInstance(jaxbContext);
		unMarshallers.put(jaxbContext, unmarshaller);
		return unmarshaller;
	}

	/**
	 * Gets the marshaller.
	 * 
	 * @param jaxbContext
	 *            the jaxb context
	 * @return the marshaller
	 */
	private Marshaller getMarshaller(final Class<?> jaxbContext) {

		Marshaller marshaller = marshallers.get(jaxbContext);

		if (marshaller != null)
			return marshaller;

		marshaller = newMarshallerInstance(jaxbContext);
		marshallers.put(jaxbContext, marshaller);
		return marshaller;
	}

	/**
	 * New unmarshaller instance.
	 * 
	 * @param jaxbContext
	 *            the jaxb context
	 * @return the unmarshaller
	 */
	private Unmarshaller newUnmarshallerInstance(final Class<?> jaxbContext) {
		try {
			final JAXBContext context = JAXBContext.newInstance(jaxbContext);
			final Unmarshaller unmarshaller = context.createUnmarshaller();
			return unmarshaller;
		} catch (JAXBException e) {
			throw new IllegalStateException(
					String.format(
							"Exception occured creating JAXB unmarshaller for context= %s",
							jaxbContext), e);
		}
	}

	/**
	 * New marshaller instance.
	 * 
	 * @param jaxbContext
	 *            the jaxb context
	 * @return the marshaller
	 */
	private Marshaller newMarshallerInstance(final Class<?> jaxbContext) {
		try {
			final JAXBContext context = JAXBContext.newInstance(jaxbContext);
			final Marshaller marshaller = context.createMarshaller();
			return marshaller;
		} catch (JAXBException e) {
			throw new IllegalStateException(
					String.format(
							"Exception occured creating JAXB marshaller for context= %s",
							jaxbContext), e);
		}
	}

	/**
	 * Unmarshall.
	 * 
	 * @param clazz
	 *            the clazz
	 * @param xmlString
	 *            the xml string
	 * @return the object
	 * @throws Exception
	 *             the exception
	 */
	public synchronized Object unmarshall(Class<?> clazz, final String xmlString)
			throws Exception {
		final StringReader sr = new StringReader(xmlString.trim());
		final InputSource is = new InputSource(sr);
		Unmarshaller unmarshaller = getUnmarshaller(clazz);
		return unmarshaller.unmarshal(is);
	}

	/**
	 * Creates the document.
	 * 
	 * @return the document
	 */
	public Document createDocument() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder;
		try {
			docFactory.setNamespaceAware(true);
			docBuilder = docFactory.newDocumentBuilder();
			return docBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException(String.format(
					"Exception occured creating document: %s", e));
		}
	}

	/**
	 * Marshall.
	 * 
	 * @param object
	 *            the object
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public synchronized String marshall(Object object) throws Exception {
		Marshaller marshaller = getMarshaller(object.getClass());
		StringWriter writer = new StringWriter();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(object, writer);
		return writer.toString();
	}

	/**
	 * Marshall.
	 * 
	 * @param object
	 *            the object
	 * @param props
	 *            the props
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public synchronized String marshall(Object object, Map<String, Object> props)
			throws Exception {
		Marshaller marshaller = getMarshaller(object.getClass());
		for (String key : props.keySet()) {
			marshaller.setProperty(key, props.get(key));
		}
		StringWriter writer = new StringWriter();
		marshaller.marshal(object, writer);
		return writer.toString();
	}

	/**
	 * From xml.
	 * 
	 * @param clazz
	 *            the clazz
	 * @param xmlString
	 *            the xml string
	 * @return the object
	 * @throws Exception
	 *             the exception
	 */
	public Object fromXML(Class<?> clazz, final String xmlString)
			throws Exception {
		return unmarshall(clazz, xmlString);
	}

	/**
	 * To xml.
	 * 
	 * @param object
	 *            the object
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String toXML(Object object) throws Exception {
		return marshall(object);
	}

	/**
	 * To xml.
	 * 
	 * @param object
	 *            the object
	 * @param props
	 *            the props
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String toXML(Object object, Map<String, Object> props)
			throws Exception {
		return marshall(object, props);
	}
}
