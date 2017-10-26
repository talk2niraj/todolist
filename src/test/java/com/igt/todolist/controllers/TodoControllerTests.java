package com.igt.todolist.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.igt.todolist.models.TodoItem;
import com.igt.todolist.service.impl.TodoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoControllerTests {
    private MockMvc mockMvc;
    @SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
	private TodoServiceImpl todoService;
	@Autowired
	private TodoController controller;
	private List<String> idList = new ArrayList<String>();
	private TodoItem item;

	@Before
    public void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		
		String id = UUID.randomUUID().toString(); 
		String title = "Title" + Math.random();
		boolean completed = false;
		item = new TodoItem(id, title, completed);
		idList.add(id);
    }
	

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	@Test
	public void controllerInitializedCorrectly() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void callAllTestStepsInSequence() throws Exception {
		
		//TEST STEP - 1
		createTodoItemTest();
		
		//TEST STEP - 2
		getTodoItem();
		
		//TEST STEP - 3		
		updateTodoItem();
		
		//TEST STEP - 4
		getAllTodoItems();
		
		//TEST STEP - 5
		deleteTodoItem();
	}

	private void createTodoItemTest() throws Exception {
		mockMvc.perform(post("/api/v1/create/todo")
				.content(json(item))
				.accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
	}
	
	private void getAllTodoItems() throws Exception {
		mockMvc.perform(get("/api/v1/get/todo")
				.accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
	}

	private void getTodoItem() throws Exception {
		mockMvc.perform(get("/api/v1/get/todo/"+item.getId())
				.accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
	}
	
	private void updateTodoItem() throws Exception {
		mockMvc.perform(put("/api/v1/update/todo/"+item.getId())
				.accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("completed", is(true)));
	}
	
	private void deleteTodoItem() throws Exception {
		mockMvc.perform(delete("/api/v1/delete/todo/"+item.getId())
				.accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
	}
	
	
	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
	
	@After
	public void tearDown() {
//		idList.forEach((id) -> todoService.delete(id));
    }
}
