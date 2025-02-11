package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Biblioteca;
import com.example.demo.entity.Libro;
import com.example.demo.repository.BibliotecaRepository;
import com.example.demo.repository.LibroRepository;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;
    
    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos los libros
    public List<Biblioteca> getAllBibliotecas() {  
        return bibliotecaRepository.findAll();  
    }

    // Obtener una biblioteca por ID
    public Optional<Biblioteca> getBibliotecaById(Long id) {  
        return bibliotecaRepository.findById(id);
    }

    // Crear una biblioteca
    public Biblioteca createBiblioteca(Biblioteca biblioteca) {  
        return bibliotecaRepository.save(biblioteca);  
    }

    // Actualizar una biblioteca
    public Biblioteca updateBiblioteca(Long id, Biblioteca biblioteca) {  
        biblioteca.setId(id);
        return bibliotecaRepository.save(biblioteca);  
    }

    // Eliminar una biblioteca
    public void deleteBiblioteca(Long id) {  
        bibliotecaRepository.deleteById(id); 
    }

 // Agregar un libro a una biblioteca usando solo los IDs
    public Biblioteca addLibroToBiblioteca(Long bibliotecaId, Long libroId) {
        // Buscar la biblioteca por ID
        Biblioteca biblioteca = bibliotecaRepository.findById(bibliotecaId)
                .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada"));

        // Buscar el libro por ID
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        // Asociar el libro con la biblioteca
        libro.setBiblioteca(biblioteca);

        // Guardamos el libro en la base de datos
        libroRepository.save(libro);

        // Devolvemos la biblioteca con la lista de libros actualizada
        return biblioteca;
    }
    
 // Obtener todos los libros de una biblioteca
    public List<Libro> getLibrosDeBiblioteca(Long bibliotecaId) {
        // Buscar la biblioteca por ID
        Biblioteca biblioteca = bibliotecaRepository.findById(bibliotecaId)
                .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada"));

        // Retornar los libros de esa biblioteca
        return biblioteca.getLibros();
    }
}
