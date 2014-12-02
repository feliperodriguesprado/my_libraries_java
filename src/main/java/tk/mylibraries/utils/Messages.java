package tk.mylibraries.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {
	
	private static final Messages INSTANCE = new Messages();
	
	private Messages() {}

	public static Messages getInstance() {
		return INSTANCE;
	}
	
	public void setMessageError(String summary, String detail) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
