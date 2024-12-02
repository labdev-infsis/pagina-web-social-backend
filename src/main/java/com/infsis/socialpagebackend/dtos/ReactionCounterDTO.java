package com.infsis.socialpagebackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ReactionCounterDTO {

    private String my_reaction_emoji;

    private Integer total_reactions;

    private List<ReactionItemDTO> reactions_by_type;

    private List<ReactionUserDTO> reactions_by_user;
}
