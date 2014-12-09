package tk.mylibraries.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import tk.mylibraries.dao.BibliotecaDAO;
import tk.mylibraries.dao.EmprestimoDAO;
import tk.mylibraries.dao.TipoBibliotecaDAO;
import tk.mylibraries.entities.Biblioteca;
import tk.mylibraries.entities.Emprestimo;
import tk.mylibraries.entities.TipoBiblioteca;
import tk.mylibraries.orm.HibernateUtil;
import tk.mylibraries.utils.Messages;
import tk.mylibraries.utils.WebUtils;

@ManagedBean
public class EmprestimoController {
	
	private Emprestimo emprestimo;
	private TipoBiblioteca tipoBiblioteca;
	private Biblioteca biblioteca;
	private BibliotecaDAO bibliotecaDAO;

	private TipoBibliotecaDAO tipoBibliotecaDAO;
	private EmprestimoDAO emprestimoDAO;
	
	private List<TipoBiblioteca> typeLibraryList;
	private Map<String, Long> typeLibraryMap;
	
	private List<Biblioteca> libraryList;
	private Map<String, Long> libraryMap;
	
	private String idTypeLibrary;
	private String idLibrary;
	
	public EmprestimoController() {
		tipoBibliotecaDAO = new TipoBibliotecaDAO(HibernateUtil.getEntityManager());
		emprestimoDAO = new EmprestimoDAO(HibernateUtil.getEntityManager());
		tipoBiblioteca = new TipoBiblioteca();
		bibliotecaDAO = new BibliotecaDAO(HibernateUtil.getEntityManager());
		biblioteca = new Biblioteca();
	}
	
	/**
	 * Metodo que popula o combo box do tipo de biblioteca
	 */
	@PostConstruct
	private void init() {
		typeLibraryList = tipoBibliotecaDAO.getAll();
		typeLibraryMap = new HashMap<String, Long>();
		for (TipoBiblioteca tipoBiblioteca : typeLibraryList) {
			typeLibraryMap.put(tipoBiblioteca.getNome(), tipoBiblioteca.getTipoId());
		}
	}
	
	/**
	 * Metodo que popula o combo box das bibliotecas de acordo com o usuário
	 * logado.
	 * 
	 * @param tipoBiblioteca {@link TipoBiblioteca}
	 */
	public void setLibraryMap(TipoBiblioteca tipoBiblioteca) {
		
		libraryList = emprestimoDAO.getBibliotecasPeloTipo(tipoBiblioteca);
		
		libraryMap = new HashMap<String, Long>();
		
		if (libraryList != null) {
			
			for (Biblioteca biblioteca : libraryList) {
				
				if (biblioteca.getUsuario().getUsuarioId() == WebUtils.getInstance().getIdUserSession()) {
					libraryMap.put(biblioteca.getNome(), biblioteca.getBibliotecaId());						
				}
			
			}
			
		}
		
	}
	
	/**
	 * Metodo que sera chamado quando for selecionado um item do combo box tipo
	 * de biblioteca
	 */
	public void onTypeLibraryChange() {
		
		if (idTypeLibrary.length() > 0 && idTypeLibrary != null) {
			
			for (TipoBiblioteca tipoBiblioteca : typeLibraryList) {
				if (tipoBiblioteca.getTipoId() == Long.parseLong(idTypeLibrary)) {
					setLibraryMap(tipoBiblioteca);
					break;
				}
			}
			
		} else {
			libraryMap = new HashMap<String, Long>();
		}
		
	}
	
	public void save() {
		emprestimoDAO.save(emprestimo);
		System.out.println(emprestimo.getBiblioteca());
		Messages.getInstance().setMessageInfo("Dados:", "ID tipo = " + idTypeLibrary + ", ID biblioteca = " + idLibrary);
	}
	
	public Map<String, Long> getTypeLibraryMap() {
		return typeLibraryMap;
	}

	public Map<String, Long> getLibraryMap() {
		return libraryMap;
	}
	
	public String getIdTypeLibrary() {
		return idTypeLibrary;
	}

	public void setIdTypeLibrary(String idTypeLibrary) {
		this.idTypeLibrary = idTypeLibrary;
	}
	
	public String getIdLibrary() {
		return idLibrary;
	}

	public void setIdLibrary(String idLibrary) {
		biblioteca = bibliotecaDAO.getById(Long.parseLong(idLibrary));
		emprestimo.setBiblioteca(biblioteca);
	}
	

}
