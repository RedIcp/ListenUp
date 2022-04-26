package com.listenup.individualassignment.dto;

import com.listenup.individualassignment.dto.createupdate.AlbumDTO;

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
    private List<AlbumDTO> albums;
}
