package br.com.xpto.projetos.exception;

public class MembroNaoExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MembroNaoExistenteException(String mensagem) {
		super(mensagem);
	}

}
