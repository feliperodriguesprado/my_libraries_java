package tk.mylibraries.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tk.mylibraries.dao.UsuarioDAO;
import tk.mylibraries.entities.Usuario;
import tk.mylibraries.orm.HibernateUtil;

@ManagedBean
public class IndexController {
	
	UsuarioDAO usuarioDAO;
	String email;
	String password;
	
	public IndexController() {
		usuarioDAO = new UsuarioDAO(HibernateUtil.getEntityManager());
	}
	
	public void checkLogin() {
		System.out.println("Email: " + email);
		System.out.println("Password: " + password);
		Usuario usuario = usuarioDAO.getUsuarioByEmail(email);
		
		if (usuario != null) {
			if (usuarioDAO.checkPasswordUser(usuario, password)) {
				redirectPage(usuario);
			}			
		}
	}
	
	public void redirectPage(Usuario usuario) {
		
		try {
			
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			context.getExternalContext().getSessionMap().put("user", usuario.getUsuarioId());
			externalContext.redirect("app/main.xhtml");			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
