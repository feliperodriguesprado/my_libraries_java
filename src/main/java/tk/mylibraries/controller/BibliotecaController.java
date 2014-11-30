package tk.mylibraries.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import tk.mylibraries.dao.BibliotecaDAO;
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
	private List<TipoBiblioteca> tipoBibliotecaList;
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
		tipoBiblioteca = new TipoBiblioteca();
		classificacaoBiblioteca = new ClassificacaoBiblioteca();

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

		List<Object> listAll = new ArrayList<Object>();
		long id = WebUtils.getInstance().getIdUserSession();
		UsuarioDAO usuarioDAO = new UsuarioDAO(HibernateUtil.getEntityManager());
		Usuario usuario = usuarioDAO.getById(id);

		TipoBibliotecaDAO tipoBibliotecaDAO = new TipoBibliotecaDAO(
				HibernateUtil.getEntityManager());
		List<TipoBiblioteca> listTipos = tipoBibliotecaDAO.getAll();
		EntityManager em = HibernateUtil.getEntityManager();
		WebUtils webUtils = WebUtils.getInstance();
		long idUsuario = webUtils.getIdUserSession();

		for (TipoBiblioteca tipoBiblioteca : listTipos) {
			long idTipo = tipoBiblioteca.getTipoId();

			try {
				String query = "select * from biblioteca join tipo_biblioteca on tipo_biblioteca.tipoid = biblioteca.bibliotecaid join usuario on usuario.usuarioid = biblioeca.usuarioid where usuarioid = :idUsuario and tipo_biblioteca.tipoid = :idTipo";
				Query q = em.createQuery(query);
				q.setParameter("idUsuario", idUsuario);
				q.setParameter("idTipo", idTipo);
				Object result = q.getResultList();
				listAll.add(result);
			} catch (NoResultException e) {
			}
		}
		list1 = (List<Biblioteca>) listAll.get(0);
		list2 = (List<Biblioteca>) listAll.get(1);
		list3 = (List<Biblioteca>) listAll.get(2);

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

}