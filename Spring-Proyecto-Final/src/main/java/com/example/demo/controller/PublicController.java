package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Controlador público que maneja las solicitudes accesibles sin autenticación.
 */
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Endpoint para obtener la lista de todos los usuarios.
     *
     * @return ResponseEntity con la lista de usuarios y el estado HTTP OK.
     */
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve la lista de todos los usuarios registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Usuarios obtenidos con éxito")
    @ApiResponse(responseCode = "401", description = "Error al obtener los usuarios")
    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        // Obtener la lista de todos los usuarios
        List<User> usuarios = userService.getAllUsers();
        // Devolver la lista de usuarios con el estado HTTP OK
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarios);
    }

    /**
     * Endpoint para crear un nuevo usuario aleatorio y devolver la lista actualizada de usuarios.
     * Este método es solo para pruebas y nos permite crear usuarios si no tenemos ninguno al iniciar
     * la aplicación por primera vez
     *
     * @return ResponseEntity con la lista actualizada de usuarios y el estado HTTP OK.
     */
    @Operation(summary = "Crear un nuevo usuario aleatorio", description = "Crea un usuario aleatorio para pruebas y devuelve la lista actualizada de usuarios")
    @ApiResponse(responseCode = "200", description = "Nuevo usuario creado con éxito")
    @ApiResponse(responseCode = "401", description = "Error al crear el nuevo usuario")
    @GetMapping("/newrandomuser")
    public ResponseEntity<List<User>> newRandomUser() {
        // Crear un nuevo usuario con un nombre de usuario aleatorio y una contraseña codificada
        User user = new User();
        user.setUserName("user" + (int)(Math.random() * 1000));
        user.setPassword(passwordEncoder.encode("password"));
        user.setRole("USER");

        // Guardar el nuevo usuario en la base de datos
        userService.saveUser(user);

        // Obtener la lista actualizada de todos los usuarios
        List<User> usuarios = userService.getAllUsers();
        // Devolver la lista actualizada de usuarios con el estado HTTP OK
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarios);
    }
}

