package com.listenup.individualassignment.dto.createdto;

import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlbumSongRequestDTO {
    private String name;
    private GenreDTO genre;
    private AlbumDTO album;
}
