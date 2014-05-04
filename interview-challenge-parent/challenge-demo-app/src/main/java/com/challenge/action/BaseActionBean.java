package com.challenge.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.log4j.Logger;

import com.challenge.service.graph.NodeService;

/**
 * Base class for actions classes
 * 
 * @author nickk
 */
public abstract class BaseActionBean implements ActionBean {

	protected String target;

	protected Logger log = Logger.getLogger(BaseActionBean.class);

	protected ChallengeActionBeanContext context;

	/**
	 * Gets the target.
	 * 
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * Sets the target.
	 * 
	 * @param target
	 *            the new target
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sourceforge.stripes.action.ActionBean#getContext()
	 */
	public ActionBeanContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sourceforge.stripes.action.ActionBean#setContext(net.sourceforge.
	 * stripes.action.ActionBeanContext)
	 */
	public void setContext(ActionBeanContext context) {
		this.context = (ChallengeActionBeanContext) context;
	}

	@SpringBean
	protected NodeService nodeService;
}
