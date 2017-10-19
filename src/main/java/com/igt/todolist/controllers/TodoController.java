package com.igt.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igt.todolist.models.TodoItem;
import com.igt.todolist.service.TodoService;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@RequestMapping("/api/v1")
public class TodoController {
	@Autowired
	private final TodoService service;
	 
    @Autowired
    TodoController(TodoService service) {
        this.service = service;
    }
    
	@PostMapping("/create/todo")
	public ResponseEntity createTodoItem(@RequestBody TodoItem item) {
		return new ResponseEntity(service.create(item), HttpStatus.OK);
	}
	
	
	@PutMapping("/update/todo/{id}")
	public ResponseEntity updateTodoItem(@RequestBody TodoItem item, @PathVariable (required = true) String id) {
		item.setId(id);
		return new ResponseEntity(service.update(item), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/todo/{id}")
	public ResponseEntity deleteTodoItem(@PathVariable (required = true) String id) {
		return new ResponseEntity(service.delete(id), HttpStatus.OK);
	}
	
	@GetMapping("/get/todo")
	public ResponseEntity getAllTodoItems() {
		return new ResponseEntity(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/get/todo/{id}")
	public ResponseEntity getTodoItem(@PathVariable (required = true) String id) throws ResourceNotFoundException {
		return new ResponseEntity(service.findById(id), HttpStatus.OK);
	}
}