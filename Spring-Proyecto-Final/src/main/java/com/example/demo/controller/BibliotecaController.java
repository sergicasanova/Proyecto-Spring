package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Biblioteca;
import com.example.demo.entity.Libro;
import com.example.demo.service.BibliotecaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/bibliotecas")
public class BibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    // Obtener todas las bibliotecas
    @GetMapping
    @Operation(summary = "Obtener todas las bibliotecas", description = "Devuelve la lista de todas las bibliotecas en el sistema")
    @ApiResponse(responseCode = "200", description = "Bibliotecas obtenidas con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Biblioteca.class)))
    @ApiResponse(responseCode = "500", description = "Error al obtener las bibliotecas")
    public List<Biblioteca> getAllBibliotecas() {
        return bibliotecaService.getAllBibliotecas();
    }

    // Obtener una biblioteca por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una biblioteca por ID", description = "Devuelve los detalles de una biblioteca específica por su ID")
    @ApiResponse(responseCode = "200", description = "Biblioteca encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Biblioteca.class)))
    @ApiResponse(responseCode = "404", description = "Biblioteca no encontrada")
    public Optional<Biblioteca> getBibliotecaById(@PathVariable Long id) {
        return bibliotecaService.getBibliotecaById(id);
    }

    // Crear una biblioteca
    @PostMapping
    @Operation(summary = "Crear una nueva biblioteca", description = "Crea una biblioteca en el sistema con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Biblioteca creada con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Biblioteca.class)))
    @ApiResponse(responseCode = "400", description = "Error de validación de datos")
    public Biblioteca createBiblioteca(@RequestBody Biblioteca biblioteca) {
        return bibliotecaService.createBiblioteca(biblioteca);
    }

    // Actualizar una biblioteca
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una biblioteca", description = "Actualiza los detalles de una biblioteca existente")
    @ApiResponse(responseCode = "200", description = "Biblioteca actualizada con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Biblioteca.class)))
    @ApiResponse(responseCode = "404", description = "Biblioteca no encontrada")
    @ApiResponse(responseCode = "400", description = "Error de validación de datos")
    public Biblioteca updateBiblioteca(@PathVariable Long id, @RequestBody Biblioteca biblioteca) {
        return bibliotecaService.updateBiblioteca(id, biblioteca);
    }

    // Eliminar una biblioteca
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una biblioteca", description = "Elimina una biblioteca específica por su ID del sistema")
    @ApiResponse(responseCode = "204", description = "Biblioteca eliminada con éxito")
    @ApiResponse(responseCode = "404", description = "Biblioteca no encontrada")
    public void deleteBiblioteca(@PathVariable Long id) {
        bibliotecaService.deleteBiblioteca(id);
    }

    // Agregar un libro a una biblioteca
    @PostMapping("/{libraryId}/libros/{bookId}")
    @Operation(summary = "Agregar un libro a una biblioteca", description = "Asocia un libro a una biblioteca específica")
    @ApiResponse(responseCode = "200", description = "Libro agregado a la biblioteca con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Biblioteca.class)))
    @ApiResponse(responseCode = "404", description = "Biblioteca no encontrada")
    @ApiResponse(responseCode = "400", description = "Error al agregar el libro a la biblioteca")
    public Biblioteca addLibroToBiblioteca(@PathVariable Long libraryId, @PathVariable Long bookId) {
        return bibliotecaService.addLibroToBiblioteca(libraryId, bookId);
    }
    
    @GetMapping("/{id}/libros")
    @Operation(summary = "Obtener todos los libros de una biblioteca", description = "Devuelve todos los libros de una biblioteca, incluidos los datos completos de los libros.")
    @ApiResponse(responseCode = "200", description = "Libros obtenidos con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
    @ApiResponse(responseCode = "404", description = "Biblioteca no encontrada")
    public List<Libro> getLibrosDeBiblioteca(@PathVariable Long id) {
        return bibliotecaService.getLibrosDeBiblioteca(id);
    }
}
