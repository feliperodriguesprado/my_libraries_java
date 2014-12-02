package tk.mylibraries.controller;

import javax.faces.bean.ManagedBean;

import tk.mylibraries.dao.UsuarioDAO;
import tk.mylibraries.entities.Usuario;
import tk.mylibraries.orm.HibernateUtil;
import tk.mylibraries.utils.WebUtils;

@ManagedBean
public class UserController {

	private UsuarioDAO usuarioDAO;
	private Usuario usuario;
	
	public UserController() {
		usuarioDAO = new UsuarioDAO(HibernateUtil.getEntityManager());
	}

	public void logout() {
		WebUtils.getInstance().invalidateSession();
		WebUtils.getInstance().redirectPage("../index.xhtml");
	}
	
	public String getCurrentUser() {
		Long userId = WebUtils.getInstance().getIdUserSession();
		usuario = usuarioDAO.getById(userId);
		return usuario.getNome();
	}
	
}
