package br.com.xpto.projetos.validator;

import static br.com.xpto.projetos.model.Status.EM_ANDAMENTO;
import static br.com.xpto.projetos.model.Status.ENCERRADO;
import static br.com.xpto.projetos.model.Status.INICIADO;

import org.junit.Before;
import org.junit.Test;

import br.com.xpto.projetos.exception.StatusEmAndamentoNaoPermiteExclusaoException;
import br.com.xpto.projetos.exception.StatusEncerradoNaoPermiteExclusaoException;
import br.com.xpto.projetos.exception.StatusIniciadoNaoPermiteExclusaoException;
import br.com.xpto.projetos.model.Projeto;

public class StatusProjetoValidatorTest {

	private StatusProjetoValidator validador;
	
	@Before
	public void setUp() {
		validador = new StatusProjetoValidator();
	}
	
	@Test(expected=StatusIniciadoNaoPermiteExclusaoException.class)
	public void naoDevePermitirExclusaoQuandoStatusEstiverIniciado() {
		Projeto projeto = new Projeto();
		projeto.setStatus(INICIADO);
		validador.valida(projeto);
	}
	
	@Test(expected=StatusEmAndamentoNaoPermiteExclusaoException.class)
	public void naoDevePermitirExclusaoQuandoStatusEstiverEmAndamento() {
		Projeto projeto = new Projeto();
		projeto.setStatus(EM_ANDAMENTO);
		validador.valida(projeto);	
	}
	
	@Test(expected=StatusEncerradoNaoPermiteExclusaoException.class)
	public void naoDevePermitirExclusaoQuandoStatusEstiverEncerrado() {
		Projeto projeto = new Projeto();
		projeto.setStatus(ENCERRADO);
		validador.valida(projeto);	
	}
	
}
