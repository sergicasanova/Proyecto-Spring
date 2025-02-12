package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en el sistema")
    @ApiResponse(responseCode = "201", description = "Usuario creado con éxito")
    @ApiResponse(responseCode = "401", description = "Error al crear el usuario")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            // Encriptar la contraseña antes de pasársela al servicio
            logger.info("Intentando crear usuario: {}", user);
            
            // Encriptamos la contraseña
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            // Llamamos al servicio para guardar el usuario
            User savedUser = userService.saveUser(user);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);  // Usuario creado con éxito (201)
        } catch (Exception e) {
            logger.error("Error al crear el usuario: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    /**
     * Endpoint para obtener la lista de todos los usuarios.
     *
     * @return ResponseEntity con la lista de usuarios y el estado HTTP OK.
     */
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve la lista de usuarios")
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
    
 // Actualizar un usuario
    @Operation(summary = "Actualizar un usuario", description = "Actualiza los datos de un usuario existente")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado con éxito")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    // Eliminar un usuario
    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario existente del sistema")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado con éxito")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // Verificar si el usuario tiene el rol de ADMIN
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Solo los usuarios con rol ADMIN pueden eliminar usuarios
        if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            userService.deleteUser(id);
        } else {
            throw new RuntimeException("No tienes permisos para eliminar este usuario");
        }
    }
}