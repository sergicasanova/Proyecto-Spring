package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Biblioteca;
import com.example.demo.entity.Libro;
import com.example.demo.repository.BibliotecaRepository;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

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

    // Agregar un libro a una biblioteca
    public Biblioteca addLibroToBiblioteca(Long bibliotecaId, Libro libro) { 
        Biblioteca biblioteca = bibliotecaRepository.findById(bibliotecaId)
                .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada"));
        libro.setBiblioteca(biblioteca);
        return bibliotecaRepository.save(biblioteca);  
    }
}
