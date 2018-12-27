package br.com.xpto.projetos.validator;

import br.com.xpto.projetos.exception.StatusEmAndamentoNaoPermiteExclusaoException;
import br.com.xpto.projetos.exception.StatusEncerradoNaoPermiteExclusaoException;
import br.com.xpto.projetos.exception.StatusIniciadoNaoPermiteExclusaoException;
import br.com.xpto.projetos.model.Projeto;
import br.com.xpto.projetos.model.Status;

public class StatusProjetoValidator {

	public void valida(Projeto projeto) {
		if(projeto.getStatus() == Status.INICIADO) {
			throw new StatusIniciadoNaoPermiteExclusaoException("projeto.crud.exlusao.nao.pode.ser.excluido.pois.status.igual.iniciado");
		}
		if(projeto.getStatus() == Status.EM_ANDAMENTO) {
			throw new StatusEmAndamentoNaoPermiteExclusaoException("projeto.crud.exlusao.nao.pode.ser.excluido.pois.status.igual.em.andamento");
		}
		if(projeto.getStatus() == Status.ENCERRADO) {
			throw new StatusEncerradoNaoPermiteExclusaoException("projeto.crud.exlusao.nao.pode.ser.excluido.pois.status.igual.encerrado");
		}
		
	}
	
}
