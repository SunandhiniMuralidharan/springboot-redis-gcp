package com.student.redis.repository;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.student.redis.model.Student;

@Repository
public class StudentRepository {

	   private HashOperations hashOperations;

	    private RedisTemplate redisTemplate;

	    public StudentRepository(RedisTemplate redisTemplate){
	        this.redisTemplate = redisTemplate;
	        this.hashOperations = this.redisTemplate.opsForHash();
	    }

	    public void save(Student student){
	        hashOperations.put("STUDENT", student.getStudentId(), student);
	    }
	    public List<Student> findAll(){
	        return hashOperations.values("STUDENT");
	    }

	    public Student findById(String id){
	        return (Student) hashOperations.get("USER", id);
	    }

	    public void update(Student student){
	        save(student);
	    }

	    public void delete(String id){
	        hashOperations.delete("USER", id);
	    }

}
