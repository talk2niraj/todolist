package com.igt.todolist.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class TodoItem {
	@Id
	private String id;
	@NotNull
	private String title;
	private String description;
	private Boolean completed = false;

	
	public TodoItem() {
		super();
	}

	public TodoItem(String id, String title, String description, boolean completed) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.completed = completed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean isCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
