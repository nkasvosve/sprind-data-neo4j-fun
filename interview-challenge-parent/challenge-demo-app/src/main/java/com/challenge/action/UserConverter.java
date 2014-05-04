package com.challenge.action;

import java.util.Collection;
import java.util.Locale;

import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;

import org.apache.commons.lang.StringUtils;

import com.challenge.domain.graph.Product;
import com.challenge.domain.graph.User;
import com.challenge.service.graph.NodeService;

/**
 * The handles conversion of an id to an actual User object.
 * 
 * @author nickk
 */
public class UserConverter implements TypeConverter<User> {

	private static final String OBJECT_NOT_FOUND = "usernotfound";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sourceforge.stripes.validation.TypeConverter#convert(java.lang.String
	 * , java.lang.Class, java.util.Collection)
	 */
	public User convert(String input, Class<? extends User> targetClass,
			Collection<ValidationError> errors) {

		if (StringUtils.isBlank(input)) {
			errors.add(new LocalizableError(OBJECT_NOT_FOUND));
			return null;
		}
		User obj = (User) nodeService.findOne(Long.valueOf(input), User.class);
		if (obj == null) {
			errors.add(new LocalizableError(OBJECT_NOT_FOUND, input));
			return null;
		}
		nodeService.fetch(obj.getFriends());
		nodeService.fetch(obj.getProducts());

		for (Product p : obj.getProducts()) {
			nodeService.fetch(p);
		}
		for (User u : obj.getFriends()) {
			nodeService.fetch(u);
		}
		return obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sourceforge.stripes.validation.TypeConverter#setLocale(java.util.
	 * Locale)
	 */
	public void setLocale(Locale arg0) {
	}

	@SpringBean
	protected NodeService nodeService;
}
