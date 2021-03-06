package tk.mylibraries.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tk.mylibraries.dao.BibliotecaDAO;
import tk.mylibraries.dao.EmprestimoDAO;
import tk.mylibraries.dao.TipoBibliotecaDAO;
import tk.mylibraries.dao.UsuarioDAO;
import tk.mylibraries.entities.Biblioteca;
import tk.mylibraries.entities.Emprestimo;
import tk.mylibraries.entities.TipoBiblioteca;
import tk.mylibraries.entities.Usuario;
import tk.mylibraries.orm.HibernateUtil;
import tk.mylibraries.utils.Messages;
import tk.mylibraries.utils.WebUtils;

@ManagedBean
@ViewScoped
public class EmprestimoController implements Serializable {

	private Emprestimo emprestimo;

	private TipoBiblioteca tipoBiblioteca;
	private Biblioteca biblioteca;
	private BibliotecaDAO bibliotecaDAO;
	private String destinatario;
	private Date dataEmprestimo;
	private String observacao;
	private TipoBibliotecaDAO tipoBibliotecaDAO;
	private EmprestimoDAO emprestimoDAO;
	private List<TipoBiblioteca> typeLibraryList;
	private Map<String, Long> typeLibraryMap;
	private List<Biblioteca> libraryList;
	private Map<String, Long> libraryMap;
	private String idTypeLibrary;
	private String idLibrary;
	private UsuarioDAO usuarioDAO;

	private List<Emprestimo> listaEmprestimoL;
	private List<Emprestimo> listaEmprestimoM;
	private List<Emprestimo> listaEmprestimoV;

	public List<Emprestimo> getListaEmprestimoL() {
		return listaEmprestimoL;
	}

	public void setListaEmprestimo(List<Emprestimo> listaEmprestimoL) {
		this.listaEmprestimoL = listaEmprestimoL;
	}

	public List<Emprestimo> getListaEmprestimoM() {
		return listaEmprestimoM;
	}

	public void setListaEmprestimoM(List<Emprestimo> listaEmprestimoM) {
		this.listaEmprestimoM = listaEmprestimoM;
	}

	public List<Emprestimo> getListaEmprestimoV() {
		return listaEmprestimoV;
	}

	public void setListaEmprestimoV(List<Emprestimo> listaEmprestimoV) {
		this.listaEmprestimoV = listaEmprestimoV;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public EmprestimoController() {
		emprestimo = new Emprestimo();
		tipoBibliotecaDAO = new TipoBibliotecaDAO(
				HibernateUtil.getEntityManager());
		emprestimoDAO = new EmprestimoDAO(HibernateUtil.getEntityManager());
		tipoBiblioteca = new TipoBiblioteca();
		bibliotecaDAO = new BibliotecaDAO(HibernateUtil.getEntityManager());
		biblioteca = new Biblioteca();
		listaEmprestimoL = new ArrayList<Emprestimo>();
		listaEmprestimoM = new ArrayList<Emprestimo>();
		listaEmprestimoV = new ArrayList<Emprestimo>();
		usuarioDAO = new UsuarioDAO(HibernateUtil.getEntityManager());
		listasDeEmprestimos();
	}

	public void deletar() {
		emprestimoDAO.delete(emprestimo);
		WebUtils.getInstance().redirectPage("emprestimo.xhtml");
	}
	
	public void encerrar(){
		emprestimo.setAtivo(false);
		emprestimo.setDataEncerramento(new Date());
		emprestimoDAO.update(emprestimo);
		WebUtils.getInstance().redirectPage("emprestimo.xhtml");
	}//emp = emprestimoDAO.getAll();

	public void listasDeEmprestimos() {
		List<Emprestimo> emp = new ArrayList<Emprestimo>();
		Long idUser = WebUtils.getInstance().getIdUserSession();
		Usuario usuario = usuarioDAO.getById(idUser);
		emp = emprestimoDAO.ListByUser(usuario);
		for (Emprestimo emprestimo : emp) {
			if (emprestimo.getUsuario().getUsuarioId() == idUser) {
				if (emprestimo.getBiblioteca().getTipoBiblioteca().getTipoId() == 1)
					listaEmprestimoV.add(emprestimo);
				else {
					if (emprestimo.getBiblioteca().getTipoBiblioteca()
							.getTipoId() == 2)
						listaEmprestimoM.add(emprestimo);
					else {
						listaEmprestimoL.add(emprestimo);
					}
				}
			}

		}
	}

	// Usuario usuario = usuarioDAO.getById(WebUtils.getInstance()
	// .getIdUserSession());
	// emp = emprestimoDAO.ListByUser(usuario);

	/**
	 * Metodo que popula o combo box do tipo de biblioteca
	 */
	@PostConstruct
	private void init() {
		typeLibraryList = tipoBibliotecaDAO.getAll();
		typeLibraryMap = new HashMap<String, Long>();
		for (TipoBiblioteca tipoBiblioteca : typeLibraryList) {
			typeLibraryMap.put(tipoBiblioteca.getNome(),
					tipoBiblioteca.getTipoId());
		}
	}

	/**
	 * Metodo que popula o combo box das bibliotecas de acordo com o usuário
	 * logado.
	 * 
	 * @param tipoBiblioteca
	 *            {@link TipoBiblioteca}
	 */
	public void setLibraryMap(TipoBiblioteca tipoBiblioteca) {

		libraryList = emprestimoDAO.getBibliotecasPeloTipo(tipoBiblioteca);

		libraryMap = new HashMap<String, Long>();

		if (libraryList != null) {

			for (Biblioteca biblioteca : libraryList) {

				if (biblioteca.getUsuario().getUsuarioId() == WebUtils
						.getInstance().getIdUserSession()) {
					libraryMap.put(biblioteca.getNome(),
							biblioteca.getBibliotecaId());
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
		Biblioteca idLib = bibliotecaDAO.getById(Long.parseLong(idLibrary));
		emprestimo.setBiblioteca(idLib);
		emprestimo.setDataEmprestimo(getDataEmprestimo());
		emprestimo.setDestinatario(getDestinatario());
		emprestimo.setObservacao(getObservacao());
		emprestimo.setAtivo(true);
		Usuario user = usuarioDAO.getById(WebUtils.getInstance()
				.getIdUserSession());
		emprestimo.setUsuario(user);
		emprestimoDAO.save(emprestimo);
		WebUtils.getInstance().redirectPage("emprestimo.xhtml");
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
		this.idLibrary = idLibrary;
	}

}
