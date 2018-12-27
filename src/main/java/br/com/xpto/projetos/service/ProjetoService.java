package br.com.xpto.projetos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xpto.projetos.exception.MembroInformadoNaoFuncionarioException;
import br.com.xpto.projetos.exception.ProjetoNaoCadastradoException;
import br.com.xpto.projetos.model.Pessoa;
import br.com.xpto.projetos.model.Projeto;
import br.com.xpto.projetos.repository.ProjetoRepository;
import br.com.xpto.projetos.validator.StatusProjetoValidator;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository repository;

	@Autowired
	private PessoaService pessoaService;

	public Iterable<Projeto> buscaTodos() {
		Iterable<Projeto> projetos = repository.findAll();
		return projetos;
	}

	public Projeto buscaPeloId(Long id) {
		return repository.findOne(id);
	}

	public Projeto salva(Projeto projeto) {
		return repository.saveAndFlush(projeto);
	}

	public void remove(Long id) {
		Projeto projeto = repository.findOne(id);
		new StatusProjetoValidator().valida(projeto);
		repository.delete(id);
	}

	public void adicionaMembroNoProjeto(Long idProjeto, Pessoa membro) {
		Optional<Projeto> optionalProjeto = repository.findById(idProjeto);
		
		if (!optionalProjeto.isPresent()) {
			throw new ProjetoNaoCadastradoException("projeto.crud.insercao.nao.pode.inserir.membro.pois.nao.tem.projeto");
		}
		
		Projeto projeto = optionalProjeto.get();
		Optional<Pessoa> optionalMembro = pessoaService.buscaPeloNome(membro.getNome());

		if (optionalMembro.isPresent()) {
			adicionaNoProjeto(projeto, optionalMembro.get());
		} else {
			adicionaNoProjeto(projeto, criaNovoMembro(projeto, membro));
		}
	}

	private void adicionaNoProjeto(Projeto projeto, Pessoa membro) {
		if (!membro.getFuncionario()) {
			throw new MembroInformadoNaoFuncionarioException("projeto.crud.insercao.nao.pode.inserir.membro.que.nao.e.funcionario");
		}
		projeto.adicionarMembro(membro);
		repository.save(projeto);
	}

	private Pessoa criaNovoMembro(Projeto projeto, Pessoa membro) {
		if (membro.getFuncionario() == null || !membro.getFuncionario()) {
			throw new MembroInformadoNaoFuncionarioException("projeto.crud.insercao.nao.pode.inserir.membro.que.nao.e.funcionario");
		}
		Pessoa membroParaAdicionar = new Pessoa();
		membroParaAdicionar.setNome(membro.getNome());
		membroParaAdicionar.setFuncionario(membro.getFuncionario());
		pessoaService.salva(membroParaAdicionar);
		return membroParaAdicionar;
	}
}
