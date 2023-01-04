package com.yy.tasks.controller;

import com.yy.tasks.model.Task;
import com.yy.tasks.repository.TaskRepository;
import com.yy.tasks.request.AddTaskRequest;
import com.yy.tasks.response.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<TaskResponse>> getTasks() {
        List<Task> tasks = repository.findAll();
        List<TaskResponse> responses = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponse response = new TaskResponse(task.getId(), task.getDescription(), task.getDayAndTime(), task.isSetReminder());
            responses.add(response);
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        Optional<Task> task = repository.findById(id);
        if(task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new TaskResponse(task.get().getId(), task.get().getDescription(), task.get().getDayAndTime(), task.get().isSetReminder()));
    }

    @PostMapping("/add")
    public ResponseEntity<TaskResponse> addTask(@RequestBody AddTaskRequest addTaskRequest, RedirectAttributes redirectAttributes) {
        Task task = new Task();
        task.setDescription(addTaskRequest.getText());
        task.setDayAndTime(addTaskRequest.getDay());
        task.setSetReminder(addTaskRequest.isReminder());

        repository.save(task);

//        redirectAttributes.addFlashAttribute("taskResponse", new TaskResponse(task.getId(), task.getDescription(), task.getDayAndTime(), task.isSetReminder()));
//        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
        return ResponseEntity.ok(new TaskResponse(task.getId(), task.getDescription(), task.getDayAndTime(), task.isSetReminder()));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location", "/").build();
    }

}
