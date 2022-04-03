package com.listenup.individualassignment.model;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SingleSong extends  Song{
    @NonNull
    private Date releasedDate;
    @NonNull
    private Date uploadedDate;
    private List<Artist> artists;

    public SingleSong(int id, String name, Genre genre, Date releasedDate, Date uploadedDate){
        super(id, name, genre);
        this.releasedDate = releasedDate;
        this.uploadedDate = uploadedDate;
    }
}
