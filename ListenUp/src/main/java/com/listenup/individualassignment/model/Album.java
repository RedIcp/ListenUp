package com.listenup.individualassignment.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Album {
    private int id;
    @NonNull
    private String name;
    private Artist artist;
    private List<AlbumSong> songs;
    @NonNull
    private Date releasedDate;
    @NonNull
    private Date uploadedDate;

    public Album(int id, String name, Artist artist, Date releasedDate, Date uploadedDate){
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.releasedDate = releasedDate;
        this.uploadedDate = uploadedDate;

        this.songs = new ArrayList<>();
    }
}
