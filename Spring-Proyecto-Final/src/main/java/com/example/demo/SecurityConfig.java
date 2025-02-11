package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtEntryPoint;

/**
 * Clase de configuración de seguridad. Se definen las reglas de seguridad.
 * SecurityConfig
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Inyección de dependencias para manejar errores de autenticación
    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    // Definición del filtro de autenticación JWT como un bean
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    /**
     * Configuración de la cadena de filtros de seguridad.
     *
     * @param http Objeto HttpSecurity para configurar la seguridad HTTP.
     * @return SecurityFilterChain configurado.
     * @throws Exception en caso de error en la configuración de seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desactivar CSRF para simplificar la configuración
            .csrf(csrf -> csrf.disable())
            // Configuración de las reglas de autorización
            .authorizeHttpRequests(auth -> auth
                // Permitir acceso a rutas públicas
                .requestMatchers("/public/**").permitAll()
                // Permitir acceso a rutas de autenticación
                .requestMatchers("/auth/**").permitAll()
//                .requestMatchers("/users/**").permitAll()
//                .requestMatchers("/bibliotecas/**").permitAll()
//                .requestMatchers("/libros/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui.html").permitAll()
                // Requerir autenticación para cualquier otra ruta
                .anyRequest().authenticated()
            )
            // Manejar errores de autenticación con JwtEntryPoint
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtEntryPoint)
            )
            // Añadir el filtro de autenticación JWT antes del filtro de autenticación de usuario y contraseña
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            // Configuración de la página de inicio de sesión personalizada (opcional)
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            );
        return http.build();
    }

    /**
     * Definición del bean AuthenticationManager para manejar la autenticación.
     *
     * @param authenticationConfiguration Objeto AuthenticationConfiguration para configurar el AuthenticationManager.
     * @return AuthenticationManager configurado.
     * @throws Exception en caso de error en la configuración del AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Definición del bean PasswordEncoder para codificar las contraseñas.
     *
     * @return PasswordEncoder configurado.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
    	System.out.println("passwordEncoder bean creado.");
        return new BCryptPasswordEncoder();
    }
}