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
@Table(name = "tipo_biblioteca")
public class TipoBiblioteca implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tipoid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_biblioteca_tipoid_seq")
	@SequenceGenerator(name = "tipo_biblioteca_tipoid_seq", sequenceName = "tipo_biblioteca_tipoid_seq", allocationSize = 1)
	private Long tipoId;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoBiblioteca", targetEntity = Biblioteca.class)
	private Set<Biblioteca> bibliotecas;

	public Long getTipoId() {
		return tipoId;
	}

	public void setTipoId(Long tipoId) {
		this.tipoId = tipoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
