package com.listenup.individualassignment.dto.vieweditdto;

import java.util.Date;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleSongDTO {
    private long id;
    private String name;
    private ArtistDTO artist;
    private GenreDTO genre;
    private Date releasedDate;
    private Date uploadedDate;
}
