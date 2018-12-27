package br.com.xpto.projetos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.xpto.projetos.exception.StatusEmAndamentoNaoPermiteExclusaoException;
import br.com.xpto.projetos.exception.StatusEncerradoNaoPermiteExclusaoException;
import br.com.xpto.projetos.exception.StatusIniciadoNaoPermiteExclusaoException;
import br.com.xpto.projetos.model.Projeto;
import br.com.xpto.projetos.service.MessageLocaleService;
import br.com.xpto.projetos.service.PessoaService;
import br.com.xpto.projetos.service.ProjetoService;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {
	
	@Autowired
	private ProjetoService service;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private MessageLocaleService messageLocaleService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listarProjetos(){
		ModelAndView mv = new ModelAndView("/projetoLista");
		mv.addObject("projetos", service.buscaTodos());
		return mv;
	}
	
	@RequestMapping(value="/adicionar", method=RequestMethod.GET)
	public ModelAndView adicionarProjeto(Projeto projeto){
		ModelAndView mv = new ModelAndView("/projetoFormulario");
		mv.addObject("pessoas", pessoaService.buscaTodos());
		mv.addObject("projeto", projeto);
		return mv;
	}
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public ModelAndView editarProjeto(@PathVariable("id") Long id){
		return adicionarProjeto(service.buscaPeloId(id));
	}
	
	@RequestMapping(value="/remover/{id}", method=RequestMethod.GET)
	public ModelAndView removerProjeto(@PathVariable("id") Long id){
			service.remove(id);
			return listarProjetos();
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public ModelAndView salvarProjeto(@Valid Projeto projeto, BindingResult result){
		if(result.hasErrors()) {
			return adicionarProjeto(projeto);
		}
		service.salva(projeto);
		return listarProjetos();
	}
	
	@ExceptionHandler({StatusIniciadoNaoPermiteExclusaoException.class, StatusEmAndamentoNaoPermiteExclusaoException.class, StatusEncerradoNaoPermiteExclusaoException.class})
	public ModelAndView gerenciaExcecoes(RuntimeException ex) {
	    ModelAndView modelAndView = new ModelAndView("/projetoLista", "error", messageLocaleService.getMessage(ex.getMessage()));
	    modelAndView.addObject("projetos", service.buscaTodos());
		return modelAndView;
	}

}
