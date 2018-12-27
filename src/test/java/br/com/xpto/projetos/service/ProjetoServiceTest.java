package br.com.xpto.projetos.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
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

	@InjectMocks
	private ProjetoService service;
	
	@Mock
	private ProjetoRepository repository;
	
	@Mock
	private PessoaService pessoaService;

	@Test
	public void deveAdicionarMembroExistenteNoProjeto() {
		Long idDoProjeto = 1L;
		String nomeDoMembro = "Juca";
		
		Pessoa membroParaAdicionarProjeto = new Pessoa();
		membroParaAdicionarProjeto.setNome(nomeDoMembro);
		
		Projeto projeto = new Projeto();
		projeto.setNome("Projeto - Beira Rio");
		when(repository.findById(idDoProjeto)).thenReturn(Optional.of(projeto));
		
		Pessoa pessoa = new Pessoa();
		pessoa.setFuncionario(true);
		pessoa.setNome(nomeDoMembro);
		when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.of(pessoa));
		
		service.adicionaMembroNoProjeto(idDoProjeto, membroParaAdicionarProjeto);
	
		verify(repository, times(1)).save(projeto);
	}
	
	@Test(expected=ProjetoNaoCadastradoException.class)
	public void naoDeveAdicionarMembroSeProjetoNaoEstaCadastrado() {
		Long idDoProjeto = 1L;
		String nomeDoMembro = "Juca";
		
		Pessoa membroParaAdicionarProjeto = new Pessoa();
		membroParaAdicionarProjeto.setNome(nomeDoMembro);
		
		Projeto projeto = new Projeto();
		projeto.setNome("Projeto - Beira Rio");
		when(repository.findById(idDoProjeto)).thenReturn(Optional.empty());
		
		Pessoa pessoa = new Pessoa();
		pessoa.setFuncionario(true);
		pessoa.setNome(nomeDoMembro);
		Mockito.when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.of(pessoa));
		
		service.adicionaMembroNoProjeto(idDoProjeto, membroParaAdicionarProjeto);
	}
	
	@Test
	public void deveCadastrarNovaPessoaQuandoMembroNaoExistir() {
		Long idDoProjeto = 1L;
		String nomeDoMembro = "Juca";
		
		Pessoa membroParaAdicionarProjeto = new Pessoa();
		membroParaAdicionarProjeto.setNome(nomeDoMembro);
		membroParaAdicionarProjeto.setFuncionario(true);
		
		
		Projeto projeto = new Projeto();
		projeto.setNome("Projeto - Beira Rio");
		when(repository.findById(idDoProjeto)).thenReturn(Optional.of(projeto));
		
		Pessoa pessoa = new Pessoa();
		pessoa.setFuncionario(true);
		pessoa.setNome(nomeDoMembro);
		when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.empty());
		
		service.adicionaMembroNoProjeto(idDoProjeto, membroParaAdicionarProjeto);
	
		verify(repository, times(1)).save(projeto);
		
		ArgumentCaptor<Pessoa> membroCaptured = forClass(Pessoa.class);
		verify(pessoaService, times(1)).salva(membroCaptured.capture());
		
		assertEquals(membroCaptured.getValue().getNome(), membroParaAdicionarProjeto.getNome());
		assertEquals(membroCaptured.getValue().getFuncionario(), membroParaAdicionarProjeto.getFuncionario());
	}

	
	@Test(expected=MembroInformadoNaoFuncionarioException.class)
	public void naoDeveCadastrarNovaPessoaQuandoNaoInformadoSeEhFuncionario() {
		Long idDoProjeto = 1L;
		String nomeDoMembro = "Juca";
		
		Pessoa membroParaAdicionarProjeto = new Pessoa();
		membroParaAdicionarProjeto.setNome(nomeDoMembro);
		
		Projeto projeto = new Projeto();
		projeto.setNome("Projeto - Beira Rio");
		when(repository.findById(idDoProjeto)).thenReturn(Optional.of(projeto));
		
		Pessoa pessoa = new Pessoa();
		pessoa.setFuncionario(true);
		pessoa.setNome(nomeDoMembro);
		when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.empty());
		
		service.adicionaMembroNoProjeto(idDoProjeto, membroParaAdicionarProjeto);
	
		verify(repository, times(1)).save(projeto);
		
		ArgumentCaptor<Pessoa> membroCaptured = forClass(Pessoa.class);
		verify(pessoaService, times(1)).salva(membroCaptured.capture());
		
		assertEquals(membroCaptured.getValue().getNome(), membroParaAdicionarProjeto.getNome());
		assertEquals(membroCaptured.getValue().getFuncionario(), membroParaAdicionarProjeto.getFuncionario());
	}
	
	@Test(expected=MembroInformadoNaoFuncionarioException.class)
	public void naoDeveCadastrarNovaPessoaQuandoNaoEhFuncionario() {
		Long idDoProjeto = 1L;
		String nomeDoMembro = "Juca";
		
		Pessoa membroParaAdicionarProjeto = new Pessoa();
		membroParaAdicionarProjeto.setNome(nomeDoMembro);
		membroParaAdicionarProjeto.setFuncionario(false);
		
		Projeto projeto = new Projeto();
		projeto.setNome("Projeto - Beira Rio");
		when(repository.findById(idDoProjeto)).thenReturn(Optional.of(projeto));
		
		Pessoa pessoa = new Pessoa();
		pessoa.setFuncionario(true);
		pessoa.setNome(nomeDoMembro);
		when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.empty());
		
		service.adicionaMembroNoProjeto(idDoProjeto, membroParaAdicionarProjeto);
	
		verify(repository, times(1)).save(projeto);
		
		ArgumentCaptor<Pessoa> membroCaptured = forClass(Pessoa.class);
		verify(pessoaService, times(1)).salva(membroCaptured.capture());
		
		assertEquals(membroCaptured.getValue().getNome(), membroParaAdicionarProjeto.getNome());
		assertEquals(membroCaptured.getValue().getFuncionario(), membroParaAdicionarProjeto.getFuncionario());
	}
	
	@Test(expected=MembroInformadoNaoFuncionarioException.class)
	public void naodeveAdicionarMembroExistenteNoProjetoQuandoNaoEhFuncionario() {
		Long idDoProjeto = 1L;
		String nomeDoMembro = "Juca";
		
		Pessoa membroParaAdicionarProjeto = new Pessoa();
		membroParaAdicionarProjeto.setNome(nomeDoMembro);
		
		
		Projeto projeto = new Projeto();
		projeto.setNome("Projeto - Beira Rio");
		when(repository.findById(idDoProjeto)).thenReturn(Optional.of(projeto));
		
		Pessoa pessoa = new Pessoa();
		pessoa.setFuncionario(true);
		pessoa.setNome(nomeDoMembro);
		pessoa.setFuncionario(false);
		when(pessoaService.buscaPeloNome(membroParaAdicionarProjeto.getNome())).thenReturn(Optional.of(pessoa));
		
		service.adicionaMembroNoProjeto(idDoProjeto, membroParaAdicionarProjeto);
	
		verify(repository, times(1)).save(projeto);
	}
	
}
