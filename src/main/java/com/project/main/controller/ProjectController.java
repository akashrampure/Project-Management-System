package com.project.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.main.model.Project;
import com.project.main.service.ProjectService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/project")
@OpenAPIDefinition
@Tag(name = "Project Controller", description = "Contains endpoints for managing projects")
public class ProjectController {

	@Autowired
    private ProjectService projectService;

    @PostMapping
    @Operation(summary = "Create a new project", description = "Creates a new project.")
    @ApiResponse(responseCode = "201", description = "Project created successfully")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all projects", description = "Retrieves a list of all projects.")
    @ApiResponse(responseCode = "200", description = "List of projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project by ID", description = "Retrieves a project by its ID.")
    @ApiResponse(responseCode = "200", description = "Project found")
    @ApiResponse(responseCode = "404", description = "Project not found")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(project -> new ResponseEntity<>(project, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update project", description = "Updates an existing project.")
    @ApiResponse(responseCode = "200", description = "Project updated successfully")
    @ApiResponse(responseCode = "404", description = "Project not found")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        try {
            Project updatedProject = projectService.updateProject(id, projectDetails);
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete project by ID", description = "Deletes a project by its ID.")
    @ApiResponse(responseCode = "204", description = "Project deleted successfully")
    @ApiResponse(responseCode = "404", description = "Project not found")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
