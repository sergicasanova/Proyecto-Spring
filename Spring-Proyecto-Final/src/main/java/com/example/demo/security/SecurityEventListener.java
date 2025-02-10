package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class SecurityEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SecurityEventListener.class);

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        logger.info("Inicio de sesión exitoso para el usuario: {}", event.getAuthentication().getName());
    }

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        logger.warn("Intento de inicio de sesión fallido con usuario: {}", event.getAuthentication().getName());
    }
}