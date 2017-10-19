package com.igt.todolist.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igt.todolist.utils.BeanUtils;
import com.igt.todolist.dao.TodoDao;
import com.igt.todolist.models.TodoItem;
import com.igt.todolist.repository.TodoRepository;

import java.util.List;
 
@Service
final class TodoDaoImpl implements TodoDao {
 
    private final TodoRepository repository;
 
    @Autowired
    TodoDaoImpl(TodoRepository repository) {
        this.repository = repository;
    }
 
    @Override
    public TodoItem create(TodoItem todo) {
    	return repository.save(todo);
    }
 
    @Override
    public TodoItem delete(String id) {
    	TodoItem deleted = repository.findOne(id);
    	
    	if (deleted == null) {
    		return deleted;
    	}
    	
        repository.delete(deleted);
        return deleted;
    }
 
    @Override
    public List<TodoItem> findAll() {
        List<TodoItem> todoEntries = repository.findAll();
        return todoEntries;
    }
 
    @Override
    public TodoItem findById(String id) {
    	TodoItem found = repository.findOne(id);
        return found;
    }
 
    @Override
    public TodoItem update(TodoItem todo) {
    	TodoItem found = repository.findOne(todo.getId());
    	
    	if (found == null) {
    		return found;
    	}
    	
    	BeanUtils.copyProperties(found, todo);
        return repository.save(todo);
    }
 
}