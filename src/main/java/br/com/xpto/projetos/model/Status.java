package br.com.xpto.projetos.model;

public enum Status {

	EM_ANALISE("Em análise"), 
	ANALISE_REALIZADA("Análise realizada"), 
	ANALISE_APROVADA("Análise aprovada"),
	INICIADO("Iniciado"), 
	PLANEJADO("Planejado"), 
	EM_ANDAMENTO("Em andamento"), 
	ENCERRADO("Encerrado"),
	CANCELADO("Cancelado");
	
	private String nome;
	
	private Status(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
}
