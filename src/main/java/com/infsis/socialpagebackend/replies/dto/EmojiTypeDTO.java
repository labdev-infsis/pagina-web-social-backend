package com.infsis.socialpagebackend.replies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmojiTypeDTO {

    private String uuid;

    @NotBlank
    @Size(min = 3, max = 20)
    private String emoji_name;

    @NotBlank
    @Size(min = 1, max = 5)
    private String emoji_code;

    public EmojiTypeDTO() {
      
    }
    

    public EmojiTypeDTO(String emoji_name, String emoji_code, String uuid) {
        this.emoji_name = emoji_name;
        this.emoji_code = emoji_code;
        this.uuid = uuid;
    }
}

