package com.kieshatave.grouplang.models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="languages")
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Size(min=2, max=20)
	private String name;
	
	@Size(min=2, max=20)
	private String creator;
	
	@NotNull(message = "Current version cannot be empty.")
	private Float currentVersion;
	
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Float getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Float currentVersion) {
		this.currentVersion = currentVersion;
	}

	public Language() {}
    
    public Language(Long id, String name, String creator, Float currentVersion) {
    	this.id = id;
    	this.name = name;
    	this.creator = creator;
    	this.currentVersion = currentVersion;
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

