package com.student.redis.model;

import java.io.Serializable;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash
@Persistent
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String studentId;
	private String name;
	private String grade;
	private String contact;
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Student{ id="+studentId+" name="+name+" grade="+grade+" contact="+contact;
	}
	
}
