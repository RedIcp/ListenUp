package com.listenup.individualassignment.dto;

import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistAlbumListDTO {
    private long id;
    private String name;
    private List<AlbumDTO> albums;
}
