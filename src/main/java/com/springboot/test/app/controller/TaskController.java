package com.springboot.test.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.test.app.model.Task;
import com.springboot.test.app.service.TaskService;

@RestController
@RequestMapping("/Tasks")
public class TaskController {
	
	@Autowired
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		super();
		this.taskService=taskService;
	}
	
	@PostMapping
	public ResponseEntity<?> createNewTask(@RequestBody Task task){
		try {
			Task createTask = taskService.creatTask(task);
			return new ResponseEntity<>(createTask, HttpStatus.CREATED);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>("Invalid Task data", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllTasks(){
		try {
			List<Task> allTasks = taskService.getAllTasks();
			return new ResponseEntity<>(allTasks, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable Long id){
		try {
			Task task = taskService.getTaskById(id);
			if(task!=null) 
				return new ResponseEntity<>(task, HttpStatus.OK);
			else
				return new ResponseEntity<>("Task Not Found", HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTaskById(@PathVariable Long id, @RequestBody Task updateTask){
		try {
			Task existingTask = taskService.getTaskById(id);
			if(existingTask != null) {
				existingTask.setTitle(updateTask.getTitle());
				existingTask.setDescription(updateTask.getDescription());
				existingTask.setDueDate(updateTask.getDueDate());
				existingTask.setTag(updateTask.getTag());
				
				Task updated = taskService.updateTask(existingTask);
				return new ResponseEntity<>(updated, HttpStatus.OK);
			}else {
					return new ResponseEntity<>("Task Not Found", HttpStatus.NOT_FOUND);
				}
			}catch(IllegalArgumentException e) {
				return new ResponseEntity<>("Invalid Task Data", HttpStatus.BAD_REQUEST);
			}catch(Exception e) {
				return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTaskById(@PathVariable Long id) {
		try {
			Task existingTask = taskService.getTaskById(id);
			if(existingTask!=null) {
				taskService.deleteTask(id);
				return new ResponseEntity<>("Task Succesfully Deleted", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Task Not Found", HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<>("Internal Server Erorr", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
