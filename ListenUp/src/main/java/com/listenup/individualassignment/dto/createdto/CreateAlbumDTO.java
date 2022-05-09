package com.listenup.individualassignment.dto.createdto;

import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlbumDTO {
    private String name;
    private ArtistDTO artist;
    private Date releasedDate;
    private Date uploadedDate;
}
