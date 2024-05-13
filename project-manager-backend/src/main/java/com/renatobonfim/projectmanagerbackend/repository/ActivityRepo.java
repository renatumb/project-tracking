package com.renatobonfim.projectmanagerbackend.repository;

import com.renatobonfim.projectmanagerbackend.domain.Activity;
import com.renatobonfim.projectmanagerbackend.domain.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepo extends JpaRepository<Activity, Integer> {

    List<Activity> findAllActivityByProject(Project project);
}
