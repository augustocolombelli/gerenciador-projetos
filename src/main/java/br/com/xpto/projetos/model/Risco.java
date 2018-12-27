package br.com.xpto.projetos.model;

public enum Risco {

	BAIXO("Baixo"), MEDIO("Médio"), ALTO("Alto");

	private String nome;

	private Risco(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

}
