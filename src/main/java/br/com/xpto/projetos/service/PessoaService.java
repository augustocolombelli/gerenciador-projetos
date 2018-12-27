package br.com.xpto.projetos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xpto.projetos.exception.AtribuicaoDoMembroNaoInformadoException;
import br.com.xpto.projetos.model.Pessoa;
import br.com.xpto.projetos.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public Iterable<Pessoa> buscaTodos() {
		Iterable<Pessoa> pessoas = repository.findAll();
		return pessoas;
	}

	public Pessoa buscaPeloId(Long id) {
		return repository.findOne(id);
	}

	public Pessoa salva(Pessoa pessoa) {
		if(pessoa.getFuncionario() == null) {
			throw new AtribuicaoDoMembroNaoInformadoException("pessoa.crud.insercao.nao.pode.incluir.pessoa.sem.atribuicao");
		}
		
		return repository.saveAndFlush(pessoa);
	}

	public void remove(Long id) {
		repository.delete(id);
	}
	
	public Optional<Pessoa> buscaPeloNome(String nome) {
		return repository.findByNome(nome);
	}

}
