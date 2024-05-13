package com.renatobonfim.projectmanagerbackend.rest.impl;

import com.renatobonfim.projectmanagerbackend.domain.Project;
import com.renatobonfim.projectmanagerbackend.rest.ProjectRest;
import com.renatobonfim.projectmanagerbackend.service.ProjectService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectRestImpl implements ProjectRest {

    @Autowired
    ProjectService projectService;

    @Override
    public ResponseEntity<Project> createProject(Project project) throws URISyntaxException {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.created(new URI(createdProject.getId())).body(createdProject);
    }

    @Override
    public ResponseEntity deleteProject(String projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Project> updateProject(String projectId, Project project) {
        Project updatedProject = projectService.updateProject(projectId, project);
        return ResponseEntity.ok().body(updatedProject);
    }

    @Override
    public ResponseEntity<Page<Project>> findAll(int page, int size, String clientId) {
        if (Objects.nonNull(clientId)) {
            return ResponseEntity.ok().body(projectService.findAllProjectsByClient(page, size, clientId));
        }
        return ResponseEntity.ok().body(projectService.findAll(page, size));
    }

    @Override
    public ResponseEntity<Project> findById(String projectId) {
        return ResponseEntity.ok().body(projectService.findById(projectId));
    }
}
