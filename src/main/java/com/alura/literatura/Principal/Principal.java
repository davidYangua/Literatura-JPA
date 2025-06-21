package com.alura.literatura.Principal;

import com.alura.literatura.Modelo.*;
import com.alura.literatura.Repositorio.AutorRepositorio;
import com.alura.literatura.Repositorio.LibroRepositorio;
import com.alura.literatura.Service.GutendexAPI;
import com.alura.literatura.Service.ObtenerDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private String URL_BASE = "https://gutendex.com/books/"; //https://gutendex.com/books/?search=don+quijote
    private GutendexAPI gutendex = new GutendexAPI();
    private ObtenerDatos datos = new ObtenerDatos();
    private LibroRepositorio libroRepositorio;
    private AutorRepositorio autorRepositorio;

    public Principal(LibroRepositorio libroRepositorio, AutorRepositorio autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                ------------------------
                1- Buscar libro por título
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listrar autores vivos en un determiando año
                5- Listar libros por idioma
                0- Salir
                """;
            System.out.println("\n"+menu);
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion){
                case 1:
                    buscarTituloPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnUnDeterminadoAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Salió del sistema...");
                    break;
                default:
                    System.out.println("\nOpción inválida");
                    break;
            }
        }
    }

    private void buscarTituloPorTitulo() {
        System.out.print("\nIngrese el nombre del libro a buscar: ");
        String busqueda = sc.nextLine();

        Optional<String> response = gutendex.getDatos(URL_BASE+"?search="+busqueda.toLowerCase().replace(" ","+"));
        if(response.isPresent()) {
            String json = response.get();
            var libros = datos.covertirDatos(json, DatosLibro.class);
            if(!libros.results().isEmpty()){
                Optional<Libro> libro = libros.results().stream().map(Libro::new).findFirst();
                Libro libroEncontrado = libro.get();

                List<Autor> autores = libros.results().stream().findFirst().get().autores().stream()
                        .map(datosAutor -> {
                            Optional<Autor> autor = autorRepositorio.findByNombreAndAnioNacimiento(datosAutor.nombre(),datosAutor.anioNacimiento());

                            if(autor.isPresent()) {
                                System.out.println("\nNo se puede registar el mismo libro más de una vez\n");
                            }

                            return autor.orElseGet(()->new Autor(datosAutor));
                        }).collect(Collectors.toList());

                libroEncontrado.setAutores(autores);
                libroRepositorio.save(libroEncontrado);
                System.out.println("\n"+libroEncontrado);
            }else{
                System.out.println("\nLibro no encontrado");
                System.out.println("------------------");
            }
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepositorio.findAll();
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = libroRepositorio.listarAutoresRegistrados();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosEnUnDeterminadoAnio() {
        System.out.print("\nIngrese el año vivo de autor(es) que desea buscar: ");
        int anio = sc.nextInt();
        sc.nextLine();
        List<Autor> autores = libroRepositorio.listarAutoresVivosEnUnDeterminadoAnio(anio);
        autores.forEach(System.out::println);
    }

    private void listarLibrosPorIdioma() {
        String idiomas = """
                Ingrese el idioma para buscar los libros:
                es- español
                en- inglés
                fr- francia
                pt- portugués
                """;
        String opcion = "";

        while (!(opcion.equalsIgnoreCase("es") || opcion.equalsIgnoreCase("en")
                || opcion.equalsIgnoreCase("fr") || opcion.equalsIgnoreCase("pt"))){
            System.out.println("\n"+idiomas);
            System.out.print("Opción: ");
            opcion = sc.nextLine();

            List<Libro> libros = libroRepositorio.listarLibrosPorIdioma(Idioma.value(opcion));
            libros.forEach(System.out::println);
        }
    }
}
