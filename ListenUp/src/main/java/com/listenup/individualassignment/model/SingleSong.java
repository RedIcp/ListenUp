package com.listenup.individualassignment.model;

import java.util.Date;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "single_song")
@Data
@AllArgsConstructor
public class SingleSong extends  Song{
    public SingleSong(long id, String name, Artist artist, Genre genre, Date releasedDate, Date uploadedDate){
        super(id, name, artist, genre, releasedDate, uploadedDate);
    }
}
