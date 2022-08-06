package com.listenup.individualassignment.repository.entity;

import lombok.*;
import java.util.Date;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "single_song")
public class SingleSong extends  Song{
    public SingleSong(String name, Artist artist, Genre genre, Date releasedDate, Date uploadedDate){
        super(name, artist, genre, releasedDate, uploadedDate);
    }
    public SingleSong(long id, String name, Artist artist, Genre genre, Date releasedDate, Date uploadedDate){
        super(id, name, artist, genre, releasedDate, uploadedDate);
    }
}
