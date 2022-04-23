package com.listenup.individualassignment.dto;

import java.util.List;

import com.listenup.individualassignment.model.Song;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistSongListDTO {
    private long id;
    private List<Song> songs;
}
