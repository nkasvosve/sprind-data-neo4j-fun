package com.challenge.action;

import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;

import com.challenge.domain.graph.User;

/**
 * The handles the presentation of users on a web page.
 * 
 * @author nickk
 */
@UrlBinding("/challenge/users/{_eventName}/{user}")
public class UserActionBean extends BaseActionBean {

	private Collection<User> users;

	@Validate(required = true, on = DETAILS, converter = UserConverter.class)
	private User user;

	/**
	 * View.
	 * 
	 * @return the resolution
	 * @throws Exception
	 *             the exception
	 */
	@DefaultHandler
	public Resolution view() throws Exception {
		users = nodeService.getAllUsers();
		return new ForwardResolution(USERS_JSP);
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
		return new ForwardResolution(USER_DETAILS_JSP);
	}

	/**
	 * Gets the users.
	 * 
	 * @return the users
	 */
	public Collection<User> getUsers() {
		return users;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	private static final String DETAILS = "details";

	private static final String USERS_JSP = "/WEB-INF/jsp/users.jsp";

	private static final String USER_DETAILS_JSP = "/WEB-INF/jsp/user-details.jsp";
}
