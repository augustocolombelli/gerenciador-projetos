package br.com.xpto.projetos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return repository.saveAndFlush(pessoa);
	}

	public void remove(Long id) {
		repository.delete(id);
	}

}
