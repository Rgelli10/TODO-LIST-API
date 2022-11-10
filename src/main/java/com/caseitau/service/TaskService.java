package com.caseitau.service;

import com.caseitau.config.TokenService;
import com.caseitau.dto.TaskDto;
import com.caseitau.entity.StatusTask;
import com.caseitau.entity.Task;
import com.caseitau.entity.User;
import com.caseitau.repository.TaskRepository;
import com.caseitau.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public Task createTask(TaskDto taskDto, String token){
        String user = tokenService.getAllClaimsFromToken(token).getIssuer();
        System.out.println(user);

        Task toTaskDto = toTaskDto(taskDto);
        return taskRepository.save(toTaskDto);
    }

    public List<Task> taskListAll(){
        return taskRepository.findAll();
    }

    public List<Task> findTasks(Long id, String token){
        List<Task> tasks = new ArrayList<>();
        User user = userRepository.findById(id).orElseThrow();

        if(token.equals("user")){
            tasks.addAll(taskRepository.findByUserId(user.getUserId()));
        }
        else{
            tasks = taskRepository.findAll();
        }

        return tasks;
    }

    public ResponseEntity<Task> updateTask(TaskDto taskDto, Long id){
        return taskRepository.findById(id)
                .map(taskToUpdate -> {
                    taskToUpdate.setResumeDescription(taskDto.getResumeDescription());
                    taskToUpdate.setDescription(taskDto.getDescription());
                    taskToUpdate.setStatus(StatusTask.valueOf(taskDto.getStatus()));
                    Task updated = taskRepository.save(taskToUpdate);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteById(Long id){
        return taskRepository.findById(id)
                .map(taskToDelete -> {
                    taskRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    private Task toTaskDto(TaskDto taskDto){
        var task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setResumeDescription(taskDto.getResumeDescription());
        task.setCreatedAt(taskDto.getCreatedAt());
        task.setUpdatedAt(taskDto.getUpdatedAt());
        task.setStatus(StatusTask.valueOf(taskDto.getStatus()));
        task.setId(taskDto.getId());
        task.setUserId(1L);
        return task;
    }

}
