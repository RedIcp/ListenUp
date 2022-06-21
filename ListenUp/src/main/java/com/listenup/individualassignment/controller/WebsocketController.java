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
    public MessageDTO greeting(CreateAlbumRequestDTO message){
        return MessageDTO.builder()
                .text("New album is created:" + message.getName())
                .build();
    }

}
