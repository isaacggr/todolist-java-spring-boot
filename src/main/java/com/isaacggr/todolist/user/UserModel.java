package com.isaacggr.todolist.user;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.CompoundIndex;

import lombok.Data;

@Data
@Document(collection = "users")
@CompoundIndex(def = "{'username': 1}", unique = true)
public class UserModel {
    
    @Id
    private String id;
    
    private String name;
    
    @Indexed(unique = true)
    private String username;
    
    private String password;
    
    private LocalDateTime createdAt;
}
