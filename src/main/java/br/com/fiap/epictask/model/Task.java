package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
@Entity
public class Task {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "Titulo não deve estar em Branco")
	private String title;
	
	@Size(min = 12, message = "Descrição Deve conter no minimo 10 caracteres")
	private String description;
	
	@Min(value = 10, message = "A pontuação Minima é 10")
	@Max(value = 500, message = "A pontuação Maxima é 500")
	private int points;

}
