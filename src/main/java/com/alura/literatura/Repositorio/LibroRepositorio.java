package com.alura.literatura.Repositorio;

import com.alura.literatura.Modelo.Autor;
import com.alura.literatura.Modelo.Idioma;
import com.alura.literatura.Modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    @Query("select a from Libro l join l.autores a")
    List<Autor> listarAutoresRegistrados();

    @Query("select a from Libro l join l.autores a where a.anioFallecimiento > :anio")
    List<Autor> listarAutoresVivosEnUnDeterminadoAnio(Integer anio);

    @Query("select l from Libro l where l.idioma = :idioma")
    List<Libro> listarLibrosPorIdioma(Idioma idioma);
}
