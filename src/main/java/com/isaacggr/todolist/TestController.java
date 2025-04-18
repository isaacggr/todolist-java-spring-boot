package com.isaacggr.todolist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "online");
        response.put("message", "API TodoList está funcionando");
        response.put("version", "1.0.0");
        response.put("cors", "enabled for all origins");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/test-cors")
    public ResponseEntity<Map<String, Object>> testCors() {
        Map<String, Object> response = new HashMap<>();
        response.put("cors_test", "success");
        response.put("message", "CORS está configurado corretamente");
        return ResponseEntity.ok(response);
    }
} 