package com.luizjacomn.hib_envers.sec;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionBean {

	private SessionBean() {}
	
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}
}