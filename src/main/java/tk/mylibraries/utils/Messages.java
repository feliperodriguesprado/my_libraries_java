package tk.mylibraries.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {
	
	private static final Messages INSTANCE = new Messages();
	
	private Messages() {}

	public static Messages getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Metodo que mostra na tela uma mensagem de erro.
	 * @param summary {@link String} - Titulo da mensagem.
	 * @param detail {@link String} - Mensagem do erro.
	 */
	public void setMessageError(String summary, String detail) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/**
	 * Metodo que mostra na tela uma mensagem de informação.
	 * @param summary {@link String} - Titulo da mensagem.
	 * @param detail {@link String} - Mensagem do erro.
	 */
	public void setMessageInfo(String summary, String detail) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
