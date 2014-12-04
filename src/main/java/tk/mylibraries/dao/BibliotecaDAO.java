package tk.mylibraries.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import tk.mylibraries.entities.Biblioteca;
import tk.mylibraries.entities.Usuario;

public class BibliotecaDAO extends GenericDAO<Biblioteca, Long> {

	public BibliotecaDAO(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	public List<Biblioteca> ListByUser(Usuario usuario2) {
		List<Biblioteca> listAll = new ArrayList<Biblioteca>();
		try {
			String query = "from Biblioteca b" + " where b.usuario = :usuario ";
			Query q = em.createQuery(query);
			q.setParameter("usuario", usuario2);
			Object result = q.getResultList();
			listAll = (List<Biblioteca>) result;
//			System.out.println("Tamanho do listAll: " + listAll.size());
			return listAll;
		} catch (NoResultException e) {
		}
		return listAll;
	}

}
