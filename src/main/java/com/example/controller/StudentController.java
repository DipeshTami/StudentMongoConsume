package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
import org.springframework.web.client.RestTemplate;

import com.example.entity.Student;
import com.example.response.Response;
import com.example.service.StudentService;

@RestController
@RequestMapping("/consume/student")
public class StudentController {

	@Autowired
	StudentService studentService;
	@Autowired
	Response response;

	@PostMapping("/create")
	public Response createStudent(@RequestBody Student student) {
		Student std = createQuote(student);
		response.setId(std.getId());
		response.setMessage(std.getName(), "create");

		return response;
	}

	@GetMapping("/getById/{id}")
	public Student getStudentbyId(@PathVariable String id) {
		return getByIdQuote(id);
	}

	@GetMapping("/all")
	public List<Student> getAllStudents() {

		return getAllQuote();
	}

	@PutMapping("/update")
	public Response updateStudent(@RequestBody Student student) {
		Student std = updateQuote(student);
		response.setMessage(std.getName(), "update");
		return response;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable String id) {
		return deleteQuote(id);

	}

	@GetMapping("/studentsByName/{name}")
	public List<Student> studentsByName(@PathVariable String name) {
		return studentService.getStudentsByName(name);
	}

	@GetMapping("/studentsByNameAndMail")
	public List<Student> studentsByNameAndMail(@RequestParam String name, @RequestParam String email) {
		return getByNameAndEmail(name, email);
	}
	/*
	 * @GetMapping("/studentsByNameOrMail") public Student
	 * studentsByNameOrMail(@RequestParam String name,
	 * 
	 * @RequestParam String email) { return
	 * studentService.studentsByNameOrMail(name, email); }
	 * 
	 * @GetMapping("/allWithPagination") public List<Student>
	 * getAllWithPagination(@RequestParam int pageNo,
	 * 
	 * @RequestParam int pageSize) { return
	 * studentService.getAllWithPagination(pageNo, pageSize); }
	 * 
	 * @GetMapping("/allWithSorting") public List<Student> allWithSorting() { return
	 * studentService.allWithSorting(); }
	 * 
	 * @GetMapping("/byDepartmentName") public List<Student>
	 * byDepartmentName(@RequestParam String deptName) { return
	 * studentService.byDepartmentName(deptName); }
	 * 
	 * @GetMapping("/bySubjectName") public List<Student>
	 * bySubjectName(@RequestParam String subName) { return
	 * studentService.bySubjectName(subName); }
	 * 
	 * @GetMapping("/emailLike") public List<Student> emailLike(@RequestParam String
	 * email) { return studentService.emailLike(email); }
	 * 
	 * @GetMapping("/nameStartsWith") public List<Student>
	 * nameStartsWith(@RequestParam String name) { return
	 * studentService.nameStartsWith(name); }
	 */

	public Student createQuote(Student student) {
		final String uri = "http://localhost:8080/api/student/create";
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Student> request = new HttpEntity<Student>(student);
		ResponseEntity<Student> response = restTemplate.exchange(uri, HttpMethod.POST, request, Student.class, 1);
		return response.getBody();

	}

	public List<Student> getAllQuote() {
		final String uri = "http://localhost:8080/api/student/all";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Student[]> response = restTemplate.getForEntity(uri, Student[].class);
		List<Student> result = Arrays.asList(response.getBody());
		return result;

	}

	public Student getByIdQuote(String id) {
		final String uri = "http://localhost:8080/api/student/getById/" + id;
		;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Student> response = restTemplate.getForEntity(uri, Student.class);
		Student result = response.getBody();
		return result;

	}

	public Student updateQuote(Student student) {
		final String uri = "http://localhost:8080/api/student/update";
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Student> request = new HttpEntity<Student>(student);
		ResponseEntity<Student> response = restTemplate.exchange(uri, HttpMethod.PUT, request, Student.class, 1);
		return response.getBody();

	}

	public String deleteQuote(String id) {
		final String uri = "http://localhost:8080/api/student/delete/" + id;
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();

		// HttpEntity<?> request = new HttpEntity<Student>(student);
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.DELETE, null, String.class, 1);
		return response.getBody();

	}

	public List<Student> getByNameAndEmail(String name, String email) {
		final String uri = "http://localhost:8080/api/student/studentsByNameAndMail?name=" + name + "&email=" + email;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Student[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, Student[].class, 1);
		List<Student> result = Arrays.asList(response.getBody());
		return result;

	}
	////////////////dshcbscscbshd
}
