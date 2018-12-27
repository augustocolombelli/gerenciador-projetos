package br.com.xpto.projetos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xpto.projetos.exception.ProjetoNaoCadastradoException;
import br.com.xpto.projetos.model.Pessoa;
import br.com.xpto.projetos.model.Projeto;
import br.com.xpto.projetos.repository.ProjetoRepository;
import br.com.xpto.projetos.validator.StatusProjetoValidator;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository repository;

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
		Optional<Projeto> optionalProjetoParaAtualizar = repository.findById(idProjeto);

		if (!optionalProjetoParaAtualizar.isPresent()) {
			throw new ProjetoNaoCadastradoException("projeto.crud.insercao.nao.pode.inserir.membro.pois.nao.tem.projeto");
		}

		Projeto projetoParaAtualizar = optionalProjetoParaAtualizar.get();
		projetoParaAtualizar.adicionarMembro(membro);
		repository.save(projetoParaAtualizar);
	}
}
