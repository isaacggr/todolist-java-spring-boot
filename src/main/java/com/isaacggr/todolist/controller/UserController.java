package com.isaacggr.todolist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de usuários simplificado para teste de CORS
 * Apenas responde às requisições sem persistência de dados
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"https://todolist-frontend-dt9a.onrender.com", "https://todolist-frontend.onrender.com", "https://isaacggr.github.io"}, 
             maxAge = 3600)
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = new HashMap<>();
        
        // Simulação de login bem-sucedido (sem verificação real)
        response.put("id", "user-123");
        response.put("username", credentials.get("username"));
        response.put("name", "Usuário de Teste");
        response.put("token", "jwt-token-simulado");
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, String> userData) {
        Map<String, Object> response = new HashMap<>();
        
        // Simulação de usuário criado com sucesso
        response.put("id", "user-" + System.currentTimeMillis());
        response.put("username", userData.get("username"));
        response.put("name", userData.get("name"));
        response.put("created", true);
        
        return ResponseEntity.ok(response);
    }
} 