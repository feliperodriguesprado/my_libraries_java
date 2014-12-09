package tk.mylibraries.utils;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import tk.mylibraries.entities.Usuario;

public class WebUtils {

	private static final WebUtils INSTANCE = new WebUtils();
	
	private WebUtils() {
	}
	
	public static WebUtils getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Metodo que obtem o Faces Context utilizado para obter a sessao do
	 * browser.
	 * 
	 * @return {@link FacesContext}
	 */
	public FacesContext getFacesContext() {  
        return FacesContext.getCurrentInstance();  
    }
	
	public RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}
	
	/**
	 * Metodo que obtem a sessao do browser.
	 * @return {@link HttpSession}
	 */
	public HttpSession getSession() {
		return (HttpSession) getFacesContext().getExternalContext().getSession(false);
	}
	
	/**
	 * Metodo obtem o ID do usuario na sessao do browser.
	 * 
	 * @return {@link Long}
	 */
	public Long getIdUserSession() {
		return Long.parseLong(getSession().getAttribute("user").toString());
	}
	
	
	/**
	 * Metodo que grava o ID do uauario na sessao do browser.
	 * 
	 * @param usuario
	 *            {@link Usuario} - objeto com o usuario que sera gravado na
	 *            sessao do browser.
	 */
	public void setSession(Usuario usuario) {
		getFacesContext().getExternalContext().getSessionMap().put("user", usuario.getUsuarioId());
		//getSession().setAttribute("user", usuario.getUsuarioId());
	}
	
	/**
	 * Metodo que remove o usuario da sessao.
	 */
	public void invalidateSession() {
		getSession().invalidate();
	}
	
	/**
	 * Metodo que redireciona para a pagina informada no parametro de entrada.
	 * 
	 * @param page
	 *            {@link String} - pagina a ser redirecinada.
	 */
	public void redirectPage(String page) {
		
		try {
			getFacesContext().getExternalContext().redirect(page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
