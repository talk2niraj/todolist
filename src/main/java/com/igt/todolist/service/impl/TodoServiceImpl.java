package com.igt.todolist.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.igt.todolist.dao.TodoDao;
import com.igt.todolist.models.TodoItem;
import com.igt.todolist.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {
	
	private final TodoDao dao;
	 
    @Autowired
    TodoServiceImpl(TodoDao dao) {
        this.dao = dao;
    }

	public TodoItem create(TodoItem item){
		return dao.create(item);
	}

	public TodoItem update(String id) {
		TodoItem todo = dao.update(id);
		
		failIfNull(id, todo);
		
		return todo;
	}

	public TodoItem delete(String id) {
		TodoItem todo = dao.delete(id);
		
		failIfNull(id, todo);
		
		return todo;
	}


	public List<TodoItem> findAll() {
		return dao.findAll();
	}

	public TodoItem findById(String id) throws ResourceNotFoundException {
		TodoItem todo = dao.findById(id);
		
		failIfNull(id, todo);
		
		return todo;
	}

	private void failIfNull(String id, TodoItem todo) {
		if (todo == null) {
			throw new ResourceNotFoundException(id, null);
		}
	}
}
