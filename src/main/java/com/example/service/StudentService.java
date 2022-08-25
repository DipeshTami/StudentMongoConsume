package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.response.Response;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	Response response;

	public Student createStudent (Student student) {
		Student std = studentRepository.save(student);
//		response.setId(std.getId());
//		response.setMessage(std.getName());
		return std;
	}
	
	public Student getStudentbyId(String id) {
		return studentRepository.findById(id).get();
	}
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public Student updateStudent (Student student) {
		return studentRepository.save(student);
	}
	
	public String deleteStudent (String id) {
		studentRepository.deleteById(id);
		return "Student has been deleted.";
	}
	
	public List<Student> getStudentsByName (String name) {
		return studentRepository.findByName(name);
	}
	
	public List<Student> studentsByNameAndMail (String name, String email) {
		return (List<Student>) studentRepository.findByEmailAndName(email, name);
	}
	
	public Student studentsByNameOrMail (String name, String email) {
		return studentRepository.findByNameOrEmail(name, email);
	}
	
	public List<Student> getAllWithPagination (int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		
		return studentRepository.findAll(pageable).getContent();
	}
	
	public List<Student> allWithSorting () {
		Sort sort = Sort.by(Sort.Direction.ASC, "name", "email");
		
		return studentRepository.findAll(sort);		
	}
	
	public List<Student> byDepartmentName (String deptName) {
		return studentRepository.findByDepartmentDepartmentName(deptName);
	}
	
	public List<Student> bySubjectName (String subName) {
		return studentRepository.findBySubjectsSubjectName(subName);
	}
	
	public List<Student> emailLike (String email) {
		return studentRepository.findByEmailIsLike(email);
	}
	
	public List<Student> nameStartsWith (String name) {
		return studentRepository.findByNameStartsWith(name);
	}
	
}
