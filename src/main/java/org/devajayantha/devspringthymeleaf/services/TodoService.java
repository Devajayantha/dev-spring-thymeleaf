package org.devajayantha.devspringthymeleaf.services;

import org.devajayantha.devspringthymeleaf.models.entities.Todo;
import org.devajayantha.devspringthymeleaf.models.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    protected TodoRepository todoRepository;

    public List<Todo> findAllTodo(String query) {
        if (query != null) {
            return todoRepository.findByTitleContaining(query);
        }

        return todoRepository.findAll();
    }

    public void storeTodo(Todo todo) {
        todoRepository.saveAndFlush(todo);
    }

    public Todo getReferenceById(Long id) {
        return todoRepository.getReferenceById(id);
    }

    public Todo updateTodo(Todo todo, Long id) {
        Todo existTodo = todoRepository.getReferenceById(id);

        existTodo.setTitle(todo.getTitle());
        existTodo.setDescription(todo.getDescription());

        return todoRepository.saveAndFlush(existTodo);
    }

    public void delete(Long id) {
        Todo existTodo = todoRepository.getReferenceById(id);

        todoRepository.delete(existTodo);
    }
}
