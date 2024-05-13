package com.renatobonfim.projectmanagerbackend.rest;

import com.renatobonfim.projectmanagerbackend.domain.Activity;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URISyntaxException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Tag(name = "Activity", description = "Activity's operations")
@RequestMapping("/api/activity")
public interface ActivityRest {

    @PostMapping
    ResponseEntity<Activity> createActivity(@RequestBody Activity activity) throws URISyntaxException;

    @DeleteMapping("/{activityId}")
    ResponseEntity deleteActivity(@PathVariable(value = "activityId", required = true) Integer activityId);

    @PutMapping("/{activityId}")
    ResponseEntity<Activity> updateActivity(@PathVariable(value = "activityId", required = true) Integer activityId, @RequestBody(required = true) Activity activity);

    @GetMapping
    ResponseEntity<Page<Activity>> findAll(@RequestParam(value = "page", defaultValue = "0 ") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size,
                                          @RequestParam(value = "projectId", required = false) String projectId
    );

    @GetMapping("/{activityId}")
    ResponseEntity<Activity> findById(@PathVariable(value = "activityId", required = true) Integer activityId);
}
