package com.infsis.socialpagebackend.dtos;

public class ContactInfoDTO {

    private String uuid;
    private String name;
    private String description;
    private String category;
    private String email;
    private String phone;
    private String url;
    private String logo_url;
    private String background_url;

    public ContactInfoDTO() {
    }

    public ContactInfoDTO(String uuid, String name, String description,
                          String category, String email, String phone,
                          String url, String logo_url, String background_url) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
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