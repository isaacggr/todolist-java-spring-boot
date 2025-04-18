package com.isaacggr.todolist.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para gerenciar tarefas (simulado sem persistência)
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = {"https://todolist-frontend.onrender.com", "https://isaacggr.github.io"})
public class TaskController {
    
    // Simulação de banco de dados em memória
    private static final List<Map<String, Object>> tasks = new ArrayList<>();
    
    // Adiciona algumas tarefas de exemplo ao iniciar
    static {
        // Tarefa 1
        Map<String, Object> task1 = new HashMap<>();
        task1.put("id", UUID.randomUUID().toString());
        task1.put("userId", "user-123");
        task1.put("title", "Implementar API REST");
        task1.put("description", "Criar endpoints para CRUD de tarefas");
        task1.put("startDate", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        task1.put("endDate", LocalDateTime.now().plusDays(3).format(DateTimeFormatter.ISO_DATE_TIME));
        task1.put("priority", "ALTA");
        task1.put("completed", false);
        tasks.add(task1);
        
        // Tarefa 2
        Map<String, Object> task2 = new HashMap<>();
        task2.put("id", UUID.randomUUID().toString());
        task2.put("userId", "user-123");
        task2.put("title", "Configurar CORS");
        task2.put("description", "Resolver problema de comunicação cross-origin");
        task2.put("startDate", LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ISO_DATE_TIME));
        task2.put("endDate", LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ISO_DATE_TIME));
        task2.put("priority", "MEDIA");
        task2.put("completed", true);
        tasks.add(task2);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getTasksByUserId(@PathVariable String userId) {
        List<Map<String, Object>> userTasks = new ArrayList<>();
        
        // Filtra tarefas pelo ID do usuário
        for (Map<String, Object> task : tasks) {
            if (task.get("userId").equals(userId)) {
                userTasks.add(task);
            }
        }
        
        return ResponseEntity.ok(userTasks);
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTask(@RequestBody Map<String, Object> taskData) {
        // Adiciona ID e outros campos
        taskData.put("id", UUID.randomUUID().toString());
        taskData.put("completed", false);
        
        // Adiciona à lista
        tasks.add(taskData);
        
        return ResponseEntity.ok(taskData);
    }
    
    @PutMapping("/{taskId}")
    public ResponseEntity<Map<String, Object>> updateTask(
            @PathVariable String taskId, 
            @RequestBody Map<String, Object> taskData) {
        
        // Encontra e atualiza a tarefa
        for (Map<String, Object> task : tasks) {
            if (task.get("id").equals(taskId)) {
                taskData.put("id", taskId); // Mantém o mesmo ID
                tasks.remove(task);
                tasks.add(taskData);
                return ResponseEntity.ok(taskData);
            }
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        // Remove a tarefa pelo ID
        tasks.removeIf(task -> task.get("id").equals(taskId));
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{taskId}/complete")
    public ResponseEntity<Map<String, Object>> completeTask(@PathVariable String taskId) {
        // Marca a tarefa como concluída
        for (Map<String, Object> task : tasks) {
            if (task.get("id").equals(taskId)) {
                task.put("completed", true);
                return ResponseEntity.ok(task);
            }
        }
        
        return ResponseEntity.notFound().build();
    }
} 