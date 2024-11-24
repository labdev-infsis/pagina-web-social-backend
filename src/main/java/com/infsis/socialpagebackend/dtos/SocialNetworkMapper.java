package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.*;
import org.springframework.stereotype.Component;

@Component
public class SocialNetworkMapper {

    public SocialNetworkDTO toDTO(SocialNetwork socialNetwork) {

        SocialNetworkDTO socialNetworkDTO = new SocialNetworkDTO();
        socialNetworkDTO.setUuid(socialNetwork.getUuid());
        socialNetworkDTO.setInstitution_id(socialNetwork.getInstitution().getUuid());
        socialNetworkDTO.setName(socialNetwork.getName());
        socialNetworkDTO.setLink(socialNetwork.getLink());

        return socialNetworkDTO;
    }

    public SocialNetwork getSocialNetwork(SocialNetworkDTO socialNetworkDTO, Institution institution) {

        SocialNetwork socialNetwork = new SocialNetwork();

        socialNetwork.setInstitution(institution);
        socialNetwork.setName(socialNetworkDTO.getName());
        socialNetwork.setLink(socialNetworkDTO.getLink());

        return socialNetwork;
    }
}
