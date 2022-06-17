package com.listenup.individualassignment.dto;

import java.util.List;

import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSongListDTO {
    private long id;
    private String name;
    private String customer;
    private long likedNumber;
    private List<SingleSongDTO> songs;
}
