package com.caseitau.controller;

import com.caseitau.config.TokenService;
import com.caseitau.dto.TaskDto;
import com.caseitau.entity.Task;
import com.caseitau.service.TaskService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestHeader Map<String, String> headers, @RequestBody TaskDto taskDto) {
        System.out.println(headers);
        log.info("Criando uma nova tarefa com as informações [{}]", taskDto);
        return taskService.createTask(taskDto, headers.get("authorization"));
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks(@RequestHeader Map<String, String> headers, @PathVariable(value = "id") Long id) {
        log.info("Listando todas as tarefas cadastradas");
        Claims authorization = tokenService.getAllClaimsFromToken(headers.get("authorization"));
        return taskService.findTasks(id, authorization.getSubject());
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updatedTaskById(@PathVariable(value = "id") Long id, @RequestBody TaskDto taskDto) {
        log.info("Atualizando uma tarefa com o id [{}] e as novas informações são: [{}]", taskDto);
        return taskService.updateTask(taskDto, id);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable(value = "id") Long id) {
        log.info("Removendo uma tarefa com o id [{}]", id);
        return taskService.deleteById(id);
    }

}
