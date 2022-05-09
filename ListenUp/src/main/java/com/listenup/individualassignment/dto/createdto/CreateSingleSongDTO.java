package com.listenup.individualassignment.dto.createdto;

import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSingleSongDTO {
    private String name;
    private ArtistDTO artist;
    private GenreDTO genre;
    private Date releasedDate;
    private Date uploadedDate;
}
