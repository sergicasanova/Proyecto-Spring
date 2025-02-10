package com.example.demo.security;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Utilidades de seguridad para obtener información de las solicitudes HTTP.
 */
public class SecurityUtils {

    /**
     * Obtiene la IP del cliente desde la solicitud HTTP.
     *
     * @param request El objeto HttpServletRequest.
     * @return La IP del cliente.
     */
    public static String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Real-IP");
        if (xfHeader == null) {
            // logger.error("Cabecera X-Real-IP con valor null.");
            return request.getRemoteAddr();
        }
        // logger.info("Cabecera X-Real-IP con valor: " + xfHeader);
        return xfHeader.split(",")[0];
    }

    /**
     * Obtiene la URL completa de la solicitud HTTP.
     *
     * @param request El objeto HttpServletRequest.
     * @return La URL completa de la solicitud.
     */
    public static String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

    /**
     * Obtiene el token JWT de la cabecera de autorización de la solicitud HTTP.
     *
     * @param request El objeto HttpServletRequest.
     * @return El token JWT si está presente, de lo contrario null.
     */
    public static String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
}