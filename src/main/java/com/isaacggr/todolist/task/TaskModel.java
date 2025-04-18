package com.isaacggr.todolist.task;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
@Document(collection = "tasks")
public class TaskModel {
    
    @Id
    private String id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 50, message = "O título deve conter no máximo 50 caracteres")
    private String title;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255, message = "A descrição deve conter no máximo 255 caracteres")
    private String description;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDateTime startAt;

    @NotNull(message = "A data de término é obrigatória")
    private LocalDateTime endAt;

    @NotBlank(message = "A prioridade é obrigatória")
    private String priority;

    private String idUser;

    private LocalDateTime createdAt;
}
