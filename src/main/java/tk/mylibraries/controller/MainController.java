package tk.mylibraries.controller;

import javax.faces.bean.ManagedBean;

import tk.mylibraries.utils.WebUtils;

@ManagedBean
public class MainController {
	
	public Long getCurrentUser() {
		return WebUtils.getInstance().getIdUserSession();
	}
	
	public void logout() {
		System.out.println(WebUtils.getInstance().getIdUserSession());
		WebUtils.getInstance().invalidateSession();
		WebUtils.getInstance().redirectPage("../index.xhtml");
	}
	
}
