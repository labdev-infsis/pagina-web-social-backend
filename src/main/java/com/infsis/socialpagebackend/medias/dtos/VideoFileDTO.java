package com.infsis.socialpagebackend.medias.dtos;

import lombok.Data;

@Data
public class VideoFileDTO {

    private String uuid;

    private String name;

    private String urlResource;

    private String type;

    private String status;
}
