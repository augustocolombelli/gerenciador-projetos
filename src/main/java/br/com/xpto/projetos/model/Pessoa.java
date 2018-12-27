package br.com.xpto.projetos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pessoa {

	private static final String NÃO = "Não";
	private static final String SIM = "Sim";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	@NotBlank(message = "O campo nome deve ser informado.")
	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "datanascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(length = 14)
	private String cpf;
	private Boolean funcionario;

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getFuncionario() {
		return funcionario;
	}

	public String getFuncionarioStr() {
		if (funcionario) {
			return SIM;
		} else {
			return NÃO;
		}
	}

	public void setFuncionario(Boolean funcionario) {
		this.funcionario = funcionario;
	}

}
