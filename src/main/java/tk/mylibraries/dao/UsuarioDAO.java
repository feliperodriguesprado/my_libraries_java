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

	public void checkLogin(String email, String password) {

		Usuario usuario = getUsuarioByEmail(email);
		
		if (usuario != null) {
			if (checkPasswordUser(usuario, password)) {
				System.out.println("User ok.");
			}			
		}
	}

	public Usuario getUsuarioByEmail(String email) {

		String query = "from Usuario u where u.email = :email";
		TypedQuery<Usuario> typedQuery = em.createQuery(query, Usuario.class);
		typedQuery.setParameter("email", email);
		Usuario usuario = typedQuery.getSingleResult();
		return usuario;

	}
	
	/**
	 * Método que faz select no usuário.
	 * @param usuario
	 * @param password
	 * @return
	 */
	public boolean checkPasswordUser(Usuario usuario, String password) {
		
		try {
			String query = "select u.usuarioId from Usuario u where u.usuarioId = :usuarioId and u.senha = md5(:senha)";
			Query q = em.createQuery(query);
			q.setParameter("usuarioId", usuario.getUsuarioId());
			q.setParameter("senha", password);
			Object result = q.getSingleResult();
			System.out.println(result);			
			if (Long.parseLong(result.toString()) == usuario.getUsuarioId()) {
				return true;
			}
		} catch (NoResultException e) {
			System.out.println("erro");
		}
		return false;
	}

}
