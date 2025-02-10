package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Entity
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la biblioteca", example = "1")
    private Long id;

    @Schema(description = "Nombre de la biblioteca", example = "Biblioteca Nacional", required = true, minLength = 3, maxLength = 255)
    private String name;

    @OneToMany(mappedBy = "biblioteca")
    @Schema(description = "Lista de libros pertenecientes a la biblioteca", required = false)
    private List<Libro> libros;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
