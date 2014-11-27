package tk.mylibraries.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "biblioteca")
public class Biblioteca implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bibliotecaid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "biblioteca_bibliotecaid_seq")
	@SequenceGenerator(name = "biblioteca_bibliotecaid_seq", sequenceName = "biblioteca_bibliotecaid_seq", allocationSize = 1)
	private Long bibliotecaId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarioid")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classificacaoid")
	private ClassificacaoBiblioteca classificacaoBiblioteca;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoid")
	private TipoBiblioteca tipoBiblioteca;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "desejado", nullable = false)
	private boolean desejado;

	public Long getBibliotecaId() {
		return bibliotecaId;
	}

	public void setBibliotecaId(Long bibliotecaId) {
		this.bibliotecaId = bibliotecaId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ClassificacaoBiblioteca getClassificacaoBiblioteca() {
		return classificacaoBiblioteca;
	}

	public void setClassificacaoBiblioteca(
			ClassificacaoBiblioteca classificacaoBiblioteca) {
		this.classificacaoBiblioteca = classificacaoBiblioteca;
	}

	public TipoBiblioteca getTipoBiblioteca() {
		return tipoBiblioteca;
	}

	public void setTipoBiblioteca(TipoBiblioteca tipoBiblioteca) {
		this.tipoBiblioteca = tipoBiblioteca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isDesejado() {
		return desejado;
	}

	public void setDesejado(boolean desejado) {
		this.desejado = desejado;
	}

}
