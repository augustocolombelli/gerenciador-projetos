package br.com.xpto.projetos.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 200)
	@NotBlank(message = "O campo nome deve ser informado.")
	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataPrevisaoFim;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataFim;

	@Column(length = 5000)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(length = 45, nullable = false)
	@NotNull(message = "O projeto deve ter um status.")
	private Status status;

	private Float orcamento;

	@ManyToOne
	@NotNull(message = "O campo gerente deve ser informado.")
	private Pessoa gerente;

	@JoinTable(name = "membros")
	@OneToMany
	private Set<Pessoa> membros;

	@Enumerated(EnumType.STRING)
	@Column(length = 45, nullable = false)
	@NotNull(message = "O projeto deve ter uma classificação de risco.")
	private Risco risco;

	public Projeto() {
		membros = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataPrevisaoFim() {
		return dataPrevisaoFim;
	}

	public void setDataPrevisaoFim(Date dataPrevisaoFim) {
		this.dataPrevisaoFim = dataPrevisaoFim;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Float getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Float orcamento) {
		this.orcamento = orcamento;
	}

	public Pessoa getGerente() {
		return gerente;
	}

	public void setGerente(Pessoa gerente) {
		this.gerente = gerente;
	}

	public Set<Pessoa> getMembros() {
		return membros;
	}

	public void setMembros(Set<Pessoa> membros) {
		this.membros = membros;
	}

	public void adicionarMembro(Pessoa membro) {
		this.membros.add(membro);
	}

	public Risco getRisco() {
		return risco;
	}

	public void setRisco(Risco risco) {
		this.risco = risco;
	}

	public String getRiscoStr() {
		return risco.getNome();
	}

	public String getStatusStr() {
		return status.getNome();
	}

}
