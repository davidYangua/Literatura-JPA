package com.alura.literatura.Modelo;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Libros")
public class Libro {

    @Id
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDescargas;
    @ManyToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

    public Libro() {
    }

    public Libro(Results results) {
        this.id = results.id();
        this.titulo = results.titulo();
        this.idioma =  Idioma.value(results.lenguaje().get(0));
        this.numeroDescargas = results.descargas();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(i-> i.setLibros(List.of(this)));
        this.autores = autores;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "\n----- Libro -----" +"\n"+
                "Titulo : " + titulo +"\n"+
                "Autor : " + autores.get(0).getNombre() + "\n"+
                "Idioma : " + idioma.abreviatura +"\n"+
                "Numero de Descargas : " + numeroDescargas +"\n"+
                "---------------------------";
    }
}
