package br.com.fiap.epictask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.epictask.model.Login;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String index(Login login) {
		
		return "login";
	}
	
	@RequestMapping("/login")
	public String save(Login user) {
		
		return "tasks";
	}
	
	/*
	@PostMapping("/login")
	public String login(@Valid Login login, BindingResult result) {
		
		if (result.hasErrors()) {
			
			return "login";
		}
		
		repo.save(login);
		//System.out.println("Realizando Login...."+login);
		return "tasks";
	}
	*/
}
