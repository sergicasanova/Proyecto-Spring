package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la biblioteca se autogenera")
    private Long id;

    @Schema(description = "Nombre de la biblioteca", example = "Biblioteca Nacional", required = true, minLength = 3, maxLength = 255)
    private String name;

    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL)
    @JsonManagedReference
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
