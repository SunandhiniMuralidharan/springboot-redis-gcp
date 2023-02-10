package com.student.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.redis.model.Student;
import com.student.redis.repository.StudentRepository;
import com.student.redis.service.StudentServiceImpl;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class StudentController {
	
	@Autowired
	private StudentServiceImpl studentService;
	
	@GetMapping(value = "/getStudents")
	public ResponseEntity<List<Student>> getStudents(){
		return ResponseEntity.ok(studentService.fetchAllStudents());
		
	}
	
	@GetMapping(value = "/getStudent/{studentId}")
	public ResponseEntity<Student> fetchStudentById(
			@PathVariable("studentId") String studentId){
		
		Student student = studentService.fetchStudentById(studentId);
		return ResponseEntity.ok(student);
	}
	
	@PostMapping(value = "/saveStudent", params = {"id","name","grade","contact"})
	public ResponseEntity<String> saveStudent(
			@Parameter(name = "id", required = true)@RequestParam("id") String id,
			@Parameter(name = "name", required = true)@RequestParam("name") String name,
			@Parameter(name = "grade", required = true)@RequestParam("grade") String grade,
			@Parameter(name = "contact", required = true)@RequestParam("contact") String contact) {
		Student student = new Student();
		student.setStudentId(id);
		student.setName(name);
		student.setGrade(grade);
		student.setContact(contact);
		
		boolean result = studentService.saveStudent(student);
		
		if(result) {
			return ResponseEntity.ok("Student Created Successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping(value = "/deleteStudent/{studentId}")
	public ResponseEntity<String> deleteStudent(
			@PathVariable("studentId") String studentId){
		
		boolean result = studentService.deleteStudent(studentId);
		if(result) {
			return ResponseEntity.ok("Student deleted Successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "/updateUser/{studentId}")
	public ResponseEntity<String> updateUser(
			@PathVariable("studentId") String studentId, @RequestBody Student student){
		
		boolean result = studentService.updateUser(studentId,student);
		if(result) {
			return ResponseEntity.ok("Student updated Successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
