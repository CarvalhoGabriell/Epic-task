package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.model.Usuario;
import br.com.fiap.epictask.repository.UsuarioRepository;

@Controller
public class UserController {
	
	@Autowired
	private MessageSource messages;
	
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
	public String save(@Valid Usuario user, BindingResult result, RedirectAttributes redirect) {
		
		if (result.hasErrors()) {
			return "register";
		}
		
		repo.save(user);
		redirect.addFlashAttribute("message", messages.getMessage("alert.newUser.success", null, LocaleContextHolder.getLocale()));
		return "redirect:users";
	}
	
}
