package com.infsis.socialpagebackend.reactions.controllers;

import com.infsis.socialpagebackend.replies.dto.EmojiTypeDTO;
import com.infsis.socialpagebackend.reactions.services.EmojiTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emoji-type")
@Validated
public class EmojiTypeController {

    @Autowired
    private EmojiTypeService emojiTypeService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    @GetMapping("/{emojiTypeUuid}")
    public EmojiTypeDTO get(@PathVariable String emojiTypeUuid) {
        return emojiTypeService.getEmojiType(emojiTypeUuid);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    @GetMapping
    public List<EmojiTypeDTO> getAll() {
        return emojiTypeService.getAllEmojiType();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmojiTypeDTO create(@Valid @RequestBody EmojiTypeDTO emojiTypeDTO) {
        return emojiTypeService.saveEmojiType(emojiTypeDTO);
    }
}
