package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Libro;
import com.example.demo.repository.BibliotecaRepository;
import com.example.demo.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    
    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    // Obtener todos los libros
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    // Obtener un libro por ID
    public Optional<Libro> getLibroById(Long id) {
        return libroRepository.findById(id);
    }

    // Crear un libro
    public Libro createLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // Actualizar un libro
    public Libro updateLibro(Long id, Libro libro) {
        libro.setId(id);
        return libroRepository.save(libro);
    }

    // Eliminar un libro
    public void deleteLibro(Long id) {
        libroRepository.deleteById(id);
    }

    // Agregar un libro a una biblioteca
    public Libro addLibroToLibrary(Long libraryId, Libro libro) {
        libro.setLibrary(bibliotecaRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found")));
        return libroRepository.save(libro);
    }
}
