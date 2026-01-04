package com.example.Tasks.controller;


import com.example.Tasks.business.TaskService;
import com.example.Tasks.infraestructure.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> salvarTaks(@RequestBody Task task) {
        taskService.salvarTask(task);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Task>> listarTudo () {
        return ResponseEntity.ok(taskService.listarTudo());
    }

    @GetMapping("/titulo")
    public ResponseEntity<Task> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(taskService.buscarPorTitulo(titulo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.buscarPorId(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarPorId(@RequestParam Integer id) {
        taskService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarPorId(@RequestParam Integer id,
                                               @RequestBody Task task) {
        taskService.atualizarPorId(id,task);
        return ResponseEntity.ok().build();
    }


}
