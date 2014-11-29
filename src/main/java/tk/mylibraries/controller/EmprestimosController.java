package tk.mylibraries.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;

import tk.mylibraries.dao.EmprestimoDAO;
import tk.mylibraries.entities.Biblioteca;
import tk.mylibraries.entities.Emprestimo;
import tk.mylibraries.orm.HibernateUtil;

@ManagedBean(value = "emprestimoBean")
public class EmprestimosController {

	Emprestimo emprestimo = new Emprestimo();
	private EmprestimoDAO dao;
	List emprestimos = new ArrayList();

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

	public Biblioteca getBiblioteca() {
		return emprestimo.getBiblioteca();
	}

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
