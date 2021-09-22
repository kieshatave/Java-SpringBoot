package com.kieshatave.studentRoster.models;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="dorms")
public class Dorm {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Dorm() {}
	
	public Dorm(String name) {
		this.name = name; 
	}
	
	@OneToMany(mappedBy="dorm", fetch = FetchType.LAZY)
    private List<Student> studentsInDorm;

	public List<Student> getStudentsInDorm() {
		return studentsInDorm;
	}

	public void setStudentsInDorm(List<Student> studentsInDorm) {
		this.studentsInDorm = studentsInDorm;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date(); 
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date(); 
	}
}
