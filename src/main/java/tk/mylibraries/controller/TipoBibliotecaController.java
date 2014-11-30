package tk.mylibraries.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import tk.mylibraries.dao.TipoBibliotecaDAO;
import tk.mylibraries.entities.TipoBiblioteca;
import tk.mylibraries.orm.HibernateUtil;

@ManagedBean
public class TipoBibliotecaController {

	private TipoBiblioteca biblioteca;
	private TipoBibliotecaDAO tipoBibliotecaDAO;
	private String nome;

	public TipoBibliotecaController() {
		tipoBibliotecaDAO = new TipoBibliotecaDAO(
				HibernateUtil.getEntityManager());
		biblioteca = new TipoBiblioteca();
		// arrumar daqui pra frente - para fazer CRUD completo
		if (tipoBibliotecaDAO.getAll().size() < 3) {

			List<TipoBiblioteca> tipos = new ArrayList<TipoBiblioteca>();
			biblioteca.setNome("Vídeos");
			tipos.add(biblioteca);
			biblioteca = new TipoBiblioteca();
			biblioteca.setNome("Músicas");
			tipos.add(biblioteca);
			biblioteca = new TipoBiblioteca();
			biblioteca.setNome("Animes");
			tipos.add(biblioteca);
			for (TipoBiblioteca tipoBiblioteca : tipos) {
				tipoBibliotecaDAO.save(tipoBiblioteca);
			}
		}
	}

	public void salvar() {
		tipoBibliotecaDAO.save(biblioteca);
	}

	public void atualizar() {
		tipoBibliotecaDAO.update(biblioteca);
	}

	public void deletar() {
		tipoBibliotecaDAO.delete(biblioteca);
	}

	public List<TipoBiblioteca> getAll() {
		return tipoBibliotecaDAO.getAll();
	}

	public String getNome() {
		return biblioteca.getNome();
	}

	public void setNome(String nome) {
		biblioteca.setNome(nome);
	}

}
