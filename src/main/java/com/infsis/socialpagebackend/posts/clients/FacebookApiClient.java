package com.infsis.socialpagebackend.posts.clients;

import com.infsis.socialpagebackend.configuration.RequestLoggingInterceptor;
import com.infsis.socialpagebackend.posts.dtos.FacebookPhotoResponseDTO;
import com.infsis.socialpagebackend.posts.dtos.MediaDTO;
import com.infsis.socialpagebackend.posts.dtos.PostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacebookApiClient {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    private final RestTemplate restTemplate;

    @Value("${meta.graph-api.url}")
    private String graphApiUrl;

    @Value("${meta.graph-api.page-id}")
    private String graphApiPageId;

    @Value("${meta.graph-api.page-access-token}")
    private String graphApiPageAccessToken;

    public FacebookApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void postPublication(PostDTO postDTO) {

        //String url = String.format("%s/feed?access_token=%s&appid=%s", graphApiUrl, graphApiPageAccessToken, graphApiPageAccessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("access_token", graphApiPageAccessToken);
        params.add("message", postDTO.getContent().getText());

        int i = 0;
        for (MediaDTO media : postDTO.getContent().getMedia()) {
            params.add("attached_media[" + i + "]", "{'media_fbid':'" + media.getFb_media_id() + "'}");
            i++;
        }

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(graphApiUrl + "/" + graphApiPageId + "/feed", request, String.class);

        logger.info("Successful Post in Facebook, Response Status: {} | Response Boddy : {}", response.getStatusCode(), response.getBody());


    }

    //Publish link images in Facebook
    private List<String> postLinkImages(List<MediaDTO> photos) {

        List<String> postedImages = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        for (MediaDTO mediaDTO : photos) {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("access_token", graphApiPageAccessToken);
            params.add("published", "false");
            params.add("url", mediaDTO.getPath());

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            ResponseEntity<FacebookPhotoResponseDTO> response = restTemplate.postForEntity(
                    graphApiUrl + "/" + graphApiPageId + "/photos", request , FacebookPhotoResponseDTO.class);

            postedImages.add(response.getBody().getId() != null ? response.getBody().getId() : "");
            logger.info("Successful posted photo in Facebook, Response Status: {} | Response Body : {}", response.getStatusCode(), response.getBody() );
        }

        return postedImages;
    }
}
