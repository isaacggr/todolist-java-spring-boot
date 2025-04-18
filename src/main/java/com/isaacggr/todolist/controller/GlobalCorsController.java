package com.isaacggr.todolist.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador global que aplica configuração CORS para toda a aplicação
 * Esta é uma solução "nuclear" para resolver erros CORS persistentes
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/**")
public class GlobalCorsController {
    // Este controlador não precisa de métodos
    // Sua única função é aplicar a configuração CORS global via anotação
} 