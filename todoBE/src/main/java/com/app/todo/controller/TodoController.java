package com.app.todo.controller;

 import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.todo.dto.TodoDto;
import com.app.todo.service.TodoService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
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
	
	//Building Get Todo REST API
	@GetMapping("{id}")
	public ResponseEntity<TodoDto>  getTodo(@PathVariable("id") Long todoId){
		
		TodoDto todoDto = todoService.getTodo(todoId);
		
		return new ResponseEntity<>(todoDto, HttpStatus.OK);
		
	}
	
	//Building Get All Todo REST API
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos(){
		List<TodoDto> todos = todoService.getAllTodos();
		//return new ResponseEntity<>(todos, HttpStatus.OK);
		return ResponseEntity.ok(todos);
	}

	
	//Building Update Todo REST API
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId){
		
		TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);
		
		
		
		return ResponseEntity.ok(updatedTodo);
		
	}

	//Building Delete Todo REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
		
		todoService.deleteTodo(todoId);
		
		return ResponseEntity.ok("Todo deleted successfully!.");
	}
	
	//Building Complete Todo REST API
	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
		
		TodoDto updatedTodo =  todoService.completeTodo(todoId);
		
		return ResponseEntity.ok(updatedTodo);
	}
	
	
	//Building In Complete Todo REST API
	@PatchMapping("{id}/in-complete")
	public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
		
		TodoDto updatedTodo =  todoService.inCompleteTodo(todoId);
		
		return ResponseEntity.ok(updatedTodo);
	}
	
	
}
