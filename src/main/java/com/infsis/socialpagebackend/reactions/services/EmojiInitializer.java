package com.infsis.socialpagebackend.reactions.services;

import com.infsis.socialpagebackend.replies.dto.EmojiTypeDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmojiInitializer {

    @Autowired
    private EmojiTypeService emojiTypeService;

    @PostConstruct
    public void initEmojis() {
        if (emojiTypeService.getAllEmojiType().isEmpty()) {
            emojiTypeService.saveEmojiType(new EmojiTypeDTO("thumbs-up", "👍", "3f696a78-c73f-475c-80a6-f5a858648af1"));
            emojiTypeService.saveEmojiType(new EmojiTypeDTO("red-heart", "❤️", "7v236a78-c73f-475c-80a6-f5a858648af1"));
            emojiTypeService.saveEmojiType(new EmojiTypeDTO("crying-face", "😢", "n1596a78-c73f-475c-80a6-f5a858648af1"));
            emojiTypeService.saveEmojiType(new EmojiTypeDTO("angry-face", "😡", "4c806a78-c73f-475c-80a6-f5a858648af1"));
            emojiTypeService.saveEmojiType(new EmojiTypeDTO("grinning-squinting-face", "😆", "l6m3bd82-c73f-475c-80a6-f5a858648af1"));
            emojiTypeService.saveEmojiType(new EmojiTypeDTO("astonished-face", "😲", "c5n1m4f0-c73f-475c-80a6-f5a858648af1"));

     
        } else {
           
        }
    }
}
