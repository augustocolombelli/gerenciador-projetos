package br.com.xpto.projetos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.xpto.projetos.model.Pessoa;
import br.com.xpto.projetos.service.PessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService service;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listarPessoas(){
		ModelAndView mv = new ModelAndView("/pessoaLista");
		Iterable<Pessoa> pessoas = service.buscaTodos();
		mv.addObject("pessoas", pessoas);
		return mv;
	}
	
	@RequestMapping(value="/adicionar", method=RequestMethod.GET)
	public ModelAndView adicionarPessoa(Pessoa pessoa){
		ModelAndView mv = new ModelAndView("/pessoaFormulario");
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public ModelAndView editarPessoa(@PathVariable("id") Long id){
		return adicionarPessoa(service.buscaPeloId(id));
	}
	
	@RequestMapping(value="/remover/{id}", method=RequestMethod.GET)
	public ModelAndView removerPessoa(@PathVariable("id") Long id){
		service.remove(id);
		return listarPessoas();
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public ModelAndView salvarPessoa(@Valid Pessoa pessoa, BindingResult result){
		if(result.hasErrors()) {
			return adicionarPessoa(pessoa);
		}
		service.salva(pessoa);
		return listarPessoas();
	}
}
