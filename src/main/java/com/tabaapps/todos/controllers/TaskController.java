package com.tabaapps.todos.controllers;

import com.tabaapps.todos.models.Listing;
import com.tabaapps.todos.models.Task;
import com.tabaapps.todos.repositories.ListingRepository;
import com.tabaapps.todos.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tasks")
public class TaskController {
    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path = "/{listingId}")
    public List<Task> viewTasks(@PathVariable Long listingId) throws Exception {
        Optional<Listing> optionalListing = listingRepository.findById(listingId);
        if (optionalListing.isEmpty()) {
            throw new Exception("Listing is not found");
        }
        return taskRepository.findByListing(optionalListing.get());
    }

    @PostMapping(path = "/{listingId}")
    public Task addNewTask(@PathVariable Long listingId, @RequestBody Task task) throws Exception {
        Optional<Listing> optionalListing = listingRepository.findById(listingId);
        if (optionalListing.isEmpty()) {
            throw new Exception("Listing is not found");
        }
        task.setListing(optionalListing.get());
        return taskRepository.save(task);
    }

    @PutMapping(path = "/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) throws Exception {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new Exception("Task is not found");
        }
        Task savedTask = optionalTask.get();
        savedTask.setDescription(task.getDescription());
        savedTask.setStatus(task.getStatus());
        return savedTask;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) throws Exception {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new Exception("Task is not found");
        }
        taskRepository.delete(optionalTask.get());
        return ResponseEntity.noContent().build();
    }

}

