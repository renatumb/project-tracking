package com.renatobonfim.projectmanagerbackend.service;

import com.renatobonfim.projectmanagerbackend.domain.Activity;
import org.springframework.data.domain.Page;

public interface ActivityService {

    Activity createActivity(Activity activity);

    void deleteActivity(Integer activityId);

    Activity updateActivity(Integer activityId, Activity activity);

    Page<Activity> findAll(int page, int size);

    Activity findById(Integer activityId);

    Page<Activity> findAllActivityByProject(int page, int size, String projectId);
}
