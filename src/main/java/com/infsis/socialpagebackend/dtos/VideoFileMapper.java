package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.VideoFile;
import org.springframework.stereotype.Component;

@Component
public class VideoFileMapper {

    public VideoFileDTO toDTO(VideoFile videoFile) {
        VideoFileDTO videoFileDTO = new VideoFileDTO();
        videoFileDTO.setUuid(videoFile.getUuid());
        videoFileDTO.setName(videoFile.getName());
        videoFileDTO.setUrlResource(videoFile.getUrl_resource());
        videoFileDTO.setType(videoFile.getType());
        videoFileDTO.setStatus(videoFile.getStatus());

        return videoFileDTO;
    }

    public VideoFile getFile(VideoFileDTO videoFileDTO) {
        VideoFile videoFile = new VideoFile();
        videoFile.setUuid(videoFileDTO.getUuid());
        videoFile.setName(videoFileDTO.getName());
        videoFile.setUrl_resource(videoFileDTO.getUrlResource());
        videoFile.setType(videoFileDTO.getType());
        videoFile.setStatus(videoFileDTO.getStatus());

        return videoFile;
    }

}
