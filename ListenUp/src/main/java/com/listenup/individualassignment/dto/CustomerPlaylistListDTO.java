package com.listenup.individualassignment.dto;

import lombok.Data;
import java.util.List;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.listenup.individualassignment.model.Playlist;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPlaylistListDTO {
    private long id;
    private List<Playlist> playlists;
}
