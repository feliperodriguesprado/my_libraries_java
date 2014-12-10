package tk.mylibraries.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import tk.mylibraries.dao.ClassificacaoBibliotecaDAO;
import tk.mylibraries.dao.TipoBibliotecaDAO;
import tk.mylibraries.entities.ClassificacaoBiblioteca;
import tk.mylibraries.entities.TipoBiblioteca;
import tk.mylibraries.orm.HibernateUtil;

public class Runner {

	public static void main(String[] args) {

		EntityManager entityManager = HibernateUtil.getEntityManager();
		System.out.println(entityManager.toString());	
		
		ClassificacaoBibliotecaController bibliotecaController = new ClassificacaoBibliotecaController();
		TipoBibliotecaController bibliotecaController2 = new TipoBibliotecaController();
		
		BibliotecaController bb = new BibliotecaController();
		List<ClassificacaoBiblioteca> list = new ArrayList<ClassificacaoBiblioteca>();
		ClassificacaoBibliotecaDAO classificacaoBibliotecaDAO = new ClassificacaoBibliotecaDAO(HibernateUtil.getEntityManager());
		list = classificacaoBibliotecaDAO.getAll();
		
		ClassificacaoBiblioteca classificacaoBiblioteca = new ClassificacaoBiblioteca();
		classificacaoBiblioteca = list.get(1);
		
		List<TipoBiblioteca> list2 = new ArrayList<TipoBiblioteca>();
		TipoBiblioteca tipoBiblioteca = new TipoBiblioteca();
		TipoBibliotecaDAO tipoBibliotecaDAO = new TipoBibliotecaDAO(HibernateUtil.getEntityManager());
		list2 = tipoBibliotecaDAO.getAll();
		tipoBiblioteca = list2.get(1);
		
//		bb.setClassificacaoBiblioteca(classificacaoBiblioteca);
//		bb.setTipoBiblioteca(tipoBiblioteca);
		bb.setNome("Corrida Mortal");
		bb.setDesejado(true);
		
		bb.salvar();
		
	}
}
