package com.example.demo.entity;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Identificador único del usuario")
    private Long id;
    
    // Nombre de usuario
    // Implementamos que no pueda ser null y un minimo y maximo de caracteres
    @Schema(description = "Nombre de usuario", example = "john_doe", required = true, minLength = 3, maxLength = 50)
    private String userName;
    
    // Contraseña del usuario
    // Implementada un minimo de 6 caracteres y imposibilita el que sea null
    @Schema(description = "Contraseña del usuario", example = "Password123", required = true, minLength = 6)
    private String password;
    
    // Rol del usuario
    @Schema(description = "Rol del usuario", example = "ADMIN", required = true)
    private String role; 
    
    @ManyToMany  // Relación muchos a muchos con libros
    @Schema(description = "Lista de libros asociados al usuario", required = false)
    private List<Libro> libro;

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
    
    public List<Libro> getBooks() {
        return libro;
    }

    public void setBooks(List<Libro> libro) {
        this.libro = libro;
    }
}
