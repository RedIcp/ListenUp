package com.listenup.ListenUp.model;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Single extends  Song{
    private Date releasedDate;
    private Date uploadedDate;
    private List<Artist> artists;

    public Single(int id, String name, Genre genre, Date releasedDate, Date uploadedDate){
        super(id, name, genre);
        this.releasedDate = releasedDate;
        this.uploadedDate = uploadedDate;
    }
}
