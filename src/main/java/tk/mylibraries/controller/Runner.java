package tk.mylibraries.controller;

import javax.persistence.EntityManager;

import tk.mylibraries.orm.HibernateUtil;

public class Runner {

	public static void main(String[] args) {

		EntityManager entityManager = HibernateUtil.getEntityManager();
		System.out.println(entityManager.toString());
		
	}

}
