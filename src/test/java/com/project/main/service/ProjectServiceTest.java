package com.project.main.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.main.model.Project;
import com.project.main.repository.ProjectRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    public void testGetAllProjects() {
        when(projectRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, projectService.getAllProjects().size());
    }

    @Test
    public void testGetProjectById() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = new Date(dateFormat.parse("2024-01-01").getTime());
        Date endDate = new Date(dateFormat.parse("2024-01-10").getTime());

        Project project = new Project("Project", "Description", startDate, endDate);
        project.setId(1L); // Simulating auto-generated ID
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Optional<Project> result = projectService.getProjectById(1L);
        assertEquals(project, result.orElse(null));
    }

    @Test
    public void testUpdateProject() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = new Date(dateFormat.parse("2024-01-01").getTime());
        Date endDate = new Date(dateFormat.parse("2024-01-10").getTime());

        Project existingProject = new Project("Project", "Description", startDate, endDate);
        existingProject.setId(1L); // Simulating auto-generated ID

        Project updatedProject = new Project("Updated Project", "Updated Description", startDate, endDate);
        updatedProject.setId(1L); // Simulating auto-generated ID

        when(projectRepository.findById(1L)).thenReturn(Optional.of(existingProject));
        when(projectRepository.save(any())).thenReturn(updatedProject);

        Project result = projectService.updateProject(1L, updatedProject);

        assertEquals(updatedProject.getName(), result.getName());
        assertEquals(updatedProject.getDescription(), result.getDescription());
        assertEquals(updatedProject.getStartDate(), result.getStartDate());
        assertEquals(updatedProject.getEndDate(), result.getEndDate());
    }

    @Test
    public void testUpdateProject_NotFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            projectService.updateProject(1L, new Project());
        });

        verify(projectRepository, never()).save(any());
    }

    @Test
    public void testDeleteProject() {
        projectService.deleteProject(1L);
        verify(projectRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCreateProject() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = new Date(dateFormat.parse("2024-01-01").getTime());
        Date endDate = new Date(dateFormat.parse("2024-01-10").getTime());

        Project project = new Project("Project", "Description", startDate, endDate);
        when(projectRepository.save(project)).thenReturn(project);

        Project result = projectService.createProject(project);
        assertEquals(project, result);
    }
}
