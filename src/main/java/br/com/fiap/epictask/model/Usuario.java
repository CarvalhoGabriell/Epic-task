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
	
	@NotNull(message = "Preencha com email válido")
	private String email;
	
	@NotBlank(message = "Nome deve ser Obrigatório")
	@Size(min = 5, message = "Mínimo deve ser 5 caracteres")
	@Size(max = 50, message = "Máximo deve ser 50 caracteres")
	private String nome;
	
	@Size(min = 8, message = "Senha deve conter no minimo 8 caracteres")
	private String password;
}
