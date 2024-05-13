package com.renatobonfim.projectmanagerbackend.service.impl;

import com.renatobonfim.projectmanagerbackend.domain.Project;
import com.renatobonfim.projectmanagerbackend.exception.NotFoundException;
import com.renatobonfim.projectmanagerbackend.repository.ProjectRepo;
import com.renatobonfim.projectmanagerbackend.service.ClientService;
import com.renatobonfim.projectmanagerbackend.service.ProjectService;
import com.renatobonfim.projectmanagerbackend.util.CopyFields;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    ClientService clientService;

    @Override
    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public void deleteProject(String projectId) {
        projectRepo.findById(projectId);
        projectRepo.deleteById(projectId);
    }

    @Override
    public Project updateProject(String projectId, Project project) {
        Project projectFound = findById(projectId);
        CopyFields.copy(project, projectFound);
        return projectRepo.save(projectFound);
    }

    @Override
    public Page<Project> findAll(int page, int size) {
        return projectRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    @Override
    public Project findById(String projectId) {
        return projectRepo.findById(projectId).orElseThrow(() -> new NotFoundException("Project not found for given id: " + projectId));
    }

    @Override
    public Page<Project> findAllProjectsByClient(int page, int size, String clientId) {

        List allProjectsByClient = projectRepo.findAllProjectsByClient(clientService.findById(clientId));

        PageRequest pageRequest = PageRequest.of(page, size);
        long start = pageRequest.getOffset();
        long end = Math.min((start + pageRequest.getPageSize()), allProjectsByClient.size());

        return new PageImpl<Project>(allProjectsByClient.subList((int) start, (int) end), pageRequest, allProjectsByClient.size());
    }
}
