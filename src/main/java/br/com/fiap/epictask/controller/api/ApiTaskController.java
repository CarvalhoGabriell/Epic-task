package br.com.fiap.epictask.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.repository.TaskRepository;

@RestController
@RequestMapping("/api/tasks")
@ResponseBody
public class ApiTaskController {
	
	@Autowired
	private TaskRepository repo;
	
	@GetMapping
	public Page<Task> index(@RequestParam(required = false) String title, @PageableDefault(size = 5) Pageable pageable) {
		if (title == null) {
			
			return repo.findAll(pageable);
		}
		return repo.findByTitleLike("%"+ title + "%", pageable);
	}
	
	
	/* EXEMPLO DO BODY NO POST RESQUEST
	    "title": "Criando entidades",
	    "description": "nfdveçnvjenvjcnjvncfdçq",
	    "points": 21
	 */
	@PostMapping
	public ResponseEntity<Task> create(@RequestBody Task task, UriComponentsBuilder uriBuilder) {
		
		repo.save(task);
		System.out.println(task);
		
		URI uri = uriBuilder
				.path("/api/tasks/{id}")
				.buildAndExpand(task.getId())
				.toUri();
		return ResponseEntity.created(uri).body(task);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Task> show(@PathVariable Long id) {
		
		return ResponseEntity.of(repo.findById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Task> delete(@PathVariable Long id) {
		Optional<Task> task = repo.findById(id);
		
		if (task.isPresent()) {
			
			repo.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	// update
	@PutMapping("{id}")
	public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody @Valid Task newTask) {
		
		// buscar uma task no banco de dados
		Optional<Task> findTask = repo.findById(id);
		System.out.println(findTask);
		
		// verificar se ele existe
		if (findTask.isPresent()) {
			
			Task task = findTask.get();
			task.setDescription(newTask.getDescription());
			task.setPoints(newTask.getPoints());
			task.setTitle(newTask.getTitle());
			repo.save(task);
			
			return ResponseEntity.ok(task);
	
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	
	
}
