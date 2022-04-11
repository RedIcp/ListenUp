package com.listenup.individualassignment.model;

import java.util.Date;
import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleSong extends  Song{
    private Date releasedDate;
    private Date uploadedDate;
    private List<Artist> artists;

    public SingleSong(int id, String name, Genre genre, Date releasedDate, Date uploadedDate){
        super(id, name, genre);
        this.releasedDate = releasedDate;
        this.uploadedDate = uploadedDate;
    }
}
