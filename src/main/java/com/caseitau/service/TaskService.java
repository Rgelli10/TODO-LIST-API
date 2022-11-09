package com.caseitau.service;

import com.caseitau.dto.TaskDto;
import com.caseitau.entity.StatusTask;
import com.caseitau.entity.Task;
import com.caseitau.entity.User;
import com.caseitau.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;
//    private User user;
//    private UserRepository user;

    public Task createTask(TaskDto taskDto){
        Task toTaskDto = toTaskDto(taskDto);
        return taskRepository.save(toTaskDto);
    }

    public List<Task> taskListAll(){
        return taskRepository.findAll();
    }

    public List<Task> findTasks(StatusTask statusTask, Long id){
//        User user = taskRepository.findUserById();
//        if (user.getIsAdmin()==1){
//            return findAll();
//        }
//        if(user.getIsAdmin() == 1){
//            return taskRepository.findByUserIdAndStatus(id, statusTask);
//        }
//        if(user.getIsAdmin() == 1){
//            return taskRepository.findAll();
//        }
        return taskRepository.findByUserIdAndStatus(id, statusTask);
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
