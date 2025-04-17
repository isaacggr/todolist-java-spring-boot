package com.isaacggr.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaacggr.todolist.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody TaskModel taskModel, HttpServletRequest request) {
        try {
            var idUser = request.getAttribute("idUser");
            taskModel.setIdUser(UUID.fromString(idUser.toString()));

            var currentDate = LocalDateTime.now();
            if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Data de início ou término não pode ser anterior à data atual");
            }
            if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Data de início deve ser menor do que a data de término");
            }

            var task = this.taskRepository.save(taskModel);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }
    // http://localhost:8080/tasks/67584657843-gsfgfsgsf-467834682
    @PutMapping("/{id}")
    public ResponseEntity<?> updated(@Valid @RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
        try {
            // Log dos dados recebidos
            System.out.println("ID da Tarefa recebido: " + id);
            System.out.println("Dados da Tarefa recebidos: " + taskModel.toString());
            
            var task = this.taskRepository.findById(id);
            if (task.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Tarefa não encontrada");
            }

            var idUser = request.getAttribute("idUser");
            if (idUser == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Usuário não identificado");
            }

            // Verificar se a tarefa pertence ao usuário
            TaskModel existingTask = task.get();
            if (!existingTask.getIdUser().equals(UUID.fromString(idUser.toString()))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Usuário não tem permissão para atualizar esta tarefa");
            }

            // Atualizar apenas os campos fornecidos
            try {
                Utils.copyNonNullProperties(taskModel, existingTask);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(e.getMessage());
            }
            
            // Validar datas se foram fornecidas
            if (existingTask.getStartAt() != null && existingTask.getEndAt() != null) {
                var currentDate = LocalDateTime.now();
                if (currentDate.isAfter(existingTask.getStartAt()) || currentDate.isAfter(existingTask.getEndAt())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Data de início ou término não pode ser anterior à data atual");
                }
                if (existingTask.getStartAt().isAfter(existingTask.getEndAt())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Data de início deve ser menor do que a data de término");
                }
            }

            // Salvar tarefa atualizada
            var updatedTask = this.taskRepository.save(existingTask);
            return ResponseEntity.ok(updatedTask);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar tarefa: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }
}

