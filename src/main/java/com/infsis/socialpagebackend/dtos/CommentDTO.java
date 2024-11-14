package com.infsis.socialpagebackend.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String uuid;
    private String content;
    private Date createdDate;
    private Date lastModifiedDate;


}