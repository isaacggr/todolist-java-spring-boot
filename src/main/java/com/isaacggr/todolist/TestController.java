package com.isaacggr.todolist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(originPatterns = {"https://todolist-frontend-dt9a.onrender.com", "https://todolist-frontend.onrender.com", "https://isaacggr.github.io", "https://isaacggr.github.io/*", "http://localhost:[*]"}, 
             allowCredentials = "true",
             maxAge = 3600)
public class TestController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "online");
        response.put("message", "API TodoList está funcionando");
        response.put("version", "1.0.0");
        response.put("cors", "enabled for allowed origins");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/test-cors")
    public ResponseEntity<Map<String, Object>> testCors() {
        Map<String, Object> response = new HashMap<>();
        response.put("cors_test", "success");
        response.put("message", "CORS está configurado corretamente");
        response.put("timestamp", System.currentTimeMillis());
        response.put("allowed_origins", "Github Pages e Render");
        return ResponseEntity.ok(response);
    }
} 