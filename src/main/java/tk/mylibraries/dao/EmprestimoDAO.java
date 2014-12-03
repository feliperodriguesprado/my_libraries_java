package tk.mylibraries.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import tk.mylibraries.entities.Biblioteca;
import tk.mylibraries.entities.Emprestimo;
import tk.mylibraries.entities.TipoBiblioteca;

public class EmprestimoDAO extends GenericDAO<Emprestimo, Long> {
	public EmprestimoDAO(EntityManager em) {
		super(em);
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