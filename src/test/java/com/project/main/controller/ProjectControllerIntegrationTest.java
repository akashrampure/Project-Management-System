package com.project.main.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;



@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllProjects() throws Exception {
        mockMvc.perform(get("/project"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetProjectById() throws Exception {
        mockMvc.perform(get("/project/{id}", 1L))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateProject() throws Exception {
        mockMvc.perform(post("/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Project\",\"description\":\"New Description\",\"startDate\":\"2024-03-10\",\"endDate\":\"2024-03-20\"}"))
               .andExpect(status().isCreated())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateProject() throws Exception {
        mockMvc.perform(put("/project/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Updated Project\",\"description\":\"Updated Description\",\"startDate\":\"2024-03-12\",\"endDate\":\"2024-03-25\"}"))
               .andExpect(status().isOk());
    }

    @Test
    public void testUpdateNonExistingProject() throws Exception {
        mockMvc.perform(put("/project/{id}", 999L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":999,\"name\":\"Updated Project\",\"description\":\"Updated Description\",\"startDate\":\"2024-03-12\",\"endDate\":\"2024-03-25\"}"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateProjectWithInvalidData() throws Exception {
        mockMvc.perform(put("/project/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"\",\"description\":\"Updated Description\",\"startDate\":\"2024-03-12\",\"endDate\":\"2024-03-25\"}"))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteProject() throws Exception {
        mockMvc.perform(delete("/project/{id}", 1L))
               .andExpect(status().isNoContent());

        mockMvc.perform(get("/project/{id}", 1L))
               .andExpect(status().isNotFound());
    }
}
