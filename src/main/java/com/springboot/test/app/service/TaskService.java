package com.springboot.test.app.service;

import java.util.List;

import com.springboot.test.app.model.Task;

public interface TaskService {
	
	public Task creatTask(Task task);
	public List<Task> getAllTasks();
	public Task getTaskById(Long id);
	public Task updateTask(Task task);
	public void deleteTask(long id);
}
