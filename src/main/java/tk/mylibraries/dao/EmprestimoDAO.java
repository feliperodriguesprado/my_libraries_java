package tk.mylibraries.dao;


import javax.persistence.EntityManager;

import tk.mylibraries.entities.Emprestimo;

public class EmprestimoDAO extends GenericDAO < Emprestimo , Long >{
	public EmprestimoDAO(EntityManager em) {
		super(em);
	}
 }