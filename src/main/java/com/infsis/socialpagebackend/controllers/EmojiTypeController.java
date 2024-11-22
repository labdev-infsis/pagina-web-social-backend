package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.EmojiTypeDTO;
import com.infsis.socialpagebackend.services.EmojiTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emoji-type")
@Validated
public class EmojiTypeController {

    @Autowired
    private EmojiTypeService emojiTypeService;

    @GetMapping("/{emojiTypeUuid}")
    public EmojiTypeDTO get(@PathVariable String emojiTypeUuid) {
        return emojiTypeService.getEmojiType(emojiTypeUuid);
    }

    @GetMapping
    public List<EmojiTypeDTO> getAll() {
        return emojiTypeService.getAllEmojiType();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmojiTypeDTO create(@Valid @RequestBody EmojiTypeDTO emojiTypeDTO) {
        return emojiTypeService.saveEmojiType(emojiTypeDTO);
    }
}
