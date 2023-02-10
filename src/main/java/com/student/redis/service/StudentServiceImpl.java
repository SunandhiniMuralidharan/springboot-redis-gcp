package com.student.redis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.redis.model.Student;
import com.student.redis.repository.StudentDao;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public boolean saveStudent(Student student) {

		return studentDao.saveStudent(student);
	}

	@Override
	public List<Student> fetchAllStudents() {
		
		return studentDao.fetchAllStudents();
	}

	@Override
	public Student fetchStudentById(String studentId) {
		
		return studentDao.fetchStudentById(studentId);
	}

	@Override
	public boolean deleteStudent(String studentId) {
		
		return studentDao.deleteStudent(studentId);
	}

	@Override
	public boolean updateUser(String studentId, Student student) {

		return studentDao.updateUser(studentId, student);
	}

}
