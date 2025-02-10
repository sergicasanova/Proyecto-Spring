package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.JwtDto;
import com.example.demo.entity.dto.LoginUsuario;
import com.example.demo.entity.dto.Mensaje;
import com.example.demo.entity.dto.RespuestaDto;
import com.example.demo.security.JwtProvider;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controlador de autenticación que maneja las solicitudes de inicio de sesión y generación de JWT.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    // Inyección de dependencias para manejar la autenticación
    @Autowired
    private AuthenticationManager authenticationManager;

    // Inyección de dependencias para manejar la generación de JWT
    @Autowired
    private JwtProvider jwtProvider;

    // Inyección de dependencias para manejar los servicios de usuario
    @Autowired
    private UserService userService;

    /**
     * Endpoint para el login.
     *
     * @param loginUsuario Objeto que contiene el nombre de usuario y la contraseña.
     * @param request      Objeto HttpServletRequest para obtener información de la solicitud.
     * @return ResponseEntity con el JWT y los detalles del usuario, o un mensaje de error.
     */
    @Operation(summary = "Iniciar sesión de un usuario", description = "Autentica al usuario con su nombre de usuario y contraseña, y genera un JWT.")
    @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso. Devuelve el JWT generado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtDto.class)))
    @ApiResponse(responseCode = "400", description = "Usuario o contraseña incorrectos.")
    @PostMapping("/login")
    public ResponseEntity<RespuestaDto> login(@RequestBody LoginUsuario loginUsuario, HttpServletRequest request){

        System.out.println("Datos de login recibidos: " + userService.getByUserName(loginUsuario.getUserName()) + ", " + loginUsuario.getPassword());
    	
        // Obtener la IP del cliente y la URL de la solicitud
        String ip = SecurityUtils.getClientIP(request);
        String urlRequest = SecurityUtils.getFullURL(request);

        // Buscar el usuario por nombre de usuario
        Optional<User> optUser = userService.getByUserName(loginUsuario.getUserName());

        // Si el usuario no existe, devolver un error
        if(optUser.isEmpty()) {
            System.err.println("Error de autenticación. IP: " + ip + ". Request URL: " + urlRequest);
            return new ResponseEntity<>(new Mensaje("Usuario o password incorrectos."), HttpStatus.BAD_REQUEST);
        }

        // Autenticar al usuario
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginUsuario.getUserName(), loginUsuario.getPassword())
        );

        // Establecer la autenticación en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generar el token JWT
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Crear el DTO del JWT
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        // Devolver la respuesta con el JWT
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);

    }
}