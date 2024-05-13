package com.renatobonfim.projectmanagerbackend.rest;


import com.renatobonfim.projectmanagerbackend.domain.Project;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URISyntaxException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Tag(name = "Project", description = "Project's operations")
@RequestMapping("/api/project")
public interface ProjectRest {

    @PostMapping
    ResponseEntity<Project> createProject(@RequestBody Project project) throws URISyntaxException;

    @DeleteMapping("/{projectId}")
    ResponseEntity deleteProject(@PathVariable(value = "projectId", required = true) String projectId);

    @PutMapping("/{projectId}")
    ResponseEntity<Project> updateProject(@PathVariable(value = "projectId", required = true) String projectId, @RequestBody(required = true) Project project);

    @GetMapping
    ResponseEntity<Page<Project>> findAll(@RequestParam(value = "page", defaultValue = "0 ") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size,
                                          @RequestParam(value = "clientId", required = false ) String clientId
                                          );

    @GetMapping("/{projectId}")
    ResponseEntity<Project> findById(@PathVariable(value = "projectId", required = true) String projectId);
}
