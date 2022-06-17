package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebsocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public AlbumDTO greeting(CreateAlbumRequestDTO message) throws Exception {
        return AlbumDTO.builder()
                .name(message.getName())
                .artist(message.getArtist())
                .releasedDate(message.getReleasedDate())
                .uploadedDate(message.getUploadedDate())
                .build();
    }

}
