package tk.mylibraries.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import tk.mylibraries.dao.BibliotecaDAO;
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
	private List<TipoBiblioteca> tipoBibliotecaList;
	private TipoBiblioteca biblioteca1;
	private TipoBiblioteca biblioteca2;
	private TipoBiblioteca biblioteca3;
	private List<ClassificacaoBiblioteca> classificacaoBibliotecaList;
	private TipoBiblioteca tipoBiblioteca;
	private ClassificacaoBiblioteca classificacaoBiblioteca;
	private List<Biblioteca> list1;
	private List<Biblioteca> list2;
	private List<Biblioteca> list3;

	public BibliotecaController() {
		bibliotecaDAO = new BibliotecaDAO(HibernateUtil.getEntityManager());
		biblioteca = new Biblioteca();
		classificacaoBibliotecaList = new ClassificacaoBibliotecaController()
				.getAll();
		tipoBibliotecaList = new TipoBibliotecaController().getAll();
		biblioteca1 = biblioteca2 = biblioteca3 = new TipoBiblioteca();
		biblioteca1 = tipoBibliotecaList.get(0);
		biblioteca2 = tipoBibliotecaList.get(1);
		biblioteca3 = tipoBibliotecaList.get(2);

		tipoBiblioteca = new TipoBiblioteca();
		classificacaoBiblioteca = new ClassificacaoBiblioteca();
		list1 = new ArrayList<Biblioteca>();
		list2 = new ArrayList<Biblioteca>();
		list3 = new ArrayList<Biblioteca>();
		getTipoBibliotecaByUser();

	}

	public void salvar() {
		Biblioteca biblioteca = new Biblioteca();
		biblioteca.setClassificacaoBiblioteca(classificacaoBiblioteca);
		biblioteca.setDesejado(this.biblioteca.isDesejado());
		biblioteca.setNome(this.biblioteca.getNome());
		biblioteca.setTipoBiblioteca(tipoBiblioteca);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		Long userId = (Long) session.getAttribute("user");
		EntityManager entityManager = HibernateUtil.getEntityManager();
		UsuarioDAO usuarioDAO = new UsuarioDAO(entityManager);
		Usuario usuario = usuarioDAO.getById(userId);

		biblioteca.setUsuario(usuario);

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

		for (Biblioteca biblioteca : listAll) {
			if (biblioteca != null) {
//				System.out.println("Tipo : " + biblioteca.getTipoBiblioteca().getTipoId() + "Nome :" +biblioteca.getNome());
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

	public List<Biblioteca> getAll() {
		return bibliotecaDAO.getAll();
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

	public TipoBiblioteca getTipoBiblioteca() {
		return tipoBiblioteca;
	}

	public void setTipoBiblioteca(TipoBiblioteca tipoBiblioteca) {
		this.tipoBiblioteca = tipoBiblioteca;
	}

	public ClassificacaoBiblioteca getClassificacaoBiblioteca() {
		return classificacaoBiblioteca;
	}

	public void setClassificacaoBiblioteca(
			ClassificacaoBiblioteca classificacaoBiblioteca) {
		this.classificacaoBiblioteca = classificacaoBiblioteca;
	}

	public List<TipoBiblioteca> getTipoBibliotecaList() {
		return tipoBibliotecaList;
	}

	public List<ClassificacaoBiblioteca> getClassificacaoBibliotecaList() {
		return classificacaoBibliotecaList;
	}

	public List<Biblioteca> getList1() {
		return list1;
	}

	public List<Biblioteca> getList2() {
		return list2;
	}

	public List<Biblioteca> getList3() {
		return list3;
	}

	public TipoBiblioteca getBiblioteca1() {
		return biblioteca1;
	}

	public TipoBiblioteca getBiblioteca2() {
		return biblioteca2;
	}

	public TipoBiblioteca getBiblioteca3() {
		return biblioteca3;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

}