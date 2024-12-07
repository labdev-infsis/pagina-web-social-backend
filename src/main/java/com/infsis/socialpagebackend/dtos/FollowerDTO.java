package com.infsis.socialpagebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowerDTO {
    private String name;
    private String las_name;
    private String photo_profile_path;
}