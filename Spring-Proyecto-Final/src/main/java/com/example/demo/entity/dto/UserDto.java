package com.example.demo.entity.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDto {

    @Schema(description = "Identificador único del usuario")
    private Long id;

    @Schema(description = "Nombre de usuario", example = "john_doe", required = true, minLength = 3, maxLength = 50)
    private String userName;
    
    @Schema(description = "Contraseña", example = "password123", required = true)
    private String password;

    @Schema(description = "Rol del usuario", example = "ADMIN", required = true)
    private String role;

    @Schema(description = "Lista de libros asociados al usuario", required = false)
    private List<Long> libroIds;
    

    // Constructor vacío
    public UserDto() {
    }

    // Constructor con parámetros
    public UserDto(Long id, String userName, String role, List<Long> libroIds) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.libroIds = libroIds;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Long> getLibroIds() {
        return libroIds;
    }

    public void setLibroIds(List<Long> libroIds) {
        this.libroIds = libroIds;
    }

	public String getPassword(String password) {
		return password;
	}
	
	public void setPassword(String password) {
        this.password = password;
    }
}
