# 📚 Proyecto Literatura - API de Libros y Autores

Este proyecto es una aplicación Java desarrollada con **Spring Boot** y **JPA** que permite consultar libros y autores desde una API externa (Gutendex) y almacenarlos en una base de datos relacional.

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- SQL Server
- Maven
- IntelliJ IDEA

## 🌐 API utilizada

Se consume la API pública de [Gutendex](https://gutendex.com/) para obtener información sobre libros, autores, idiomas y descargas.

## 🧠 Funcionalidades principales

La aplicación ofrece un menú de consola interactivo con las siguientes opciones:

1. 🔎 **Buscar libro por título**  
   Permite buscar un libro por su título en la API de Gutendex. Si no existe previamente en la base de datos, se registra junto con sus autores.

2. 📖 **Listar libros registrados**  
   Muestra todos los libros que han sido guardados en la base de datos.

3. 🧑‍💼 **Listar autores registrados**  
   Lista todos los autores guardados, junto con su información biográfica y los libros que escribió.

4. 🎯 **Listar autores vivos en un año determinado**  
   Permite ingresar un año y ver qué autores estaban vivos en ese momento.

5. 🌍 **Listar libros por idioma**  
   Filtra los libros registrados en la base de datos por su idioma (español, inglés, etc.).

## Autor

- [@davidYangua](https://github.com/davidYangua)
