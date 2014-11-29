package tk.mylibraries.entities;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "emprestimo")
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "emprestimoid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emprestimo_emprestimoid_seq")
	@SequenceGenerator(name = "emprestimo_emprestimoid_seq", sequenceName = "emprestimo_emprestimoid_seq", allocationSize = 1)
	private Long emprestimoId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bibliotecaid")
	private Biblioteca biblioteca;

	@Column(name = "data_emprestimo", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataEmprestimo;

	@Column(name = "data_encerramento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataEncerramento;

	@Column(name = "destinatario", nullable = false, length = 100)
	private String destinatario;

	@Column(name = "observacao", length = 200)
	private String observacao;

	@Column(name = "ativo", nullable = false)
	private Boolean ativo;

	public Long getEmprestimoId() {
		return emprestimoId;
	}

	public void setEmprestimoId(Long emprestimoId) {
		this.emprestimoId = emprestimoId;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
