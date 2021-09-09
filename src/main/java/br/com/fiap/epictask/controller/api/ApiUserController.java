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

import br.com.fiap.epictask.model.Usuario;
import br.com.fiap.epictask.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/user")
@ResponseBody
public class ApiUserController {
	
	@Autowired
	private UsuarioRepository repo;
	
	@GetMapping
	public Page<Usuario> index(@RequestParam(required = false) String email, @PageableDefault(size = 5) Pageable pageable) {
		
		if (email == null ) {
			return repo.findAll(pageable);
		}
		
		return repo.findByEmailLike("%"+ email+ "%", pageable);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario user, UriComponentsBuilder uriBuilder ) {
		
		if (user != null) {
			repo.save(user); 
			
			URI uri = uriBuilder
					.path("/api/use/{id}")
					.buildAndExpand(user.getId())
					.toUri();
			return ResponseEntity.created(uri).body(user);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Usuario> show(@PathVariable Long id) {
		
		return ResponseEntity.of(repo.findById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Usuario> delete(@PathVariable Long id) {
		
		Optional<Usuario> user = repo.findById(id);
		
		if (user.isPresent()) {
			
			repo.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Usuario> updates(@PathVariable Long id,@RequestBody @Valid Usuario newUser) {
		
		Optional<Usuario> findUser = repo.findById(id);
		
		if(findUser.isPresent()) {
			
			Usuario user = findUser.get();
			user.setEmail(newUser.getEmail());
			user.setNome(newUser.getNome());
			user.setPassword(newUser.getPassword());
			
			repo.save(user);
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.notFound().build();
	}
}
