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

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.repository.TaskRepository;

@Controller
public class TaskController {
	
	@Autowired
	private TaskRepository repo;
	
	@GetMapping("/tasks")
	public ModelAndView index() {
		List<Task> allTasks = repo.findAll();
		ModelAndView modelAndView = new ModelAndView("tasks"); // nome da view q vai retornar (arquivo HTML)
		modelAndView.addObject("tasks", allTasks);
		return modelAndView;
	}
	
	@RequestMapping("/tasks/new")
	public String create(Task task) {
		
		return "task-form";
	}
	
	
	@PostMapping("/tasks")
	public String save(@Valid Task task, BindingResult result) {
		
		if (result.hasErrors()) {
			
			return "task-form";
		}
		repo.save(task);
		System.out.println("Salvando tarefa..."+task);
		return "tasks";
	}
	

}
