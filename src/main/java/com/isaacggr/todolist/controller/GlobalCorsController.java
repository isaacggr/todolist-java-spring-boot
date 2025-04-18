package com.isaacggr.todolist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador global que aplica configuração CORS para toda a aplicação
 * Também serve como endpoint para testar se API está funcionando
 */
@RestController
@CrossOrigin(origins = {"https://todolist-frontend-dt9a.onrender.com", "https://todolist-frontend.onrender.com", "https://isaacggr.github.io"}, 
             allowCredentials = "false",
             maxAge = 3600)
@RequestMapping("/")
public class GlobalCorsController {
    
    /**
     * Endpoint para testar se a API está online e se CORS está funcionando
     */
    @GetMapping("/cors-test")
    public ResponseEntity<Map<String, Object>> testCors() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "CORS está configurado corretamente");
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }
} 