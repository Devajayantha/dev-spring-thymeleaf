package org.devajayantha.devspringthymeleaf.controllers;

import org.devajayantha.devspringthymeleaf.models.entities.Todo;
import org.devajayantha.devspringthymeleaf.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    protected TodoService todoService;

    /**
     * Get all todos from storage.
     *
     * @param query search query
     * @param model Model
     * @return String view
     */
    @GetMapping
    public String getAllTodo(@RequestParam(name = "query", required = false) String query, Model model){
        List<Todo> todos = todoService.findAllTodo(query);
        model.addAttribute("todos", todos);

        return "todo/index";
    }

    /**
     * Create a new todo.
     *
     * @param model
     * @return String view
     */
    @GetMapping("/create")
    public String createTodo(Model model){
        model.addAttribute("todo", new Todo());
        return "todo/create";
    }

    /**
     * Store a new todo into storage.
     *
     * @param todo Todo
     * @param model Model
     * @return String view
     */
    @PostMapping
    public String storeTodo(@ModelAttribute Todo todo, Model model){
        todoService.storeTodo(todo);

        return "redirect:/todos";
    }

    /**
     * Get a todo by id.
     *
     * @param model Model
     * @param id Long
     * @return String view
     */
    @GetMapping("/{id}")
    public String getTodo(Model model, @PathVariable("id") Long id){
        Todo todo = todoService.getReferenceById(id);

        model.addAttribute("todo", todo);

        return "todo/edit";
    }

    /**
     * Update a todo by id.
     *
     * @param id Long
     * @param todo Todo
     * @param model Model
     * @return String view
     */
    @PatchMapping("/{id}/update")
    public String updateTodo(@PathVariable("id") Long id, @ModelAttribute Todo todo, Model model){
        todoService.updateTodo(todo, id);

        return "redirect:/todos";
    }

    /**
     * Delete a todo by id.
     *
     * @param id Long
     * @return String view
     */
    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable("id") Long id){
        todoService.delete(id);

        return "redirect:/todos";
    }
}
