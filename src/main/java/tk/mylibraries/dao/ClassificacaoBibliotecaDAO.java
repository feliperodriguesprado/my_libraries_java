package tk.mylibraries.dao;

import javax.persistence.EntityManager;

import tk.mylibraries.entities.ClassificacaoBiblioteca;

public class ClassificacaoBibliotecaDAO extends GenericDAO<ClassificacaoBiblioteca,Long> {

	public ClassificacaoBibliotecaDAO(EntityManager em) {
		super(em);
	}

}