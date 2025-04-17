package com.isaacggr.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 50, message = "O título deve conter no máximo 50 caracteres")
    @Column(length = 50)
    private String title;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(length = 255)
    @Size(max = 255, message = "A descrição deve conter no máximo 255 caracteres")
    private String description;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDateTime startAt;

    @NotNull(message = "A data de término é obrigatória")
    private LocalDateTime endAt;

    @NotBlank(message = "A prioridade é obrigatória")
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) {
        if (title != null && title.length() > 50) {
            throw new IllegalArgumentException("O título deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }

}
