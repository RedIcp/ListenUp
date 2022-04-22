package com.listenup.individualassignment.dto.CreateUpdate;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.model.Artist;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private long id;
    private String name;
    private Artist artist;
    private Genre genre;
    private Date releasedDate;
    private Date uploadedDate;
}
