package com.isaacggr.todolist.task;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITaskRepository extends MongoRepository<TaskModel, String> {
    List<TaskModel> findByIdUser(String idUser);
}
