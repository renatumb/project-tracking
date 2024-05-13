package com.renatobonfim.projectmanagerbackend.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Tag(name = "Status", description = "Used to check the API's status")
@RequestMapping("/api/status")
public interface Status {

    @GetMapping
    public ResponseEntity<Map> status();

}
