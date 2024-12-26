package com.app.todo.controller;

 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.todo.dto.TodoDto;
import com.app.todo.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
	
	private TodoService todoService;
	
	
	
	//Building Add Todo REST API
	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody  TodoDto todoDto){
		
		TodoDto savedTodo = todoService.addTodo(todoDto);
		
		return new ResponseEntity<>(savedTodo,HttpStatus.CREATED);
		
	}
	
	

}
