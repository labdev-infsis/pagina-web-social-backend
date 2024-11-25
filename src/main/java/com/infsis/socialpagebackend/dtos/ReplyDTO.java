package com.infsis.socialpagebackend.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyDTO {
    private String uuid;
    private Date createdDate;
    private String content;
    private String name;
    private String lastName;
}