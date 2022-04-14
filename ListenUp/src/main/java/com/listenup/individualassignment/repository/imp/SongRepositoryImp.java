package com.listenup.individualassignment.repository.imp;

import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.repository.SongRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class SongRepositoryImp implements SongRepository {
    private final List<Song> songs;

    public SongRepositoryImp(){
        songs = new ArrayList<>();
    }
    public boolean addSong(int id, String name, Genre genre){
        return true;
    }
    public List<Song> getSongs(){
        return songs;
    }
    public boolean editSong(int id, String name, Genre genre){
        return true;
    }
    public boolean deleteSong(int id){
        return true;
    }
}
