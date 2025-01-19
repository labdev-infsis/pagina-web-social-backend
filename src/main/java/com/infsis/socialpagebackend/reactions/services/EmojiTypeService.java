package com.infsis.socialpagebackend.reactions.services;

import com.infsis.socialpagebackend.replies.dto.EmojiTypeDTO;
import com.infsis.socialpagebackend.reactions.mappers.EmojiTypeMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.reactions.models.EmojiType;
import com.infsis.socialpagebackend.reactions.repositories.EmojiTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmojiTypeService {

    @Autowired
    private EmojiTypeRepository emojiTypeRepository;

    @Autowired
    private EmojiTypeMapper emojiTypeMapper;

    public EmojiTypeDTO getEmojiType(String emojiTypeUuid) {
        EmojiType emojiType = emojiTypeRepository.findOneByUuid(emojiTypeUuid);
        if(emojiType == null) {
            throw new NotFoundException("EmojiType:", emojiTypeUuid);
        }
        return emojiTypeMapper.toDTO(emojiType);
    }

    public List<EmojiTypeDTO> getAllEmojiType() {
        return emojiTypeRepository
                .findAll()
                .stream()
                .map(emojiType -> emojiTypeMapper.toDTO(emojiType))
                .collect(Collectors.toList());
    }

    public EmojiTypeDTO saveEmojiType(EmojiTypeDTO emojiTypeDTO) {
        EmojiType emojiType = emojiTypeMapper.getEmojiType(emojiTypeDTO);
        emojiTypeRepository.save(emojiType);

        return emojiTypeMapper.toDTO(emojiType);
    }
}
