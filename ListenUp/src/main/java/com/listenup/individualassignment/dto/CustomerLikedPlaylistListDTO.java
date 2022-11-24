package com.listenup.individualassignment.dto;

import java.util.List;

import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLikedPlaylistListDTO {
    private long id;
    private List<PlaylistDTO> playlists;
}
