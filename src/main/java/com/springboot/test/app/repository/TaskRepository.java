package com.springboot.test.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.test.app.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
