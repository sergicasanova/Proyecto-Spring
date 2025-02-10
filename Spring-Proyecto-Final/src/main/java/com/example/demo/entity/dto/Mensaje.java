package com.example.demo.entity.dto;

/**
 * DTO para mensajes de respuesta.
 */
public class Mensaje implements RespuestaDto {

    // El mensaje de respuesta
    private String mensaje;

    /**
     * Constructor para Mensaje.
     *
     * @param mensaje El mensaje de respuesta.
     */
    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el mensaje de respuesta.
     *
     * @return El mensaje de respuesta.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje de respuesta.
     *
     * @param mensaje El mensaje de respuesta.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}