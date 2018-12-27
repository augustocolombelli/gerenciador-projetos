package br.com.xpto.projetos.exception;

public class StatusIniciadoNaoPermiteExclusaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public StatusIniciadoNaoPermiteExclusaoException(String mensagem) {
		super(mensagem);
	}
}
