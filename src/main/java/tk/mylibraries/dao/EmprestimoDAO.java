package tk.mylibraries.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tk.mylibraries.entities.Biblioteca;
import tk.mylibraries.entities.Emprestimo;
import tk.mylibraries.entities.TipoBiblioteca;
import tk.mylibraries.entities.Usuario;

public class EmprestimoDAO extends GenericDAO<Emprestimo, Long> {
	public EmprestimoDAO(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings("unchecked")
	public List<Emprestimo> ListByUser(Usuario usuario) {
		List<Emprestimo> listAll = new ArrayList<Emprestimo>();
		try {
			String query = "from emprestimo e" + " where e.usuarioid = :usuario and e.ativo=true ";
			Query q = em.createQuery(query);
			q.setParameter("usuario", usuario);
			Object result = q.getResultList();
			listAll = (List<Emprestimo>) result;
//			System.out.println("Tamanho do listAll: " + listAll.size());
			return listAll;
		} catch (NoResultException e) {
		}
		return listAll;
	}

	public List<Biblioteca> getBibliotecasPeloTipo(TipoBiblioteca tipoBiblioteca) {
		
		try {
			String query = "from Biblioteca b where b.tipoBiblioteca = :tipoBiblioteca";
			TypedQuery<Biblioteca> typedQuery = em.createQuery(query, Biblioteca.class);
			typedQuery.setParameter("tipoBiblioteca", tipoBiblioteca);
			List<Biblioteca> bibliotecas = typedQuery.getResultList();
			return bibliotecas;
		} catch (NoResultException e) {
			return null;
		}
	}
}