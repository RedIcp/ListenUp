package com.listenup.individualassignment.dto;

import lombok.Data;
import java.util.List;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.listenup.individualassignment.model.Song;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistSongsDTO {
    private long id;
    private List<Song> songs;
}
