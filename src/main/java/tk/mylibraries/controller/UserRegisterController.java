package tk.mylibraries.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import tk.mylibraries.dao.UsuarioDAO;
import tk.mylibraries.entities.Usuario;
import tk.mylibraries.orm.HibernateUtil;
import tk.mylibraries.utils.EncryptionMD5;
import tk.mylibraries.utils.Messages;
import tk.mylibraries.utils.WebUtils;

@ManagedBean
public class UserRegisterController {

	private UsuarioDAO usuarioDAO;
	private Usuario usuario;
	private String confirmSenha;
	
	public void saveUser() {
		
		if (usuario.getNome().equals("") 
				|| usuario.getEmail().equals("")
				|| usuario.getSenha().equals("")
				|| confirmSenha.equals("")) {
			Messages.getInstance().setMessageError("Erro", "Informe corretamente todos os campos!");
			return;
		} else if (!usuario.getEmail().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$")) {
			Messages.getInstance().setMessageError("Erro", "Informe um email válido!");
			return;
		}
		
		if (usuario.getSenha().equals(confirmSenha)) {
			usuario.setSenha(EncryptionMD5.getInstance().getPasswordEncryptionMD5(usuario.getSenha()));
			usuarioDAO.save(usuario);
			WebUtils.getInstance().setSession(usuario);
			WebUtils.getInstance().redirectPage("app/main.xhtml");
		} else {
			FacesMessage msg = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Erro:",
					"A senha informada e a confirmação da senha não são iguais");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public UserRegisterController() {
		usuarioDAO = new UsuarioDAO(HibernateUtil.getEntityManager());
		usuario = new Usuario();
	}
	
	public String getName() {
		return usuario.getNome();		
	}
	
	public void setName(String name) {
		usuario.setNome(name.trim());
	}
	
	public String getEmail() {
		return usuario.getEmail();
	}
	
	public void setEmail(String email) {
		usuario.setEmail(email.trim());
	}
	
	public String getSenha() {
		return usuario.getSenha();
	}
	
	public void setSenha(String senha) {
		usuario.setSenha(senha.trim());
	}

	public String getConfirmSenha() {
		return confirmSenha;
	}

	public void setConfirmSenha(String confirmSenha) {
		this.confirmSenha = confirmSenha.trim();
	}
	
}
