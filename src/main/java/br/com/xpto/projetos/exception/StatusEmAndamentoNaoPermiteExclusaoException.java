package br.com.xpto.projetos.exception;

public class StatusEmAndamentoNaoPermiteExclusaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public StatusEmAndamentoNaoPermiteExclusaoException(String mensagem) {
		super(mensagem);
	}
}
