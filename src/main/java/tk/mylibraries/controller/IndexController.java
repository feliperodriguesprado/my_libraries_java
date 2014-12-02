package tk.mylibraries.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import tk.mylibraries.dao.UsuarioDAO;
import tk.mylibraries.entities.Usuario;
import tk.mylibraries.orm.HibernateUtil;
import tk.mylibraries.utils.WebUtils;

@ManagedBean
public class IndexController {
	
	private UsuarioDAO usuarioDAO;
	private String email;
	private String password;
	
	public IndexController() {
		usuarioDAO = new UsuarioDAO(HibernateUtil.getEntityManager());
	}
	
	public void checkLogin() {
		
		Usuario usuario = usuarioDAO.getUsuarioByEmail(email);
		
		if (usuario != null) {
			if (usuarioDAO.checkPasswordUser(usuario, password)) {
				WebUtils.getInstance().setSession(usuario);
				WebUtils.getInstance().redirectPage("app/emprestimo.xhtml");
			}
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "E-mail ou senha incorretos");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String getHorario() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	    return "Atualizado em " + sdf.format(new Date());
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
}
