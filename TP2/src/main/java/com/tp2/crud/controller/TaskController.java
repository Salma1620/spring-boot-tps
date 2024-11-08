package com.tp2.crud.controller;

import com.tp2.crud.model.Task;
import com.tp2.crud.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getAllTasks")
    public String getAllTasks(Model model){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("taskslist", tasks);
        return "TaskListPage";
    }

    @GetMapping("/getTaskById/{id}")
    public String getTaskById(@PathVariable Long id, Model model){
        Task task = taskService.getTaskById(id);
        model.addAttribute("taskdetails", task);
        return "TaskDetailsPage";
    }

    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/showAddTaskPage")
    public String showAddTaskPage(){
        //Task createdTask = taskService.createTask(task);
        return "AddTaskPage";
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task){
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

}
