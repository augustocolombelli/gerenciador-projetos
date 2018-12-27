package br.com.xpto.projetos.exception;

public class ProjetoNaoCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProjetoNaoCadastradoException(String mensagem) {
		super(mensagem);
	}

}
