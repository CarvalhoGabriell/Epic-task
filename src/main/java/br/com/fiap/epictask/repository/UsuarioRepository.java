package br.com.fiap.epictask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictask.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
}
