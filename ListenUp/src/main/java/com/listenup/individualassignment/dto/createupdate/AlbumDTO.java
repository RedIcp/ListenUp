package com.listenup.individualassignment.dto.createupdate;

import java.util.Date;

import com.listenup.individualassignment.model.Artist;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {
    private long id;
    private String name;
    private Artist artist;
    private Date releasedDate;
    private Date uploadedDate;
}
