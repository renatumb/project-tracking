package com.renatobonfim.projectmanagerbackend.service.impl;

import com.renatobonfim.projectmanagerbackend.domain.Activity;
import com.renatobonfim.projectmanagerbackend.exception.NotFoundException;
import com.renatobonfim.projectmanagerbackend.repository.ActivityRepo;
import com.renatobonfim.projectmanagerbackend.service.ActivityService;
import com.renatobonfim.projectmanagerbackend.service.ProjectService;
import com.renatobonfim.projectmanagerbackend.util.CopyFields;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepo activityRepo;

    @Autowired
    ProjectService projectService;

    @Override
    public Activity createActivity(Activity activity) {
        return activityRepo.save(activity);
    }

    @Override
    public void deleteActivity(Integer activityId) {
        findById(activityId);
        activityRepo.deleteById(activityId);
    }

    @Override
    public Activity updateActivity(Integer activityId, Activity activity) {
        Activity activityFound = findById(activityId);
        CopyFields.copy(activity, activityFound);
        return activityRepo.save(activityFound);
    }

    @Override
    public Page<Activity> findAll(int page, int size) {
        return activityRepo.findAll(PageRequest.of(page, size, Sort.by("id")));
    }

    @Override
    public Activity findById(Integer activityId) {
        return activityRepo.findById(activityId).orElseThrow(() -> new NotFoundException("Activity not found for the given id: " + activityId));
    }

    @Override
    public Page<Activity> findAllActivityByProject(int page, int size, String projectId) {

        List allProjectsByClient = activityRepo.findAllActivityByProject(projectService.findById(projectId));

        PageRequest pageRequest = PageRequest.of(page, size);
        long start = pageRequest.getOffset();
        long end = Math.min((start + pageRequest.getPageSize()), allProjectsByClient.size());

        return new PageImpl<Activity>(allProjectsByClient.subList((int) start, (int) end), pageRequest, allProjectsByClient.size());
    }
}
