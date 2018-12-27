package br.com.xpto.projetos.exception;

public class AtribuicaoDoMembroNaoInformadoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AtribuicaoDoMembroNaoInformadoException(String mensagem) {
		super(mensagem);
	}
}
