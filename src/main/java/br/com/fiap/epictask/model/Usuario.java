package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Usuario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "{user.title.email}")
	private String email;
	
	@NotBlank(message = "{user.title.name}")
	@Size(min = 5, message = "{user.name.size.min}")
	@Size(max = 50, message = "{user.name.size.max}")
	private String nome;
	
	@Size(min = 8, message = "{user.title.password}")
	private String password;
}
