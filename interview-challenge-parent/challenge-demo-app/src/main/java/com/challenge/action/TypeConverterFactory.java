package com.challenge.action;

import java.util.Locale;

import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.integration.spring.SpringHelper;
import net.sourceforge.stripes.validation.DefaultTypeConverterFactory;
import net.sourceforge.stripes.validation.TypeConverter;

import org.springframework.web.context.ContextLoader;

/**
 * 
 * A factory for creating TypeConverter objects.
 * 
 * @author nickk
 * 
 */
public class TypeConverterFactory extends DefaultTypeConverterFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sourceforge.stripes.validation.DefaultTypeConverterFactory#init(net
	 * .sourceforge.stripes.config.Configuration)
	 */
	public void init(final Configuration configuration) {
		super.init(configuration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sourceforge.stripes.validation.DefaultTypeConverterFactory#getInstance
	 * (java.lang.Class, java.util.Locale)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public TypeConverter getInstance(Class<? extends TypeConverter> clazz,
			Locale locale) throws Exception {
		TypeConverter converter = super.getInstance(clazz, locale);
		SpringHelper.injectBeans(converter,
				ContextLoader.getCurrentWebApplicationContext());
		return converter;
	}
}
