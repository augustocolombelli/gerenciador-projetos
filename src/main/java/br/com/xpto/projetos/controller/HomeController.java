package br.com.xpto.projetos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		return "home";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
}
