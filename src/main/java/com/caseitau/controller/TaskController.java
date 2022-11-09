package com.caseitau.controller;

import com.caseitau.dto.TaskDto;
import com.caseitau.entity.StatusTask;
import com.caseitau.entity.Task;
import com.caseitau.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {

    TaskService taskService;

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody TaskDto taskDto){
        log.info("Criando uma nova tarefa com as informações [{}]", taskDto);
        return taskService.createTask(taskDto);
    }

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks(){
        log.info("Listando todas as tarefas cadastradas");
        return taskService.taskListAll();
    }

    @GetMapping("/tasks/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Task>> getTasksById(@PathVariable (value = "id") Long id, @PathVariable (value = "status") StatusTask status){
        log.info("Buscando tarefa com o id [{}]", id);
        List<Task> tasks = taskService.findTasks(status, id);
        return ResponseEntity.ok().body(tasks);
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updatedTaskById(@PathVariable (value = "id") Long id, @RequestBody TaskDto taskDto){
        log.info("Atualizando uma tarefa com o id [{}] e as novas informações são: [{}]", taskDto);
        return taskService.updateTask(taskDto, id);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id){
        log.info("Removendo uma tarefa com o id [{}]", id);
        return taskService.deleteById(id);
    }

}
