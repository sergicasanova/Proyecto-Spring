package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @ApiResponse(responseCode = "200", description = "Libros obtenidos con éxito")
    @GetMapping
    public List<Libro> getAllLibros() { 
        return libroService.getAllLibros();
    }

    // Buscar libros por título
    @Operation(summary = "Buscar libros por título", description = "Devuelve una lista de libros que contienen el título especificado")
    @ApiResponse(responseCode = "200", description = "Libros encontrados con éxito")
    @GetMapping("/search")
    public List<Libro> searchBooksByTitle(@RequestParam String title) {
        return libroService.searchBooksByTitle(title);
    }
    
    // Buscar libros por autor
    @Operation(summary = "Buscar libros por autor", description = "Devuelve una lista de libros que contienen el autor especificado")
    @ApiResponse(responseCode = "200", description = "Libros encontrados con éxito")
    @GetMapping("/searchByAuthor")
    public List<Libro> searchBooksByAuthor(@RequestParam String author) {
        return libroService.searchBooksByAuthor(author);
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
    @ApiResponse(responseCode = "201", description = "Libro creado con éxito")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Libro createLibro(@RequestBody Libro libro) {
            return libroService.createLibro(libro); 
    }

    // Actualizar un libro
    @Operation(summary = "Actualizar un libro", description = "Actualiza los detalles de un libro existente")
    @ApiResponse(responseCode = "200", description = "Libro actualizado con éxito")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Libro updateLibro(@PathVariable Long id, @RequestBody Libro libro) {
            return libroService.updateLibro(id, libro);
    }

    // Eliminar un libro
    @Operation(summary = "Eliminar un libro", description = "Elimina un libro específico por su ID del sistema")
    @ApiResponse(responseCode = "204", description = "Libro eliminado con éxito")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteLibro(@PathVariable Long id) {
            libroService.deleteLibro(id);
    }
    
    @Operation(summary = "Prestar un libro a un usuario", description = "Permite a un usuario tomar prestado un libro")
    @ApiResponse(responseCode = "200", description = "Libro prestado con éxito")
    @ApiResponse(responseCode = "400", description = "El libro ya está prestado")
    @PostMapping("/borrow/{userId}/{libroId}")
    public Libro borrowBook(@PathVariable Long userId, @PathVariable Long libroId) {
        return libroService.borrowBook(userId, libroId);
    }
    
    @Operation(summary = "Devolver un libro a la biblioteca", description = "Permite a un usuario devolver un libro a la biblioteca")
    @ApiResponse(responseCode = "200", description = "Libro devuelto con éxito")
    @ApiResponse(responseCode = "400", description = "El libro no está prestado o no pertenece al usuario")
    @PutMapping("/return/{userId}/{libroId}")
    public Libro returnBook(@PathVariable Long userId, @PathVariable Long libroId) {
        return libroService.returnBook(userId, libroId);
    }
}
