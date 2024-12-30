package com.app.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.app.todo.dto.TodoDto;
import com.app.todo.entity.Todo;
import com.app.todo.exception.ResourceNotFoundException;
import com.app.todo.repository.TodoRepository;
import com.app.todo.service.TodoService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TodoServiceImpl  implements TodoService{
	
	private TodoRepository todoRepository;
	
	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		
//		//Converting TodoDto into Todo Jpa entity
//		Todo todo = new Todo();
//		todo.setTitle(todoDto.getTitle());
//		todo.setDescription(todoDto.getDescription());
//		todo.setCompleted(todoDto.isCompleted());
		
		Todo todo = modelMapper.map(todoDto, Todo.class);
		 
		//Todo Jpa entity
		Todo savedTodo =  todoRepository.save(todo);
		
		//Converting saved Todo Jpa entity object into TodoDto object
//		TodoDto savedTodoDto = new TodoDto();
//		savedTodoDto.setId(savedTodo.getId());
//		savedTodoDto.setTitle(savedTodo.getTitle());
//		savedTodoDto.setDescription(savedTodo.getDescription());
//		savedTodoDto.setCompleted(savedTodo.isCompleted() );
		
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
		
		
		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
		
		
		
		return modelMapper.map(todo, TodoDto.class);
		
	}

	@Override
	public List<TodoDto> getAllTodos() {
		
		List<Todo> todos = todoRepository.findAll();
		
		
		
		
		return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {
		
		Todo todo = todoRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
		
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		
		Todo updatedTodo = todoRepository.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public void deleteTodo(Long id) {
		
		Todo deletedTodo = todoRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
		
		todoRepository.deleteById(id);
		
		
	}

	@Override
	public TodoDto completeTodo(Long id) {
		
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
		
		todo.setCompleted(true);
		
		Todo updatedTodo = todoRepository.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) {
		
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
		
		todo.setCompleted(false);
		
		Todo updatedTodo = todoRepository.save(todo);

		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

}
