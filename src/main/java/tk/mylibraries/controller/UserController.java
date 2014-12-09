package tk.mylibraries.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.RollbackException;

import tk.mylibraries.dao.UsuarioDAO;
import tk.mylibraries.entities.Usuario;
import tk.mylibraries.orm.HibernateUtil;
import tk.mylibraries.utils.EncryptionMD5;
import tk.mylibraries.utils.Messages;
import tk.mylibraries.utils.WebUtils;

@ManagedBean
public class UserController {

	private UsuarioDAO usuarioDAO;
	private Usuario usuario;
	private String passwordCurrent;
	private String passwordNew;
	private String passwordConfirm;
	
	public UserController() {
		usuarioDAO = new UsuarioDAO(HibernateUtil.getEntityManager());
	}
	
	/**
	 * Metodo que popula o combo box do tipo de biblioteca
	 */
	@PostConstruct
	private void init() {
		usuario = usuarioDAO.getById(WebUtils.getInstance().getIdUserSession());
	}

	public String getCurrentUser() {
		return usuario.getNome();
	}

	public void logout() {
		WebUtils.getInstance().invalidateSession();
		WebUtils.getInstance().redirectPage("../index.xhtml");
	}
	
	public void updateUser() {
		
		if (usuario.getNome().equals("") 
				|| usuario.getEmail().equals("")
				|| passwordCurrent.equals("")
				|| passwordNew.equals("")
				|| passwordConfirm.equals("")) {
			Messages.getInstance().setMessageErrorDialog("Erro", "Informe corretamente todos os campos!");
			return;
		} else if (!usuario.getEmail().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$")) {
			Messages.getInstance().setMessageErrorDialog("Erro", "Informe um email válido!");
			return;
		}
		
		if (usuario.getSenha().equals(EncryptionMD5.getInstance().getPasswordEncryptionMD5(passwordCurrent))) {
			if (passwordNew.equals(passwordConfirm)) {
				usuario.setSenha(EncryptionMD5.getInstance().getPasswordEncryptionMD5(passwordNew));
				
				try {
					usuarioDAO.update(usuario);
					Messages.getInstance().setMessageInfoDialog("Informação", "Conta atualizada com sucesso!");
					WebUtils.getInstance().getRequestContext().execute("PF('account').hide();");					
				} catch (RollbackException e) {
					Messages.getInstance().setMessageErrorDialog("Erro", "E-mail informado já existente para outra conta!");
					WebUtils.getInstance().getRequestContext().execute("PF('account').hide();");				
				}
				
				// TODO: atualizar pagina.
			} else {
				Messages.getInstance().setMessageErrorDialog("Erro", "A senha informada e a confirmação da senha não são iguais!");
			}
		} else {
			Messages.getInstance().setMessageErrorDialog("Erro", "Senha atual informada incorreta!");
		}
		
	}
	
	public void inactivateUser() {
		
		usuario = usuarioDAO.getById(WebUtils.getInstance().getIdUserSession());
		usuario.setAtivo(false);
		usuarioDAO.update(usuario);
		WebUtils.getInstance().redirectPage("../index.xhtml");
		
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

	public String getPasswordCurrent() {
		return passwordCurrent;
	}

	public void setPasswordCurrent(String passwordCurrent) {
		this.passwordCurrent = passwordCurrent;
	}
	
	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	public void setPasswordConfirm(String confirmSenha) {
		this.passwordConfirm = confirmSenha.trim();
	}

}
