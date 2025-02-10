package com.example.demo.controller;

import com.example.demo.entity.Biblioteca;
import com.example.demo.entity.Libro;
import com.example.demo.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bibliotecas")
public class BibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    // Obtener todas las bibliotecas
    @GetMapping
    public List<Biblioteca> getAllBibliotecas() {
        return bibliotecaService.getAllBibliotecas();
    }

    // Obtener una biblioteca por ID
    @GetMapping("/{id}")
    public Optional<Biblioteca> getBibliotecaById(@PathVariable Long id) {
        return bibliotecaService.getBibliotecaById(id);
    }

    // Crear una biblioteca
    @PostMapping
    public Biblioteca createBiblioteca(@RequestBody Biblioteca biblioteca) {
        return bibliotecaService.createBiblioteca(biblioteca);
    }

    // Actualizar una biblioteca
    @PutMapping("/{id}")
    public Biblioteca updateBiblioteca(@PathVariable Long id, @RequestBody Biblioteca biblioteca) {
        return bibliotecaService.updateBiblioteca(id, biblioteca);
    }

    // Eliminar una biblioteca
    @DeleteMapping("/{id}")
    public void deleteBiblioteca(@PathVariable Long id) {
        bibliotecaService.deleteBiblioteca(id);
    }

    // Agregar un libro a una biblioteca
    @PostMapping("/{bibliotecaId}/libros")
    public Biblioteca addLibroToBiblioteca(@PathVariable Long bibliotecaId, @RequestBody Libro libro) {
        return bibliotecaService.addLibroToBiblioteca(bibliotecaId, libro);
    }
}
