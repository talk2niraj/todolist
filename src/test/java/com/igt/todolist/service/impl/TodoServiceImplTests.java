package com.igt.todolist.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.igt.todolist.models.TodoItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceImplTests {
	
	private List<String> idList = new ArrayList<String>();
	private TodoItem item;
	
	@Autowired
	private TodoServiceImpl todoService;
	
	
	@Before
    public void setUp() {
		String id = UUID.randomUUID().toString(); 
		String title = "Title" + Math.random();
		boolean completed = false;
		item = new TodoItem(id, title, completed);
		idList.add(id);
    }
	
	@Test
	public void createTest() {
		TodoItem persistedItem = todoService.create(item);
		
		assertThat(item.getId()).isEqualTo(persistedItem.getId());
		assertThat(item.getTitle()).isEqualTo(persistedItem.getTitle());
		assertThat(item.isCompleted()).isEqualTo(persistedItem.isCompleted());
		
	}

	@Test
	public void updateTest() {
		TodoItem persistedItem = todoService.create(item);
		persistedItem = todoService.update(persistedItem.getId());
		
		assertThat(item.getId()).isEqualTo(persistedItem.getId());
		assertThat(item.isCompleted()).isNotEqualTo(persistedItem.isCompleted());
	}
	
	public void deleteTest() {
		TodoItem persistedItem = todoService.create(item);
		persistedItem = todoService.delete(persistedItem.getId());
		
		assertThat(item.getId()).isEqualTo(persistedItem.getId());
	}
	
	@Test
	public void findTest() {
		TodoItem persistedItem = todoService.create(item);
		
		persistedItem = todoService.findById(item.getId());
		
		assertThat(item.getId()).isEqualTo(persistedItem.getId());
	}
	 
/*//	@Test
	@Test (expected = ResourceNotFoundException.class )
	public void findNonExistentTest() {
		TodoItem persistedItem = todoService.create(item);
		persistedItem = todoService.delete(persistedItem.getId());
		
		assertThat(item.getId()).isEqualTo(persistedItem.getId());
		
		try {
			persistedItem = todoService.findById(item.getId());
		} catch (ResourceNotFoundException ex) {
			String msg = "null defined by '"+ item.getId() + "' does not exist";
			assertThat(ex).isInstanceOf(ResourceNotFoundException.class);
			//.hasMessage(msg);
		}
		
	}*/
	
	@Test
	public void findAllTest() {
		todoService.create(item);
		
		List<TodoItem> itemList = todoService.findAll();

		assertThat(itemList.isEmpty(), is(false));
	}
	
	@After
	public void tearDown() {
		idList.forEach((id) -> todoService.delete(id));
    }
}
