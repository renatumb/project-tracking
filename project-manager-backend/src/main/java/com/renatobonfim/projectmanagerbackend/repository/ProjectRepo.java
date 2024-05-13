package com.renatobonfim.projectmanagerbackend.repository;

import com.renatobonfim.projectmanagerbackend.domain.Client;
import com.renatobonfim.projectmanagerbackend.domain.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, String> {

    List<Project> findAllProjectsByClient(Client client);
}
