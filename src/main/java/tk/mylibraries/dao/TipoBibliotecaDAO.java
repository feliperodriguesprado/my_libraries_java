package tk.mylibraries.dao;

import javax.persistence.EntityManager;

import tk.mylibraries.entities.TipoBiblioteca;

public class TipoBibliotecaDAO extends GenericDAO<TipoBiblioteca, Long> {

	public TipoBibliotecaDAO(EntityManager em) {
		super(em);
	}

}
