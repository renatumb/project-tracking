package com.renatobonfim.projectmanagerbackend.service;


import com.renatobonfim.projectmanagerbackend.domain.Project;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ProjectService {

    Project createProject(Project project);

    void deleteProject(String projectId);

    Project updateProject(String projectId, Project project);

    Page<Project> findAll(int page, int size);

    Project findById(String projectId);

    Page<Project> findAllProjectsByClient(int page, int size, String clientId);
}
