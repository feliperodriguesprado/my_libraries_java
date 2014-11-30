package tk.mylibraries.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import tk.mylibraries.dao.BibliotecaDAO;
import tk.mylibraries.dao.EmprestimoDAO;
import tk.mylibraries.dao.TipoBibliotecaDAO;
import tk.mylibraries.entities.Biblioteca;
import tk.mylibraries.entities.Emprestimo;
import tk.mylibraries.entities.TipoBiblioteca;
import tk.mylibraries.orm.HibernateUtil;

/**
 * @author Diego
 *
 */
@ManagedBean
@ViewScoped
public class EmprestimosController {
	Emprestimo emprestimo = new Emprestimo();
	Biblioteca biblioteca = new Biblioteca();
	TipoBiblioteca tipo = new TipoBiblioteca();
	private EmprestimoDAO dao;
	private List emprestimos;
	private List<Biblioteca> library;

	public List<Biblioteca> getLibrary() {
		return library;
	}

	public void setLibrary(List<Biblioteca> library) {
		this.library = library;
	}

	public TipoBiblioteca getTipo() {
		return tipo;
	}

	public void setTipo(TipoBiblioteca tipo) {
		this.tipo = tipo;
	}

	// insere as bibliotecas cadastras no combobox de bibliotecas
	public List<SelectItem> getTipos() {
		System.out.println("Chegou nessa bosta");
		List<TipoBiblioteca> tipos = new TipoBibliotecaDAO(
				HibernateUtil.getEntityManager()).getAll();
		List<SelectItem> lib = new ArrayList<SelectItem>(tipos.size());
		for (TipoBiblioteca p : tipos) {
			System.out.println(p.getNome());
			lib.add(new SelectItem(p.getTipoId(), p.getNome()));
		}
		return lib;
	}

	public void getBibliotecas() {
		if (tipo != null && !tipo.equals("")) {
			List<Biblioteca> library = new EmprestimoDAO(
					HibernateUtil.getEntityManager())
					.getBibliotecasPeloTipo(tipo.getTipoId());
		}
		// System.out.println("Chegou nessa bosta");
		// List<Biblioteca> bibliotecas = new BibliotecaDAO(
		// HibernateUtil.getEntityManager()).getAll();
		// List<SelectItem> bib = new ArrayList<SelectItem>(bibliotecas.size());
		// for (Biblioteca p : bibliotecas) {
		// System.out.println(p.getNome());
		// bib.add(new SelectItem(p.getBibliotecaId(), p.getNome()));
		// }
		// return bib;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public EmprestimosController() {
		emprestimos = new EmprestimoDAO(HibernateUtil.getEntityManager())
				.getAll();
		emprestimo = new Emprestimo();
	}

	public void cadastrar() {
		new EmprestimoDAO(HibernateUtil.getEntityManager()).save(emprestimo);
		emprestimos = new EmprestimoDAO(HibernateUtil.getEntityManager())
				.getAll();
		emprestimo = new Emprestimo();
	}

	public void alterar() {
		new EmprestimoDAO(HibernateUtil.getEntityManager()).update(emprestimo);
		emprestimos = new EmprestimoDAO(HibernateUtil.getEntityManager())
				.getAll();
		emprestimo = new Emprestimo();
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		emprestimo.setBiblioteca(biblioteca);
	}

	// public Biblioteca getBiblioteca() {
	// bibliotecas = new
	// BibliotecaDAO(HibernateUtil.getEntityManager()).getAll();
	// return emprestimo.getBiblioteca();
	// }
	public void setDataEmprestimo(Date data_emprestimo) {
		emprestimo.setDataEmprestimo(data_emprestimo);
	}

	public Date getDataEmprestimo() {
		return emprestimo.getDataEmprestimo();
	}

	public void setDataEncerramento(Date data_encerramento) {
		emprestimo.setDataEncerramento(data_encerramento);
	}

	public Date getDataEncerramento() {
		return emprestimo.getDataEncerramento();
	}

	public void setDestinatario(String destinatario) {
		emprestimo.setDestinatario(destinatario);
	}

	public String getDestinatario() {
		return emprestimo.getDestinatario();
	}

	public void setObservacao(String observacao) {
		emprestimo.setObservacao(observacao);
	}

	public String getObservacao() {
		return emprestimo.getObservacao();
	}

	public void setAtivo(Boolean ativo) {
		emprestimo.setAtivo(ativo);
	}

	public Boolean getAtivo() {
		return emprestimo.getAtivo();
	}
}