package org.devajayantha.devspringthymeleaf.models.repositories;

import org.devajayantha.devspringthymeleaf.models.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository  extends JpaRepository<Todo, Long>{
    List<Todo> findAll();

    @Query("SELECT t FROM Todo t WHERE t.title LIKE %:query%")
    List<Todo> findByTitleContaining(String query);

    Todo saveAndFlush(Todo todo);

    Todo getReferenceById(Long id);

    void delete(Todo todo);
}
