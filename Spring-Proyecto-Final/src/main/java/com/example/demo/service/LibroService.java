package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Libro;
import com.example.demo.entity.User;
import com.example.demo.repository.BibliotecaRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.UserRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    
    @Autowired
    private BibliotecaRepository bibliotecaRepository;
    
    @Autowired
    private UserRepository userRepository;

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

    // Buscar libros por título
    public List<Libro> searchBooksByTitle(String title) {
        return libroRepository.findByTitleContainingIgnoreCase(title);
    }
    
    // Buscar libros por autor
    public List<Libro> searchBooksByAuthor(String author) {
        return libroRepository.findByAuthorContainingIgnoreCase(author);
    }
    
 // Prestar un libro a un usuario
    public Libro borrowBook(Long userId, Long libroId) {
        // Obtener el libro y usuario
        Libro libro = libroRepository.findById(libroId).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // Si el libro no está prestado, marcarlo como prestado
        if (!libro.isPrestado()) {
            libro.setPrestado(true);
            user.getBooks().add(libro); // Añadir el libro a la lista de libros del usuario
            libroRepository.save(libro);  // Guardar el libro con el estado actualizado
            userRepository.save(user);   // Guardar el usuario con el libro prestado
            return libro;
        } else {
            throw new RuntimeException("El libro ya está prestado.");
        }
    }
    
    // Devolver un libro a la biblioteca
    public Libro returnBook(Long userId, Long libroId) {
        // Obtener el libro y usuario
        Libro libro = libroRepository.findById(libroId).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // Verificar si el libro está prestado
        if (libro.isPrestado()) {
            // Marcar el libro como no prestado
            libro.setPrestado(false);
            
            // Eliminar el libro de la lista de libros prestados del usuario
            user.getBooks().remove(libro);
            
            // Guardar el libro con el estado actualizado
            libroRepository.save(libro);
            
            // Guardar el usuario con la lista de libros actualizada
            userRepository.save(user);
            
            return libro;
        } else {
            throw new RuntimeException("El libro no está prestado o no pertenece al usuario");
        }
    }
}
