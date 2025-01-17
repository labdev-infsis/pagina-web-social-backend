package com.infsis.socialpagebackend.social_networks.mappers;

import com.infsis.socialpagebackend.institutions.models.Institution;
import com.infsis.socialpagebackend.social_networks.dtos.SocialNetworkDTO;
import com.infsis.socialpagebackend.social_networks.models.SocialNetwork;
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
