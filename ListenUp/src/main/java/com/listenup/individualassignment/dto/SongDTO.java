package com.listenup.individualassignment.dto;

import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
