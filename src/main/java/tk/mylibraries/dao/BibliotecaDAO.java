package tk.mylibraries.dao;

import javax.persistence.EntityManager;

import tk.mylibraries.entities.Biblioteca;

public class BibliotecaDAO extends GenericDAO<Biblioteca, Long> {

	public BibliotecaDAO(EntityManager em) {
		super(em);
	}

}
