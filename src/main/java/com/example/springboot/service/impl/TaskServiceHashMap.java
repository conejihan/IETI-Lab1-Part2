package com.example.springboot.service.impl;

import com.example.springboot.data.Task;
import com.example.springboot.dto.TaskDto;
import com.example.springboot.service.TaskService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskServiceHashMap implements TaskService {
    private HashMap<String, Task> tasks = new HashMap<String, Task>();
    private Integer idGenerate = 0;
    @Override
    public Task create(Task task) {
        idGenerate += 1;
        task.setId(idGenerate.toString());
        tasks.put(idGenerate.toString(),task);
        return tasks.get(idGenerate);
    }

    @Override
    public Task findById(String id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> getAll() {
        List<Task> allTasks = new ArrayList<Task>();
        for(String id : tasks.keySet()){
            allTasks.add(tasks.get(id));
        }
        return allTasks;
    }

    @Override
    public boolean deleteById(String id) {
        tasks.remove(id);
        return true;
    }

    @Override
    public Task update(Task task, String id) {
        return tasks.replace(id, task);
    }
}
