package com.example.Tasks.infraestructure.repository;

import com.example.Tasks.infraestructure.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {


    Optional<Task> findByTitulo(String string);

    @Transactional
    void deleteById(Integer id);
}
