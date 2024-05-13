package com.renatobonfim.projectmanagerbackend.rest.impl;

import com.renatobonfim.projectmanagerbackend.rest.Status;
import java.time.ZonedDateTime;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class StatusImpl implements Status {

    @Override
    public ResponseEntity<Map> status() {
        return ResponseEntity.ok(
                Map.of("status", "OK", "current Time", ZonedDateTime.now())
        );
    }
}
