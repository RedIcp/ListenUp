package com.listenup.individualassignment.dto.CreateUpdate;

import lombok.Data;
import lombok.Builder;
import java.util.Date;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.listenup.individualassignment.model.Artist;

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
