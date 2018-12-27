package br.com.xpto.projetos.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.xpto.projetos.exception.MembroInformadoNaoFuncionarioException;
import br.com.xpto.projetos.exception.ProjetoNaoCadastradoException;
import br.com.xpto.projetos.model.Pessoa;
import br.com.xpto.projetos.model.Projeto;
import br.com.xpto.projetos.service.MessageLocaleService;
import br.com.xpto.projetos.service.ProjetoService;

@RestController
@RequestMapping("/pessoas")
public class PessoaRestController {

	@Autowired
	private ProjetoService service;
	
	@Autowired
	private MessageLocaleService messageLocaleService;

	@RequestMapping(value = "/{id}/membro", method = RequestMethod.PUT)
	public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody Pessoa membro) {
		try {
			service.adicionaMembroNoProjeto(id, membro);
			return new ResponseEntity<Projeto>(HttpStatus.OK);
		} catch (ProjetoNaoCadastradoException | MembroInformadoNaoFuncionarioException ex) {
			return new ResponseEntity<MensagemErroType>(new  MensagemErroType(messageLocaleService.getMessage(ex.getMessage())), HttpStatus.NOT_FOUND);
		}
	}

}
