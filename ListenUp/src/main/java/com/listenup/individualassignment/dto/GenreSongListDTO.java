package com.listenup.individualassignment.dto;

import java.util.List;

import com.listenup.individualassignment.dto.createupdate.SongDTO;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreSongListDTO {
    private long id;
    private List<SongDTO> songs;
}
