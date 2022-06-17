package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.dto.MessageDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public MessageDTO greeting(CreateAlbumRequestDTO message) throws Exception {
        return MessageDTO.builder()
                .message("New album is created:" + message.getName())
                .build();
    }

}
