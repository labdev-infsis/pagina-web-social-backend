package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.FollowerDTO;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.Users;
import com.infsis.socialpagebackend.services.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institutions")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @PreAuthorize("hasRole('STUDENT')")
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

    @PreAuthorize("hasRole('STUDENT')")
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
    @GetMapping("/{institutionUuid}/followers/count")
    public ResponseEntity<String> countFollowers(@PathVariable String institutionUuid) {
        try {
            long count = followerService.countFollowers(institutionUuid);
            return ResponseEntity.ok(count + " Seguidores");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{institutionUuid}/followers")
    public ResponseEntity<List<FollowerDTO>> getFollowers(@PathVariable String institutionUuid) {
        try {
            List<FollowerDTO> followers = followerService.getFollowers(institutionUuid);
            return ResponseEntity.ok(followers);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/{institutionUuid}/isFollowing")
    public ResponseEntity<Boolean> isFollowing(@PathVariable String institutionUuid) {
        try {
            boolean isFollowing = followerService.isFollowing(institutionUuid);
            return ResponseEntity.ok(isFollowing);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}