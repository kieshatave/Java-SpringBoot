package com.kieshatave.studentRoster.models;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="contact")
public class Contact {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String address;
	    private String city;
	    private String state;
	    @Column(updatable=false)
	    private Date createdAt;
	    private Date updatedAt;
	    @OneToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="student_id")
	    private Student student;
	    public Contact() {
	        
	    }
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		public Student getStudent() {
			return student;
		}
		public void setStudent(Student student) {
			this.student = student;
		}
}
