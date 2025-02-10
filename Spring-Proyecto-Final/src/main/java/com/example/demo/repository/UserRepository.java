package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

/**
 * Consultas personalizadas: Las consultas personalizadas te permiten tener control total 
 * sobre cómo se recuperan los datos. En este caso, consultas como findUsersByRole y findUsersByUserNameContaining 
 * son ejemplos de cómo puedes hacer consultas más específicas sin tener que escribir SQL directamente.
 * */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findUsersByRole(@Param("role") String role);

    @Query("SELECT u FROM User u WHERE u.userName LIKE %:userName%")
    List<User> findUsersByUserNameContaining(@Param("userName") String userName);
    
    @Query(value = "SELECT * FROM users WHERE role = ?1", nativeQuery = true)
    List<User> findUsersByRoleNative(String role);
    
    /**
     * Encuentra un usuario por su identificador.
     *
     * @param id El identificador del usuario.
     * @return El usuario encontrado.
     */
    public User findById(long id);
    
    /**
     * Encuentra un usuario por su nombre de usuario.
     *
     * @param userName El nombre de usuario.
     * @return Un Optional que contiene el usuario encontrado, o vacío si no se encuentra.
     */
    public Optional<User> findByUserName(String userName);
}

