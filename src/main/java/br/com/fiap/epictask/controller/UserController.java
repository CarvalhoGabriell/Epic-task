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
public class UserController {

	@Autowired
	private UsuarioRepository repo;
	
	@GetMapping("/users")
	public ModelAndView index() {
		List<Usuario> allUsers = repo.findAll();
		ModelAndView modelAndView = new ModelAndView("users");
		modelAndView.addObject("users", allUsers); // Objeto adicionado na view para poder recuperar os dados
		return modelAndView;
	}
	
	@RequestMapping("/users/new")
	public String create(Usuario user) {
		
		return "register";
	}
	
	@PostMapping("/users")
	public String save(@Valid Usuario user, BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println("Paasou aqui com erros");
			return "register";
		}
		
		repo.save(user);
		return "users";
	}
	
}
