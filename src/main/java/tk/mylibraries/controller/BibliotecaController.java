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
	private UsuarioDAO usuarioDAO;
	private TipoBiblioteca tipoBibliotecaTO;
	private Usuario usuarioTO;
	private ClassificacaoBibliotecaDAO classificacaoBibliotecaDAO;
	private ClassificacaoBiblioteca classificacaoBibliotecaTO;
	private List<Biblioteca> list1, list2, list3;
	private Biblioteca biblioteca1, biblioteca2, biblioteca3;
	private EntityManager entityManager;
	
	private String aux;
	
	private long idBiblioteca = 0l;

	private String idTipo;
	private Map<String, Long> tipoLibraryMap;

	private String idClassificacao;
	private Map<String, Long> classificacaoLibraryMap;

	public BibliotecaController() {
		entityManager = HibernateUtil.getEntityManager();
		tipoBibliotecaDAO = new TipoBibliotecaDAO(entityManager);
		classificacaoBibliotecaDAO = new ClassificacaoBibliotecaDAO(
				entityManager);
		bibliotecaDAO = new BibliotecaDAO(entityManager);
		usuarioDAO = new UsuarioDAO(entityManager);

		biblioteca = new Biblioteca();
		tipoBibliotecaTO = new TipoBiblioteca();
		classificacaoBibliotecaTO = new ClassificacaoBiblioteca();
		usuarioTO = new Usuario();

	}

	@PostConstruct
	private void init() {
		tipoLibraryMap = new HashMap<String, Long>();
		tipoLibraryMap.put("Livros", 1l);
		tipoLibraryMap.put("Musicas", 2l);
		tipoLibraryMap.put("Videos", 3l);

		classificacaoLibraryMap = new HashMap<String, Long>();
		classificacaoLibraryMap.put("Otimo", 1l);
		classificacaoLibraryMap.put("Satisfatorio", 2l);
		classificacaoLibraryMap.put("Regular", 3l);
		classificacaoLibraryMap.put("Pessimo", 4l);

		list1 = new ArrayList<Biblioteca>();
		list2 = new ArrayList<Biblioteca>();
		list3 = new ArrayList<Biblioteca>();

		biblioteca1 = new Biblioteca();
		biblioteca2 = new Biblioteca();
		biblioteca3 = new Biblioteca();

		biblioteca1.setNome("Livros");
		biblioteca2.setNome("Musicas");
		biblioteca3.setNome("Videos");
		getTipoBibliotecaByUser();

	}

	public void salvar() {
		tipoBibliotecaTO = tipoBibliotecaDAO.getById(Long.parseLong(idTipo));
		classificacaoBibliotecaTO = classificacaoBibliotecaDAO.getById(Long
				.parseLong(idClassificacao));
		biblioteca.setClassificacaoBiblioteca(classificacaoBibliotecaTO);
		biblioteca.setTipoBiblioteca(tipoBibliotecaTO);
		usuarioTO = usuarioDAO.getById(WebUtils.getInstance()
				.getIdUserSession());
		biblioteca.setUsuario(usuarioTO);
		bibliotecaDAO.save(biblioteca);
		biblioteca = new Biblioteca();
		
		WebUtils.getInstance().redirectPage("biblioteca.xhtml");

	}

	public void getTipoBibliotecaByUser() {

		List<Biblioteca> listAll = new ArrayList<Biblioteca>();

		Usuario usuario2 = usuarioDAO.getById(WebUtils.getInstance()
				.getIdUserSession());
		listAll = bibliotecaDAO.ListByUser(usuario2);

		for (Biblioteca biblioteca : listAll) {
			if (biblioteca != null) {

				if (biblioteca.getTipoBiblioteca().getTipoId() == 1) {
					list1.add(biblioteca);
				} else if (biblioteca.getTipoBiblioteca().getTipoId() == 2) {
					list2.add(biblioteca);
				} else if (biblioteca.getTipoBiblioteca().getTipoId() == 3) {
					list3.add(biblioteca);
				}
			}

		}

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
		
		usuarioTO = usuarioDAO.getById(WebUtils.getInstance()
				.getIdUserSession());

		biblioteca.setUsuario(usuarioTO);
		
		List<Biblioteca> bibliotecas = new ArrayList<Biblioteca>();
		bibliotecas = bibliotecaDAO.ListByUser(usuarioTO);
		
		long idBiblioteca = 0l;
		
		for (Biblioteca biblioteca2 : bibliotecas) {
			if(biblioteca2.getNome() == biblioteca.getNome()){
				idBiblioteca = biblioteca2.getBibliotecaId();
				break;
			}
		}
		
		biblioteca.setBibliotecaId(idBiblioteca);
		biblioteca.setNome(aux);

		tipoBibliotecaTO = tipoBibliotecaDAO.getById(Long.parseLong(idTipo));
		biblioteca.setTipoBiblioteca(tipoBibliotecaTO);

		classificacaoBibliotecaTO = classificacaoBibliotecaDAO.getById(Long
				.parseLong(idClassificacao));
		biblioteca.setClassificacaoBiblioteca(classificacaoBibliotecaTO);
		//biblioteca.setBibliotecaId(78l);

		bibliotecaDAO.update(biblioteca);
		biblioteca = new Biblioteca();
		
		WebUtils.getInstance().redirectPage(
				"/MyLibraries_JavaEE7/app/biblioteca.xhtml");
		
	}

	public void deletar() {
		bibliotecaDAO.delete(biblioteca);
		WebUtils.getInstance().redirectPage(
				"/MyLibraries_JavaEE7/app/biblioteca.xhtml");
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

	public List<Biblioteca> getList1() {
		return list1;
	}

	public void setList1(List<Biblioteca> list1) {
		this.list1 = list1;
	}

	public List<Biblioteca> getList2() {
		return list2;
	}

	public void setList2(List<Biblioteca> list2) {
		this.list2 = list2;
	}

	public List<Biblioteca> getList3() {
		return list3;
	}

	public void setList3(List<Biblioteca> list3) {
		this.list3 = list3;
	}

	public Biblioteca getBiblioteca1() {
		return biblioteca1;
	}

	public void setBiblioteca1(Biblioteca biblioteca1) {
		this.biblioteca1 = biblioteca1;
	}

	public Biblioteca getBiblioteca2() {
		return biblioteca2;
	}

	public void setBiblioteca2(Biblioteca biblioteca2) {
		this.biblioteca2 = biblioteca2;
	}

	public Biblioteca getBiblioteca3() {
		return biblioteca3;
	}

	public void setBiblioteca3(Biblioteca biblioteca3) {
		this.biblioteca3 = biblioteca3;
	}

	public long getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(long idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public String getAux() {
		aux= biblioteca.getNome();
		return aux;
	}

	public void setAux(String aux) {
		this.aux = aux;
	}
	
	

}