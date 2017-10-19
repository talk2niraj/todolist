package com.igt.todolist.utils;

import com.igt.todolist.models.TodoItem;

public class BeanUtils {

	public static void copyProperties(TodoItem source, TodoItem target) {
		if(target.getTitle() == null) {
			target.setTitle(source.getTitle());
		}
		
		if(target.getDescription() == null) {
			target.setDescription(source.getDescription());
		}
		
		if(target.isCompleted() == null) {
			target.setCompleted(source.isCompleted());
		}
	}
}
