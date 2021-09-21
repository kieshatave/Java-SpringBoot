package com.kieshatave.studentRoster.controllers;

import java.util.*;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.kieshatave.studentRoster.models.*;
import com.kieshatave.studentRoster.services.*;

@Controller
public class StudentController {
	private final StudentService studentService;
	private final ContactService contactService;
	public StudentController(StudentService studentService, ContactService contactService) {
		this.studentService = studentService;
		this.contactService = contactService;
	}
	
	@RequestMapping("/")
	public String Index(@ModelAttribute("student") Student student) {
		return "student/newStudent.html";
	}
	
	@PostMapping("/student/create")
	public String createStudent(@Valid Student student, BindingResult result) {
		if(result.hasErrors()) {
			return "/";
		} else {
			this.studentService.createStudent(student);
			return "redirect:/contact/create";
		}
	}
	
	@GetMapping("/students")
	public String getStudents(Model model) {
		List<Student> students = this.studentService.allStudent();
		model.addAttribute("students", students);
		return "/student/students.html";
	}
	
	@GetMapping("/contact/create")
	public String getContact(@ModelAttribute("contact") Contact contact, Model model) {
		List<Student> students = this.studentService.allStudent();
		model.addAttribute("students", students);
		return "/student/newContact.html";
	}
	
	@PostMapping("/contact/create")
	public String createContact(@Valid Contact contact, BindingResult result) {
		if(result.hasErrors()) {
			return "/contact/create";
		} else {
			this.contactService.createContact(contact);
			return "redirect:/students";
		}
	}
}
