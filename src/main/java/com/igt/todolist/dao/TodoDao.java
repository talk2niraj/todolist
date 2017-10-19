package com.igt.todolist.dao;

import java.util.List;

import com.igt.todolist.models.TodoItem;
 
public interface TodoDao {
 
	TodoItem create(TodoItem todo);
 
	TodoItem delete(String id);
 
    List<TodoItem> findAll();
 
    TodoItem findById(String id);
 
    TodoItem update(TodoItem todo);
}