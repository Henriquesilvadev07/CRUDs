package com.example.Tasks.business;


import com.example.Tasks.infraestructure.entity.Task;
import com.example.Tasks.infraestructure.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {


    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public void salvarTask (Task task) {
        task.setDataCriacao(LocalDateTime.now());
        taskRepository.saveAndFlush(task);
    }

    public List<Task> listarTudo() {
        return taskRepository.findAll();
    }

    public Task buscarPorId (Integer id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task não encontrada!")
        );
    }

    public Task buscarPorTitulo (String titulo) {
        return taskRepository.findByTitulo(titulo).orElseThrow(
                () -> new RuntimeException("Task não encontrada!")
        );
    }

    public void deletarPorId (Integer id) {
        taskRepository.deleteById(id);
    }

    public Task atualizarPorId (Integer id, Task task) {
        Task taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ID não encontrado!")
        );
        Task taskAtualizado = Task.builder()
                .titulo(task.getTitulo() != null ? task.getTitulo() :
                        taskEntity.getTitulo())
                .descricao(task.getDescricao() != null ? task.getDescricao() :
                        taskEntity.getDescricao())
                .status(task.getStatus() != null ? task.getStatus() :
                        taskEntity.getStatus())
                .id(taskEntity.getId())
                .dataCriacao(taskEntity.getDataCriacao())
                .build();
        taskRepository.saveAndFlush(taskAtualizado);
        return taskRepository.saveAndFlush(taskAtualizado);
    }

}
