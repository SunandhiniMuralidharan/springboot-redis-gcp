package com.student.redis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.student.redis.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	private static final String KEY = "STUDENT";
	
	@Override
	public boolean saveStudent(Student student) {

        try {
        	
        	redisTemplate.opsForHash().put(KEY, student.getStudentId(), student);
            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public List<Student> fetchAllStudents() {
		
        List students;
        students = redisTemplate.opsForHash().values(KEY);
        return  students;
	}

	@Override
	public Student fetchStudentById(String studentId) {

        Student user;
        user = (Student) redisTemplate.opsForHash().get(KEY,studentId);
        return user;
	}

	@Override
	public boolean deleteStudent(String studentId) {
		
        try {
            redisTemplate.opsForHash().delete(KEY,studentId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean updateUser(String studentId, Student student) {
		
        try {
            redisTemplate.opsForHash().put(KEY, studentId, student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

}
