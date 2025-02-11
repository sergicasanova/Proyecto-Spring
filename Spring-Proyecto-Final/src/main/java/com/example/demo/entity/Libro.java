package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del libro se autogenera")
    private Long id;

    @Schema(description = "Título del libro", example = "El Gran Gatsby", required = true, minLength = 3, maxLength = 255)
    private String title;

    @Schema(description = "Autor del libro", example = "F. Scott Fitzgerald", required = true)
    private String author;

    @Schema(description = "Género del libro", example = "Ficción", required = false)
    private String genre;

    @ManyToOne
    @JsonBackReference 
    @JoinColumn(name = "biblioteca_id")// Relación muchos a uno con Biblioteca
    private Biblioteca biblioteca;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
