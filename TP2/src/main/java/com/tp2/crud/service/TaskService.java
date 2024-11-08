package com.tp2.crud.service;

import com.tp2.crud.model.Task;
import com.tp2.crud.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task createTask(Task task){
        return taskRepository.save(task);
    }
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }
    public Task updateTask(Long id, Task updatedTask){
        if(taskRepository.existsById(id)){
            updatedTask.setId(id);
            return taskRepository.save(updatedTask);
        }
        return null;
    }

    public Task deleteTask(Long id){
        Optional<Task> task = taskRepository.findById(id);
        taskRepository.deleteById(id);;
        return task.orElse(null);
    }

}
