package com.example.demo.entity.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * DTO para el token JWT.
 */
public class JwtDto implements RespuestaDto {
    private String token;
    private String bearer = "Bearer";
    private String userName;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor para JwtDto.
     *
     * @param token         El token JWT.
     * @param nombreUsuario El nombre de usuario.
     * @param authorities   Las autoridades del usuario.
     */
    public JwtDto(String token, String userName, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.userName = userName;
        this.authorities = authorities;
    }

    /**
     * Obtiene el token JWT.
     *
     * @return El token JWT.
     */
    public String getToken() {
        return token;
    }

    /**
     * Establece el token JWT.
     *
     * @param token El token JWT.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Obtiene el tipo de token (Bearer).
     *
     * @return El tipo de token.
     */
    public String getBearer() {
        return bearer;
    }

    /**
     * Establece el tipo de token (Bearer).
     *
     * @param bearer El tipo de token.
     */
    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getNombreUsuario() {
        return userName;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombreUsuario El nombre de usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.userName = nombreUsuario;
    }

    /**
     * Obtiene las autoridades del usuario.
     *
     * @return Las autoridades del usuario.
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Establece las autoridades del usuario.
     *
     * @param authorities Las autoridades del usuario.
     */
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}