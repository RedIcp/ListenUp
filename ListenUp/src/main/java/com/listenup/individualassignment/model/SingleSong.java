package com.listenup.individualassignment.model;

import java.util.Date;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "single_song")
@Data
@AllArgsConstructor
public class SingleSong extends  Song{
    public SingleSong(int id, String name, Artist artist, Genre genre, Date released_date, Date uploaded_date){
        super(id, name, artist, genre, released_date, uploaded_date);
    }
}
