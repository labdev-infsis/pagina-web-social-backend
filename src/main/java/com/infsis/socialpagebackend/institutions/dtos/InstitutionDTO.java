package com.infsis.socialpagebackend.institutions.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InstitutionDTO {

    private String uuid;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 3, max = 300)
    private String description;

    @NotBlank
    @Size(min = 3, max = 300)
    private String location;

    @NotBlank
    @Size(min = 3, max = 100)
    private String category;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 7, max = 15)
    private String phone;

    @NotBlank
    @Size(min = 3, max = 80)
    private String url;

    @NotBlank
    @Size(min = 3, max = 100)
    private String logo_url;

    @NotBlank
    @Size(min = 3, max = 100)
    private String background_url;

    public InstitutionDTO() {
    }

    public InstitutionDTO(String uuid, String name, String description, String location,
                          String category, String email, String phone,
                          String url, String logo_url, String background_url) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        this.email = email;
        this.phone = phone;
        this.url = url;
        this.logo_url = logo_url;
        this.background_url = background_url;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getBackground_url() {
        return background_url;
    }

    public void setBackground_url(String background_url) {
        this.background_url = background_url;
    }
}
