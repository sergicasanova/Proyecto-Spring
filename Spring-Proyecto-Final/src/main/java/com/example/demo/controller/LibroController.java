package com.example.demo.controller;

import com.example.demo.entity.Libro;  // Cambio de Book a Libro
import com.example.demo.service.LibroService;  // Cambio de BookService a LibroService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroController {  // Cambio de BookController a LibroController

    @Autowired
    private LibroService libroService;  // Cambio de BookService a LibroService

    // Obtener todos los libros
    @GetMapping
    public List<Libro> getAllLibros() {  // Cambio de getAllBooks a getAllLibros
        return libroService.getAllLibros();  // Cambio de BookService a LibroService
    }

    // Obtener un libro por ID
    @GetMapping("/{id}")
    public Optional<Libro> getLibroById(@PathVariable Long id) {  // Cambio de Book a Libro
        return libroService.getLibroById(id);  // Cambio de BookService a LibroService
    }

    // Crear un libro
    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {  // Cambio de Book a Libro
        return libroService.createLibro(libro);  // Cambio de BookService a LibroService
    }

    // Actualizar un libro
    @PutMapping("/{id}")
    public Libro updateLibro(@PathVariable Long id, @RequestBody Libro libro) {  // Cambio de Book a Libro
        return libroService.updateLibro(id, libro);  // Cambio de BookService a LibroService
    }

    // Eliminar un libro
    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) {  // Cambio de Book a Libro
        libroService.deleteLibro(id);  // Cambio de BookService a LibroService
    }

    // Agregar un libro a una biblioteca
    @PostMapping("/{libraryId}")
    public Libro addLibroToLibrary(@PathVariable Long libraryId, @RequestBody Libro libro) {  // Cambio de Book a Libro
        return libroService.addLibroToLibrary(libraryId, libro);  // Cambio de BookService a LibroService
    }
}
