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
	private final DormService dormService;
	private final CourseService courseService;
	private final StudentCourseService studentCourseService;
	public StudentController(StudentService studentService, 
			ContactService contactService,
			DormService dormService,
			CourseService courseService,
			StudentCourseService studentCourseService) {
		this.studentService = studentService;
		this.contactService = contactService;
		this.dormService = dormService;
		this.studentCourseService = studentCourseService;
		this.courseService = courseService;
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
	
	@GetMapping("/student/show/{studentId}")
	public String showStudent(@PathVariable("studentId") Long id, Model model) {
		Student student = this.studentService.findStudent(id);
		model.addAttribute("student", student);
		List<Course> courses = this.courseService.allCourses();
		model.addAttribute("courses", courses);
		return "/student/showStudent.html";
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
	
	@GetMapping("/dorm/create")
	public String getCreateDorm(@ModelAttribute("dorm") Dorm dorm, Model model) {
		List<Dorm> dorms = this.dormService.allDorms();
		model.addAttribute("dorms", dorms);
		return "/student/newDorm.html";
	}
	
	@PostMapping("/dorm/create")
	public String createDorm(@Valid Dorm dorm, BindingResult result) {
		if(result.hasErrors()) {
			return "/dorm/create";
		} else {
			this.dormService.createDorm(dorm);
			return "redirect:/students";
		}
	}
	
	@GetMapping("/student/add/{dormId}")
	public String getShowDormForm(@ModelAttribute("student") Student student, 
			@PathVariable("dormId") Long dormId,
			Model model) {
		Dorm dorm = this.dormService.findDorm(dormId);
		List<Student> students = this.studentService.allStudent();
		List<Student> studentsInDorm = dorm.getStudentsInDorm();
		model.addAttribute("students", students);
		model.addAttribute("studentsInDorm", studentsInDorm);
		model.addAttribute("dorm", dorm);
		return "/student/showDorm.html";
	}
	
	@PostMapping("/student/add/{dormId}")
	public String addDormStudent(@Valid @ModelAttribute("student") Student student,
			BindingResult result, Model model,
			@PathVariable("dormId") Long dormId) {
		Dorm dorm = this.dormService.findDorm(dormId);
		model.addAttribute("dorm", dorm);
		if(result.hasErrors()) {
			return "/student/add/{dormId}";
		} else {
			this.dormService.createDorm(dorm);
			return "redirect:/student/add/{dormId}";
		}
	}
	
	@RequestMapping("/dorm/remove/{studentId}")
	public String deleteStudent(@PathVariable Long studentId) {
		Student removeStudent = this.studentService.findStudent(studentId);
		if ( removeStudent != null ) {
			this.dormService.removeStudent(studentId);
		}
		return "redirect:/dorm/create";
	}
	
	
	
	@GetMapping("/student/course/create")
	public String getCreateCourse(@ModelAttribute("course") Course course, Model model) {
		List<Course> courses = this.courseService.allCourses();
		model.addAttribute("courses", courses);
		return "/student/newCourse.html";
	}
	
	@PostMapping("/student/course/create")
	public String createCourse(@Valid Course course, BindingResult result) {
		if(result.hasErrors()) {
			return "/students";
		} else {
			this.courseService.addCourse(course);
			return "redirect:/student/course/create";
		}
	}
	
	@PostMapping("/studentcourse/add/{studentId")
	public String addStudentToCourse(@PathVariable("studentId") Long studentId,
			@RequestParam(value="courseId") Long courseId) {
		Student student = this.studentService.findStudent(studentId);
		Course course = this.courseService.find(courseId);
		StudentCourse studentCourse = new StudentCourse(course, student);
		studentCourseService.addCourse(studentCourse);
		return "redirect:/student/show/" + studentId;
	}
	
	@GetMapping("/student/showCourse/{courseId}")
	public String getShowCourse(@PathVariable("courseId") Long courseId, Model model) {
		Course course = this.courseService.find(courseId);
		model.addAttribute("course", course);
		return "/student/showCourse.html";
	}
	
}
