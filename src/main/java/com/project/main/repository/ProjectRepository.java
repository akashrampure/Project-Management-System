package com.project.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.main.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
