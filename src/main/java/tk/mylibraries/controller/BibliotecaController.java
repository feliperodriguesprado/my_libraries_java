package tk.mylibraries.controller;

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

@ManagedBean
public class BibliotecaController {
	
	private Biblioteca biblioteca;
	private BibliotecaDAO bibliotecaDAO;
	private List<TipoBiblioteca> tipoBibliotecaList;
	private List<ClassificacaoBiblioteca> classificacaoBibliotecaList;
	private TipoBiblioteca tipoBiblioteca;
	private ClassificacaoBiblioteca classificacaoBiblioteca;
	
	public BibliotecaController() {
		bibliotecaDAO = new BibliotecaDAO(HibernateUtil.getEntityManager());
		biblioteca = new Biblioteca();
		classificacaoBibliotecaList = new ClassificacaoBibliotecaController().getAll();
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
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		Long userId = (Long) session.getAttribute("user");
		EntityManager entityManager = HibernateUtil.getEntityManager();
		UsuarioDAO usuarioDAO = new UsuarioDAO(entityManager);
		Usuario usuario = usuarioDAO.getById(userId);

		biblioteca.setUsuario(usuario);

		bibliotecaDAO.save(biblioteca);
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
}