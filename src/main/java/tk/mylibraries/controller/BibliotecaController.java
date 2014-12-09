package tk.mylibraries.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import tk.mylibraries.dao.BibliotecaDAO;
import tk.mylibraries.dao.ClassificacaoBibliotecaDAO;
import tk.mylibraries.dao.TipoBibliotecaDAO;
import tk.mylibraries.dao.UsuarioDAO;
import tk.mylibraries.entities.Biblioteca;
import tk.mylibraries.entities.ClassificacaoBiblioteca;
import tk.mylibraries.entities.TipoBiblioteca;
import tk.mylibraries.entities.Usuario;
import tk.mylibraries.orm.HibernateUtil;
import tk.mylibraries.utils.WebUtils;

@ManagedBean
public class BibliotecaController {

	private Biblioteca biblioteca;
	private BibliotecaDAO bibliotecaDAO;
	private TipoBibliotecaDAO tipoBibliotecaDAO;
	private TipoBiblioteca tipoBibliotecaTO;
	private ClassificacaoBibliotecaDAO classificacaoBibliotecaDAO;
	private ClassificacaoBiblioteca classificacaoBibliotecaTO;

	private String idTipo;
	private Map<String, Long> tipoLibraryMap;

	private String idClassificacao;
	private Map<String, Long> classificacaoLibraryMap;

	public BibliotecaController() {
		tipoBibliotecaDAO = new TipoBibliotecaDAO(
				HibernateUtil.getEntityManager());
		classificacaoBibliotecaDAO = new ClassificacaoBibliotecaDAO(
				HibernateUtil.getEntityManager());
		bibliotecaDAO = new BibliotecaDAO(HibernateUtil.getEntityManager());
		biblioteca = new Biblioteca();
		tipoBibliotecaTO = new TipoBiblioteca();
		classificacaoBibliotecaTO = new ClassificacaoBiblioteca();

	}

	@PostConstruct
	private void init() {
		tipoLibraryMap = new HashMap<String, Long>();
		tipoLibraryMap.put("Livros", 1l);
		tipoLibraryMap.put("Músicas", 2l);
		tipoLibraryMap.put("Vídeos", 3l);

		classificacaoLibraryMap = new HashMap<String, Long>();
		classificacaoLibraryMap.put("Ótimo", 1l);
		classificacaoLibraryMap.put("Satisfatório", 2l);
		classificacaoLibraryMap.put("Regular", 3l);
		classificacaoLibraryMap.put("Péssimo", 4l);
	}

	public void salvar() {
		tipoBibliotecaTO = tipoBibliotecaDAO.getById(Long.parseLong(idTipo));
		classificacaoBibliotecaTO = classificacaoBibliotecaDAO.getById(Long
				.parseLong(idClassificacao));
		biblioteca.setClassificacaoBiblioteca(classificacaoBibliotecaTO);
		biblioteca.setTipoBiblioteca(tipoBibliotecaTO);
		bibliotecaDAO.save(biblioteca);

	}

	public void getTipoBibliotecaByUser() {

		EntityManager em = HibernateUtil.getEntityManager();
		List<Biblioteca> listAll = new ArrayList<Biblioteca>();
		UsuarioDAO usuarioDAO = new UsuarioDAO(em);

		WebUtils webUtils = WebUtils.getInstance();
		long idUsuario = webUtils.getIdUserSession();
		Usuario usuario2 = usuarioDAO.getById(idUsuario);
		listAll = bibliotecaDAO.ListByUser(usuario2);

		// for (Biblioteca biblioteca : listAll) {
		// if (biblioteca != null) {
		// // System.out.println("Tipo : " +
		// // biblioteca.getTipoBiblioteca().getTipoId() + "Nome :"
		// // +biblioteca.getNome());
		// if (biblioteca.getTipoBiblioteca().getTipoId() == 1) {
		// list1.add(biblioteca);
		// } else if (biblioteca.getTipoBiblioteca().getTipoId() == 2) {
		// list2.add(biblioteca);
		// } else if (biblioteca.getTipoBiblioteca().getTipoId() == 3) {
		// list3.add(biblioteca);
		// }
		// }
		//
		// }

	}

	public Map<String, Long> getTipoLibraryMap() {
		return tipoLibraryMap;
	}

	public void setTipoLibraryMap(Map<String, Long> tipoLibraryMap) {
		this.tipoLibraryMap = tipoLibraryMap;
	}

	public String getIdClassificacao() {
		return idClassificacao;
	}

	public void setIdClassificacao(String idClassificacao) {
		this.idClassificacao = idClassificacao;
	}

	public Map<String, Long> getClassificacaoLibraryMap() {
		return classificacaoLibraryMap;
	}

	public void setClassificacaoLibraryMap(
			Map<String, Long> classificacaoLibraryMap) {
		this.classificacaoLibraryMap = classificacaoLibraryMap;
	}

	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	public void atualizar() {
		bibliotecaDAO.update(biblioteca);
	}

	public void deletar() {
		bibliotecaDAO.delete(biblioteca);
	}

	public String getNome() {
		return biblioteca.getNome();
	}

	public void setNome(String nome) {
		biblioteca.setNome(nome);
	}

	public Boolean getDesejado() {
		return biblioteca.isDesejado();
	}

	public void setDesejado(Boolean desejado) {
		biblioteca.setDesejado(desejado);
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public TipoBiblioteca getTipoBibliotecaTO() {
		return tipoBibliotecaTO;
	}

	public void setTipoBibliotecaTO(TipoBiblioteca tipoBibliotecaTO) {
		this.tipoBibliotecaTO = tipoBibliotecaTO;
	}

	public ClassificacaoBiblioteca getClassificacaoBibliotecaTO() {
		return classificacaoBibliotecaTO;
	}

	public void setClassificacaoBibliotecaTO(
			ClassificacaoBiblioteca classificacaoBibliotecaTO) {
		this.classificacaoBibliotecaTO = classificacaoBibliotecaTO;
	}

}