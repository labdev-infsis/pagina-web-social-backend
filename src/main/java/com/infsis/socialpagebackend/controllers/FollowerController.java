package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.services.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/institutions")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @PostMapping("/{institutionUuid}/follow")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> followInstitution(@PathVariable String institutionUuid) {
        try {
            followerService.followInstitution(institutionUuid);
            return ResponseEntity.ok("Has seguido esta página.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: ya sigues esta página.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{institutionUuid}/unfollow")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> unfollowInstitution(@PathVariable String institutionUuid) {
        try {
            followerService.unfollowInstitution(institutionUuid);
            return ResponseEntity.ok("Has dejado de seguir la página.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: no sigues esta página.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}