package com.listenup.individualassignment.dto;

import java.util.List;

import com.listenup.individualassignment.dto.vieweditdto.AlbumSongDTO;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumSongListDTO {
    private long id;
    private String name;
    private List<AlbumSongDTO> songs;
}
