package com.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.model.*;
import com.todo.repository.TodoRepo;

import java.util.List;
import java.util.ArrayList;

@Service
public class TodoService {

	@Autowired
	private TodoRepo todoRepo;
	
	public List<ToDo> getAllTodoItems(){
		ArrayList<ToDo> todoList = new ArrayList<>(); 
		todoRepo.findAll().forEach(todo -> todoList.add(todo));
		return todoList;
	}
	
	public ToDo getTodoItembyId(Long id) {
		return todoRepo.findById(id).get();
	}
	
	public boolean updateStatus(Long id) {
		ToDo todo = getTodoItembyId(id);
		todo.setStatus("Completed");
		return saveOrUpdateTodoItem(todo);
	}
	
	public boolean saveOrUpdateTodoItem(ToDo todo) {
		ToDo obj = todoRepo.save(todo);
		if(getTodoItembyId(obj.getId()) != null) {
			return true;
		}
		return false;
	}
	
	public boolean deleteTodoItem(Long id) {
		todoRepo.deleteById(id);
		if(todoRepo.findById(id).isEmpty()) {
			return true;
		}
		return false;
	}
	
}
