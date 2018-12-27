package br.com.xpto.projetos.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.xpto.projetos.exception.MembroInformadoNaoFuncionarioException;
import br.com.xpto.projetos.exception.ProjetoNaoCadastradoException;
import br.com.xpto.projetos.model.Pessoa;
import br.com.xpto.projetos.model.Projeto;
import br.com.xpto.projetos.repository.ProjetoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProjetoServiceTest {

	private static final String NOME_MEMBRO = "Nome qualquer";
	private static final long ID_PROJETO = 1L;

	@InjectMocks
	private ProjetoService service;
	
	@Mock
	private ProjetoRepository repository;
	
	@Mock
	private PessoaService pessoaService;
	
	private Projeto projetoQualquer;

	@Before
	public void setUp() {
		projetoQualquer = new Projeto();
	}
	
	@Test
	public void deveIncluirMembroJaCadastradoNoProjeto() {
		Pessoa membroParaAdicionarProjeto = new Pessoa();
		membroParaAdicionarProjeto.setNome(NOME_MEMBRO);
		
		Pessoa funcionarioCadastrado = criaMembroFuncionario();
		
		when(repository.findById(ID_PROJETO)).thenReturn(Optional.of(projetoQualquer));
		when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.of(funcionarioCadastrado));
		
		service.adicionaMembroNoProjeto(ID_PROJETO, membroParaAdicionarProjeto);
	
		verify(repository, times(1)).save(projetoQualquer);
	}

	@Test
	public void deveCadastrarNovaPessoaEIncluirNoProjeto() {
		Pessoa membroParaAdicionarProjeto = new Pessoa();
		membroParaAdicionarProjeto.setNome(NOME_MEMBRO);
		membroParaAdicionarProjeto.setFuncionario(true);
		
		when(repository.findById(ID_PROJETO)).thenReturn(Optional.of(projetoQualquer));
		when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.empty());
		
		service.adicionaMembroNoProjeto(ID_PROJETO, membroParaAdicionarProjeto);
	
		verify(repository, times(1)).save(projetoQualquer);
		
		ArgumentCaptor<Pessoa> membroCaptured = forClass(Pessoa.class);
		verify(pessoaService, times(1)).salva(membroCaptured.capture());
		
		assertEquals(membroCaptured.getValue().getNome(), membroParaAdicionarProjeto.getNome());
		assertEquals(membroCaptured.getValue().getFuncionario(), membroParaAdicionarProjeto.getFuncionario());
	}
	
	@Test(expected=ProjetoNaoCadastradoException.class)
	public void naoDeveAdicionarMembroSeProjetoNaoExistir() {
		Pessoa funcionarioParaAdicionarProjeto = new Pessoa();
		funcionarioParaAdicionarProjeto.setNome(NOME_MEMBRO);
		
		Pessoa funcionarioCadastrado = criaMembroFuncionario();
		
		when(repository.findById(ID_PROJETO)).thenReturn(Optional.empty());
		when(pessoaService.buscaPeloNome(funcionarioParaAdicionarProjeto.getNome())).thenReturn(Optional.of(funcionarioCadastrado));
		
		service.adicionaMembroNoProjeto(ID_PROJETO, funcionarioParaAdicionarProjeto);
	}
	
	@Test(expected=MembroInformadoNaoFuncionarioException.class)
	public void naoDeveIncluirPessoaQuandoNaoInformadoAtribuicao() {
		Pessoa membroParaAdicionarProjeto = new Pessoa();
		membroParaAdicionarProjeto.setNome(NOME_MEMBRO);
		
		when(repository.findById(ID_PROJETO)).thenReturn(Optional.of(projetoQualquer));
		when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.empty());
		
		service.adicionaMembroNoProjeto(ID_PROJETO, membroParaAdicionarProjeto);
	}
	
	@Test(expected=MembroInformadoNaoFuncionarioException.class)
	public void naoDeveIncluirPessoaQuandoNaoForFuncionario() {
		Pessoa membroParaAdicionarProjeto = criaMembroNaoFuncionario();
		
		when(repository.findById(ID_PROJETO)).thenReturn(Optional.of(projetoQualquer));
		when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.empty());
		
		service.adicionaMembroNoProjeto(ID_PROJETO, membroParaAdicionarProjeto);
	}
	
	@Test(expected=MembroInformadoNaoFuncionarioException.class)
	public void naoDeveAdicionarMembroQueNaoEhFuncionario() {
		Pessoa membroParaAdicionarProjeto = new Pessoa();
		membroParaAdicionarProjeto.setNome(NOME_MEMBRO);
		
		Pessoa membroNaoFuncionario = criaMembroNaoFuncionario();

		when(repository.findById(ID_PROJETO)).thenReturn(Optional.of(projetoQualquer));
		when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.of(membroNaoFuncionario));
		
		service.adicionaMembroNoProjeto(ID_PROJETO, membroParaAdicionarProjeto);
	}

	private Pessoa criaMembroFuncionario() {
		Pessoa membroFuncionario = new Pessoa();
		membroFuncionario.setFuncionario(true);
		membroFuncionario.setNome(NOME_MEMBRO);
		return membroFuncionario;
	}
	
	private Pessoa criaMembroNaoFuncionario() {
		Pessoa membroNaoFuncionario = new Pessoa();
		membroNaoFuncionario.setNome(NOME_MEMBRO);
		membroNaoFuncionario.setFuncionario(false);
		return membroNaoFuncionario;
	}
	
}
