package com.infsis.socialpagebackend.dtos;

import lombok.Data;

@Data
public class DocumentFileDTO {
    private String uuid;

    private String name;

    private String url;

    private String urlResource;

    private String type;

    private String status;
}
