package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

/**
 * Servicio para manejar las operaciones relacionadas con los usuarios.
 */
@Service
public class UserService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;
    /**
     * Carga un usuario por su nombre de usuario.
     *
     * @param username El nombre de usuario.
     * @return Los detalles del usuario.
     * @throws UsernameNotFoundException si el usuario no se encuentra.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario por nombre de usuario en el repositorio
        User user = userRepository.findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        // Crear y devolver un objeto UserDetails con el nombre de usuario, contraseña y rol del usuario
        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return Una lista de todos los usuarios.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Guarda un usuario.
     *
     * @param user El usuario a guardar.
     * @return El usuario guardado.
     */
    public User saveUser(User user) {
    	Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El nombre de usuario ya está en uso");
        }
        return userRepository.save(user);
    }

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param userName El nombre de usuario.
     * @return Un Optional que contiene el usuario encontrado, o vacío si no se encuentra.
     */
    public Optional<User> getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    
 // Actualizar un usuario
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Eliminar un usuario
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}