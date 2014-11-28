package tk.mylibraries.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tk.mylibraries.entities.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Long> {

	public UsuarioDAO(EntityManager em) {
		super(em);
	}

	/**
	 * Método que obtem o objeto do usuário do banco de dados através do email
	 * digitado no login.
	 * 
	 * @param email
	 *            String - email digitado pelo usuário.
	 * @return {@link Usuario} - Objeto referente ao usuário do banco de dados.
	 */
	public Usuario getUsuarioByEmail(String email) {

		try {
			String query = "from Usuario u where u.email = :email";
			TypedQuery<Usuario> typedQuery = em.createQuery(query, Usuario.class);
			typedQuery.setParameter("email", email);
			Usuario usuario = typedQuery.getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Método que faz validação da senha digitada.
	 * 
	 * @param usuario
	 *            {@link Usuario} - Objeto referente ao usuário.
	 * @param password
	 *            {@link String} - Senha digitado pelo usuário.
	 * @return {@link Boolean} - <b>true</b> caso a senha esteja correta e
	 *         <b>false</b> caso a senha esteja incorreta.
	 */
	public Boolean checkPasswordUser(Usuario usuario, String password) {
		
		try {
			String query = "select u.usuarioId from Usuario u where u.usuarioId = :usuarioId and u.senha = md5(:senha)";
			Query q = em.createQuery(query);
			q.setParameter("usuarioId", usuario.getUsuarioId());
			q.setParameter("senha", password);
			Object result = q.getSingleResult();		
			if (Long.parseLong(result.toString()) == usuario.getUsuarioId()) {
				return true;
			} else {
				return false;
			}
		} catch (NoResultException e) {
			return false;
		}
	}

}
