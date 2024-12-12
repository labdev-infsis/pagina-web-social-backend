package com.infsis.socialpagebackend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class VideoFileListValidator implements ConstraintValidator<ValidVideoFile, List<MultipartFile>> {

    private static final long MAX_SIZE = 100 * 1024 * 1024; // 100 MB

    @Override
    public void initialize(ValidVideoFile constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext context) {
        if (files == null || files.isEmpty()) {
            return false;
        }
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                return false;
            }
            String contentType = file.getContentType();
            if (contentType == null || !(contentType.equals("video/mp4"))) {
                return false;
            }
            if (file.getSize() > MAX_SIZE) {
                return false;
            }
        }
        return true;
    }
}