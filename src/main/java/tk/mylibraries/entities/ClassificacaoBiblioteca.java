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
@Table(name = "classificacao_biblioteca")
public class ClassificacaoBiblioteca implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "classificacaoid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classificacao_biblioteca_classificacaoid_seq")
	@SequenceGenerator(name = "classificacao_biblioteca_classificacaoid_seq", sequenceName = "classificacao_biblioteca_classificacaoid_seq", allocationSize = 1)
	private Long classificacaoId;
	
	@Column(name = "nome", nullable = false, length = 30)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classificacaoBiblioteca", targetEntity = Biblioteca.class)
	private Set<Biblioteca> bibliotecas;

	public Long getClassificacaoId() {
		return classificacaoId;
	}

	public void setClassificacaoId(Long classificacaoId) {
		this.classificacaoId = classificacaoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
