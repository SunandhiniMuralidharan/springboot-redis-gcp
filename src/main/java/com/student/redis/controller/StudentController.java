package com.student.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.redis.model.Student;
import com.student.redis.repository.StudentRepository;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@RequestMapping(value = "/getStudents")
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	@PutMapping(value = "/addStudent", params = {"id","name","grade","contact"})
	public Student addStudent(
			@Parameter(name = "id", required = true)@RequestParam("id") String id,
			@Parameter(name = "name", required = true)@RequestParam("name") String name,
			@Parameter(name = "grade", required = true)@RequestParam("grade") String grade,
			@Parameter(name = "contact", required = true)@RequestParam("contact") String contact) {
		Student student = new Student();
		student.setStudentId(id);
		student.setName(name);
		student.setGrade(grade);
		student.setContact(contact);
		
		studentRepository.save(student);
		return student;
	}

}
