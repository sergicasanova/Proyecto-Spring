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

import com.example.demo.entity.Libro;
import com.example.demo.service.LibroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Obtener todos los libros
    @Operation(summary = "Obtener todos los libros", description = "Devuelve la lista de todos los libros en el sistema")
    @ApiResponse(responseCode = "200", description = "Libros obtenidos con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
    @GetMapping
    public List<Libro> getAllLibros() { 
        return libroService.getAllLibros();
    }

    // Obtener un libro por ID
    @Operation(summary = "Obtener un libro por ID", description = "Devuelve los detalles de un libro específico por su ID")
    @ApiResponse(responseCode = "200", description = "Libro encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
    @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    @GetMapping("/{id}")
    public Optional<Libro> getLibroById(@PathVariable Long id) {
        return libroService.getLibroById(id); 
    }

    // Crear un libro
    @Operation(summary = "Crear un nuevo libro", description = "Crea un libro en el sistema con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Libro creado con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
    @ApiResponse(responseCode = "400", description = "Error de validación de datos")
    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) { 
        return libroService.createLibro(libro); 
    }

    // Actualizar un libro
    @Operation(summary = "Actualizar un libro", description = "Actualiza los detalles de un libro existente")
    @ApiResponse(responseCode = "200", description = "Libro actualizado con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
    @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    @ApiResponse(responseCode = "400", description = "Error de validación de datos")
    @PutMapping("/{id}")
    public Libro updateLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.updateLibro(id, libro);
    }

    // Eliminar un libro
    @Operation(summary = "Eliminar un libro", description = "Elimina un libro específico por su ID del sistema")
    @ApiResponse(responseCode = "204", description = "Libro eliminado con éxito")
    @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) { 
        libroService.deleteLibro(id);
    }

    // Agregar un libro a una biblioteca
    @Operation(summary = "Agregar un libro a una biblioteca", description = "Asocia un libro a una biblioteca específica")
    @ApiResponse(responseCode = "200", description = "Libro agregado a la biblioteca con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
    @ApiResponse(responseCode = "404", description = "Biblioteca no encontrada")
    @ApiResponse(responseCode = "400", description = "Error al agregar el libro a la biblioteca")
    @PostMapping("/{libraryId}")
    public Libro addLibroToLibrary(@PathVariable Long libraryId, @RequestBody Libro libro) {
        return libroService.addLibroToLibrary(libraryId, libro);
    }
}
