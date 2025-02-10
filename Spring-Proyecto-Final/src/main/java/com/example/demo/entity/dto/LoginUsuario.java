package com.example.demo.entity.dto;

/**
 * DTO para los datos de inicio de sesión del usuario.
 */
public class LoginUsuario {

    // Nombre de usuario
    private String userName;

    // Contraseña del usuario
    private String password;

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUserName() { // Método getter debe ser consistente
        return userName;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombreUsuario El nombre de usuario.
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
}
