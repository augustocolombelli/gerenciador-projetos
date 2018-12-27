package br.com.xpto.projetos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xpto.projetos.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

	Optional<Projeto> findById(Long id);
}
