package com.example.springboot.service;

import com.example.springboot.data.Task;
import com.example.springboot.dto.TaskDto;

import java.util.List;

public interface TaskService {
    Task create(Task task );

    Task findById( String id );

    List<Task> getAll();

    boolean deleteById( String id );

    Task update(Task task, String id );
}
