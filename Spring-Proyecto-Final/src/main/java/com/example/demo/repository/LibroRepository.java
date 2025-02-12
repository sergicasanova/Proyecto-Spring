package com.example.demo.repository;

import com.example.demo.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros por título, ignorando mayúsculas/minúsculas
    List<Libro> findByTitleContainingIgnoreCase(String title);
    
 // Buscar libros por autor, ignorando mayúsculas/minúsculas
    List<Libro> findByAuthorContainingIgnoreCase(String author);
}
