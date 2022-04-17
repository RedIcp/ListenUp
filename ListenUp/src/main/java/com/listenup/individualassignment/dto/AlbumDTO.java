package com.listenup.individualassignment.dto;

import com.listenup.individualassignment.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {
    private int id;
    private String name;
    private Artist artist;
    private Date releasedDate;
    private Date uploadedDate;
}
