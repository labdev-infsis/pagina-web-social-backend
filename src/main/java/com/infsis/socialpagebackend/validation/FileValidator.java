package com.infsis.socialpagebackend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class FileValidator implements ConstraintValidator<ValidDocumentFile, MultipartFile> {

    @Override
    public void initialize(ValidDocumentFile validDocumentFile) {
        log.info("File validator initialized!!");
    }

    @Override
    public boolean isValid(MultipartFile multipartFile,
                           ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validating file");
        String contentType = multipartFile.getContentType();
        assert contentType != null;
        return isSupportedContentType(contentType);
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("application/pdf");
    }
}
