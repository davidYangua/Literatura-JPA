package com.alura.literatura;

import com.alura.literatura.Principal.Principal;
import com.alura.literatura.Repositorio.AutorRepositorio;
import com.alura.literatura.Repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LibroRepositorio libroRepositorio;
	@Autowired
	private AutorRepositorio autorRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//https://gutendex.com/
		Principal principal = new Principal(libroRepositorio, autorRepositorio);
		principal.mostrarMenu();
	}
}
