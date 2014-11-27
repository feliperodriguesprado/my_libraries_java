package tk.mylibraries.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usuarioid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_usuarioid_seq")
	@SequenceGenerator(name = "usuario_usuarioid_seq", sequenceName = "usuario_usuarioid_seq", allocationSize = 1)
	private Long usuarioId;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "senha", nullable = false, length = 30)
	private String senha;

	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", targetEntity = Biblioteca.class)
	private Set<Biblioteca> bibliotecas;

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioid) {
		this.usuarioId = usuarioid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
