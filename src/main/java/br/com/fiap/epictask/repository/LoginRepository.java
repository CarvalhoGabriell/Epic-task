package br.com.fiap.epictask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictask.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{

}
