package com.example.springboot.controller;

import com.example.springboot.data.Task;
import com.example.springboot.dto.TaskDto;
import com.example.springboot.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TaskController {
    private final TaskService taskService;

    public TaskController(@Autowired TaskService taskService ) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> create( @RequestBody TaskDto taskDto ) {
        try{
            Task task = new Task("",taskDto.getName(),taskDto.getDescription(),taskDto.getStatus(),taskDto.getAssignedTo(), taskDto.getDueDate(), "");
            return new ResponseEntity<>(taskService.create(task), HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        try{
            return new ResponseEntity<>(taskService.getAll(), HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Task> findById( @PathVariable String id ) {
        try{
            return new ResponseEntity<>(taskService.findById(id), HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        try{
            return new ResponseEntity<>(taskService.deleteById(id), HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Task> update( @RequestBody TaskDto taskDto, @PathVariable String id ) {
        try{
            Task task = taskService.findById(id);
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setStatus(taskDto.getStatus());
            task.setAssignedTo(taskDto.getAssignedTo());
            task.setDueDate(taskDto.getDueDate());
            return new ResponseEntity<>(taskService.update(task,id),HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
