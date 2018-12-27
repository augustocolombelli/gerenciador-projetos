package br.com.xpto.projetos.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.xpto.projetos.exception.AtribuicaoDoMembroNaoInformadoException;
import br.com.xpto.projetos.model.Pessoa;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

	@InjectMocks
	private PessoaService pessoaService;

	@Test(expected = AtribuicaoDoMembroNaoInformadoException.class)
	public void naoDeveIncluirPessoaQuandoNaoInformadoSeEhFuncionario() {
		pessoaService.salva(new Pessoa());
	}

}
