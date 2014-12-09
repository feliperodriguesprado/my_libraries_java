package tk.mylibraries.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import tk.mylibraries.dao.UsuarioDAO;
import tk.mylibraries.entities.Usuario;
import tk.mylibraries.orm.HibernateUtil;
import tk.mylibraries.utils.Messages;
import tk.mylibraries.utils.WebUtils;

@ManagedBean
public class IndexController {
	
	private UsuarioDAO usuarioDAO;
	Usuario usuario;
	private String email;
	private String password;
	
	public IndexController() {
		usuarioDAO = new UsuarioDAO(HibernateUtil.getEntityManager());
		usuario = new Usuario();
	}
	
	public void checkLogin(ActionEvent actionEvent) {

		if (email.equals("") || password.equals("")) {
			Messages.getInstance().setMessageError("Erro", "Informe corretamente todos os campos!");
			return;
		} else if (!email.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$")) {
			Messages.getInstance().setMessageError("Erro", "Informe um email válido!");
			return;
		}
	    
		usuario = usuarioDAO.getUsuarioByEmail(email);
		
		if (usuario != null) {
			
			if (usuarioDAO.checkPasswordUser(usuario, password)) {
				
				if (!usuario.isAtivo()) {
					WebUtils.getInstance().getRequestContext().execute("PF('activeAccount').show();");
					return;
				}
				
				WebUtils.getInstance().setSession(usuario);
				WebUtils.getInstance().redirectPage("app/main.xhtml");
			}
		}
		Messages.getInstance().setMessageError("Erro", "E-mail ou senha incorretos!");
	}
	
	public void activeAccount() {
		WebUtils.getInstance().redirectPage("userRegister.xhtml");
	}
	
	public void setEmail(String email) {
		this.email = email.trim();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password.trim();
	}
	
	public String getPassword() {
		return password;
	}
	
}
