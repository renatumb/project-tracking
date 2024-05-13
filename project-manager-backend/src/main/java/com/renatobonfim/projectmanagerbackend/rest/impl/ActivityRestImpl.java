package com.renatobonfim.projectmanagerbackend.rest.impl;

import com.renatobonfim.projectmanagerbackend.domain.Activity;
import com.renatobonfim.projectmanagerbackend.rest.ActivityRest;
import com.renatobonfim.projectmanagerbackend.service.ActivityService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityRestImpl implements ActivityRest {

    @Autowired
    ActivityService activityService;

    @Override
    public ResponseEntity<Activity> createActivity(Activity activity) throws URISyntaxException {
        Activity createdActivity = activityService.createActivity(activity);
        return ResponseEntity.created(new URI(createdActivity.getId() + "")).body(createdActivity);
    }

    @Override
    public ResponseEntity deleteActivity(Integer activityId) {
        activityService.deleteActivity(activityId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Activity> updateActivity(Integer activityId, Activity activity) {

        Activity updatedActivity = activityService.updateActivity(activityId, activity);
        return ResponseEntity.ok().body(updatedActivity);
    }

    @Override
    public ResponseEntity<Page<Activity>> findAll(int page, int size, String projectId) {
        if (Objects.nonNull(projectId)) {
            return ResponseEntity.ok().body(activityService.findAllActivityByProject(page, size, projectId));
        }
        return ResponseEntity.ok().body(activityService.findAll(page, size));
    }

    @Override
    public ResponseEntity<Activity> findById(Integer activityId) {
        return ResponseEntity.ok().body(activityService.findById(activityId));
    }
}
