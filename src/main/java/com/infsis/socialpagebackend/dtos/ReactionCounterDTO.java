package com.infsis.socialpagebackend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ReactionCounterDTO {

    private String my_reaction_emoji;

    private Integer total_reactions;

    private List<ReactionItemDTO> reactions;
}
