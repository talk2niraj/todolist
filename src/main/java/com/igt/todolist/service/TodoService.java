package com.igt.todolist.service;


import java.util.List;

import org.springframework.boot.context.config.ResourceNotFoundException;

import com.igt.todolist.models.TodoItem;

public interface TodoService {

	public TodoItem create(TodoItem item);

	public TodoItem update(TodoItem item);

	public TodoItem delete(String id);

	public List<TodoItem> findAll();

	public TodoItem findById(String id) throws ResourceNotFoundException;

}
