package com.project.main.model;
import jakarta.persistence.*;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;


@Entity
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Unique identifier for the project")
	private Long id;
	
	@Schema(description = "Name of the project")
    private String name;
	
	@Schema(description = "Description of the project")
    private String description;
	
	@Schema(description = "Start date of the project")
    private Date startDate;
	
	@Schema(description = "End date of the project")
    private Date endDate;
    
    
	public Project(String name, String description, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	public Project(Long id, String name, String description) {
	    this.id = id;
	    this.name = name;
	    this.description = description;
	}

	

	public Project() {
		super();
	}




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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
    
    
    

}
