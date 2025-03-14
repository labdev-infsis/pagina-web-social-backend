package com.infsis.socialpagebackend.followers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowerDTO {
    private String name;
    private String last_name;
    private String photo_profile_path;
}