package com.listenup.individualassignment.model;

import lombok.*;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album")
public class Album {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Length(min = 1, max = 50)
    @Column(name = "name")
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<AlbumSong> albumSongs;

    @Column(name = "released_date")
    private Date releasedDate;

    @Column(name = "uploaded_date")
    private Date uploadedDate;
}
