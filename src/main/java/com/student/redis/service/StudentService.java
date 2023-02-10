package com.student.redis.service;

import java.util.List;

import com.student.redis.model.Student;

public interface StudentService {
	
	boolean saveStudent(Student student);
	
	List<Student> fetchAllStudents();
	
	Student fetchStudentById(String studentId);
	
	boolean deleteStudent(String studentId);
	
	boolean updateUser(String studentId, Student student);

}
