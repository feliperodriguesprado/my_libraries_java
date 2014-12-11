package tk.mylibraries.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import tk.mylibraries.dao.BibliotecaDAO;
import tk.mylibraries.entities.Biblioteca;
import tk.mylibraries.orm.HibernateUtil;
import tk.mylibraries.utils.WebUtils;

@ManagedBean
public class MainController {

	private Long userId;
	private BibliotecaDAO bibliotecaDAO;
	
	private List<Biblioteca> bibliotecaList;
	private List<Biblioteca> bibliotecaLivroList;
	private List<Biblioteca> bibliotecaMusicaList;
	private List<Biblioteca> bibliotecaVideoList;

	private List<Biblioteca> bibliotecaLivroListDes;
	private List<Biblioteca> bibliotecaMusicaListDes;
	private List<Biblioteca> bibliotecaVideoListDes;
	
	public MainController() {
		userId = WebUtils.getInstance().getIdUserSession();
		bibliotecaDAO = new BibliotecaDAO(HibernateUtil.getEntityManager());

		bibliotecaList = bibliotecaDAO.getAll();
		
		bibliotecaLivroList = new ArrayList<Biblioteca>();
		bibliotecaMusicaList = new ArrayList<Biblioteca>();
		bibliotecaVideoList = new ArrayList<Biblioteca>();
		setBibliotecaLivroList();
		setBibliotecaMusicaList();
		setBibliotecaVideoList();

		bibliotecaLivroListDes = new ArrayList<Biblioteca>();
		bibliotecaMusicaListDes = new ArrayList<Biblioteca>();
		bibliotecaVideoListDes = new ArrayList<Biblioteca>();
		setBibliotecaLivroDesList();
		setBibliotecaMusicaDesList();
		setBibliotecaVideoDesList();
		
	}

	// Painel de Livros
	
	public List<Biblioteca> getBibliotecaLivroList() {
		return bibliotecaLivroList;
	}

	public void setBibliotecaLivroList() {
		for (Biblioteca biblioteca : bibliotecaList) {
			if (biblioteca.getTipoBiblioteca().getTipoId() == 1
					&& biblioteca.getUsuario().getUsuarioId() == userId) {
				addBibliotecaLivroList(biblioteca);
				
				if (bibliotecaLivroList.size() == 5) {
					break;
				}
				
			}
		}
	}
	
	public void addBibliotecaLivroList(Biblioteca biblioteca) {
		this.bibliotecaLivroList.add(biblioteca);
	}
	
	
	// Painel de Músicas
	
	public List<Biblioteca> getBibliotecaMusicaList() {
		return bibliotecaMusicaList;
	}
	
	public void setBibliotecaMusicaList() {
		for (Biblioteca biblioteca : bibliotecaList) {
			if (biblioteca.getTipoBiblioteca().getTipoId() == 2
					&& biblioteca.getUsuario().getUsuarioId() == userId) {
				addBibliotecaMusicasList(biblioteca);
				
				if (bibliotecaMusicaList.size() == 5) {
					break;
				}
				
			}
		}
	}
	
	public void addBibliotecaMusicasList(Biblioteca biblioteca) {
		this.bibliotecaMusicaList.add(biblioteca);
	}
	
	
	// Painel de Videos
	
	public List<Biblioteca> getBibliotecaVideoList() {
		return bibliotecaVideoList;
	}

	public void setBibliotecaVideoList() {
		for (Biblioteca biblioteca : bibliotecaList) {
			if (biblioteca.getTipoBiblioteca().getTipoId() == 3
					&& biblioteca.getUsuario().getUsuarioId() == userId) {
				addBibliotecaVideoList(biblioteca);
				
				if (bibliotecaVideoList.size() == 5) {
					break;
				}
				
			}
		}
	}

	public void addBibliotecaVideoList(Biblioteca biblioteca) {
		this.bibliotecaVideoList.add(biblioteca);
	}
	
	
	// Painel de Livros desejados

	public List<Biblioteca> getBibliotecaLivroDesList() {
		return bibliotecaLivroListDes;
	}

	public void setBibliotecaLivroDesList() {
		for (Biblioteca biblioteca : bibliotecaList) {
			if (biblioteca.getTipoBiblioteca().getTipoId() == 1
					&& biblioteca.getUsuario().getUsuarioId() == userId
					&& biblioteca.isDesejado()) {
				addBibliotecaLivroDesList(biblioteca);
			}
		}
	}

	public void addBibliotecaLivroDesList(Biblioteca biblioteca) {
		bibliotecaLivroListDes.add(biblioteca);
	}
	
	// Painel de Musicas desejados

	public List<Biblioteca> getBibliotecaMusicaDesList() {
		return bibliotecaMusicaListDes;
	}

	public void setBibliotecaMusicaDesList() {
		for (Biblioteca biblioteca : bibliotecaList) {
			if (biblioteca.getTipoBiblioteca().getTipoId() == 2
					&& biblioteca.getUsuario().getUsuarioId() == userId
					&& biblioteca.isDesejado()) {
				addBibliotecaMusicaDesList(biblioteca);
			}
		}
	}

	public void addBibliotecaMusicaDesList(Biblioteca biblioteca) {
		bibliotecaMusicaListDes.add(biblioteca);
	}
	
	
	// Painel de Videos desejados

		public List<Biblioteca> getBibliotecaVideoDesList() {
			return bibliotecaVideoListDes;
		}

		public void setBibliotecaVideoDesList() {
			for (Biblioteca biblioteca : bibliotecaList) {
				if (biblioteca.getTipoBiblioteca().getTipoId() == 3
						&& biblioteca.getUsuario().getUsuarioId() == userId
						&& biblioteca.isDesejado()) {
					addBibliotecaVideoDesList(biblioteca);
				}
			}
		}

		public void addBibliotecaVideoDesList(Biblioteca biblioteca) {
			bibliotecaVideoListDes.add(biblioteca);
		}
	
	
	
}
