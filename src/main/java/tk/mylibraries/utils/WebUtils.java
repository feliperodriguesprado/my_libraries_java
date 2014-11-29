package tk.mylibraries.utils;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import tk.mylibraries.entities.Usuario;

public class WebUtils {

	private static final WebUtils INSTANCE = new WebUtils();
	
	private WebUtils() {
	}
	
	public static WebUtils getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Metodo que redireciona para a pagina informada no parametro de entrada.
	 * 
	 * @param page
	 *            {@link String} - pagina a ser redirecinada.
	 */
	public void redirectPage(String page) {
		
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo obtem o ID do usuario na sessao do browser.
	 * 
	 * @return {@link Long}
	 */
	public Long getIdUserSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		Long userId = (Long) session.getAttribute("user");
		return userId;
	}
	
	/**
	 * Metodo que grava o ID do uauario na sessao do browser.
	 * 
	 * @param usuario
	 *            {@link Usuario} - objeto com o usuario que sera gravado na
	 *            sessao do browser.
	 */
	public void setSession(Usuario usuario) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("user", usuario.getUsuarioId());
	}
	
}
