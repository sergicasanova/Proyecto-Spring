package com.example.demo.entity;

import java.awt.print.Book;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/*
 * 
 * 	AUTO: JPA elige automáticamente la estrategia de generación de claves más adecuada para la base de datos.
	IDENTITY: Utiliza una columna de identidad en la base de datos para generar las claves primarias.
	SEQUENCE: Utiliza una secuencia en la base de datos para generar las claves primarias.
	TABLE: Utiliza una tabla especial en la base de datos para generar las claves primarias.
 * */
@Entity
public class User {
	// Identificador único del usuario
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // Nombre de usuario
    // Implementamos que no pueda ser null y un minimo y maximo de caracteres
    private String userName;
    
    // Contraseña del usuario
    // Implementada un minimo de 6 caracteres y imposibilita el que sea null
    private String password;
    
    // Rol del usuario
    private String role; 
    
    @ManyToMany  // Relación muchos a muchos con libros
    private List<Book> books;

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Establece el identificador único del usuario.
     *
     * @param id El identificador único del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param userName El nombre de usuario.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return El rol del usuario.
     */
    public String getRole() {
    return role;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param role El rol del usuario.
     */
    public void setRole(String role) {
        this.role = role;
    }
    
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
