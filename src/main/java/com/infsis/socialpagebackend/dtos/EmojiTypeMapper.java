package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.EmojiType;
import org.springframework.stereotype.Component;

@Component
public class EmojiTypeMapper {

    public EmojiTypeDTO toDTO(EmojiType emojiType) {
        EmojiTypeDTO emojiTypeDTO = new EmojiTypeDTO();
        emojiTypeDTO.setUuid(emojiType.getUuid());
        emojiTypeDTO.setEmoji_name(emojiType.getEmoji_name());
        emojiTypeDTO.setEmoji_code(emojiType.getEmoji_code());

        return emojiTypeDTO;
    }

    public EmojiType getEmojiType(EmojiTypeDTO emojiTypeDTO) {
        EmojiType emojiType = new EmojiType();
        emojiType.setUuid(emojiTypeDTO.getUuid());
        emojiType.setEmoji_name(emojiTypeDTO.getEmoji_name());
        emojiType.setEmoji_code(emojiTypeDTO.getEmoji_code());

        return emojiType;
    }
}
