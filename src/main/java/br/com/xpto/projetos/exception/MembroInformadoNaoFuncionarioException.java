package br.com.xpto.projetos.exception;

public class MembroInformadoNaoFuncionarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MembroInformadoNaoFuncionarioException(String mensagem) {
		super(mensagem);
	}

}
