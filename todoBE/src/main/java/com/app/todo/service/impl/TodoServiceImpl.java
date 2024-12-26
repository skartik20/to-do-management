package com.app.todo.service.impl;

import org.springframework.stereotype.Service;

import com.app.todo.dto.TodoDto;
import com.app.todo.entity.Todo;
import com.app.todo.repository.TodoRepository;
import com.app.todo.service.TodoService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TodoServiceImpl  implements TodoService{
	
	private TodoRepository todoRepository;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		
		//Converting TodoDto into Todo Jpa entity
		Todo todo = new Todo();
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		
		 
		//Todo Jpa entity
		Todo savedTodo =  todoRepository.save(todo);
		
		//Converting saved Todo Jpa entity object into TodoDto object
		TodoDto savedTodoDto = new TodoDto();
		savedTodoDto.setId(savedTodo.getId());
		savedTodoDto.setTitle(savedTodo.getTitle());
		savedTodoDto.setDescription(savedTodo.getDescription());
		savedTodoDto.setCompleted(savedTodo.isCompleted() );
		
		
		return savedTodoDto;
	}

}
