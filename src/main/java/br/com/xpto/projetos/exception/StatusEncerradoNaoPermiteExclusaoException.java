package br.com.xpto.projetos.exception;

public class StatusEncerradoNaoPermiteExclusaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StatusEncerradoNaoPermiteExclusaoException(String mensagem) {
		super(mensagem);
	}
}