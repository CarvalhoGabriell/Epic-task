package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictask.model.Usuario;
import br.com.fiap.epictask.repository.UsuarioRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioRepository repo;
	
	@GetMapping("/login")
	public String index() {
	
		return "login";
	}
	
	@RequestMapping("/login")
	public String save(Usuario user) {
		
		return "tasks";
	}
	
	@PostMapping("/login")
	public String login(@Valid Usuario login, BindingResult result) {
		
		if (result.hasErrors()) {
			
			return "login";
		}
		
		repo.save(login);
		System.out.println("Realizando Login...."+login);
		return "tasks";
	}
}
