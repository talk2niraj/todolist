package com.igt.todolist.repository;

import org.springframework.data.repository.Repository;
import com.igt.todolist.models.TodoItem;
import java.util.List;
 
public interface TodoRepository extends Repository<TodoItem, String> {
 
    void delete(TodoItem deleted);
 
    List<TodoItem> findAll();
 
    TodoItem findOne(String id);
 
    TodoItem save(TodoItem saved);
}