package com.infsis.socialpagebackend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class VideoFileValidator implements ConstraintValidator<ValidVideoFile, MultipartFile> {

    @Override
    public void initialize(ValidVideoFile constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null) {
            return false;
        }
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("video/mp4") || contentType.equals("video/mpeg"));
    }
}