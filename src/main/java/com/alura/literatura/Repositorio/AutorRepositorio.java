package com.alura.literatura.Repositorio;

import com.alura.literatura.Modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombreAndAnioNacimiento(String nombre, Integer anioNacimiento);
}
