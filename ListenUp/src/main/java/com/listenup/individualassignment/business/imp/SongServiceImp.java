package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.SongService;
import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class SongServiceImp implements SongService {
    private final SongRepository db;

    public boolean addSong(Song song){
        boolean result = false;
        if(!songExist(song.getId())){
            getSongs().add(song);
            db.addSong(song.getId(), song.getName(), song.getGenre());
            result = true;
        }
        return result;
    }

    public List<Song> getSongs(){
        return db.getSongs();
    }

    public boolean editSong(Song song){
        boolean result = false;
        Song old = getSong(song.getId());
        if(old != null){
            old.setName(song.getName());
            old.setGenre(song.getGenre());
            db.editSong(song.getId(), song.getName(), song.getGenre());
            result = true;
        }
        return result;
    }

    public boolean deleteSong(int id){
        boolean result = false;
        if(songExist(id)){
            getSongs().remove(getSong(id));
            db.deleteSong(id);
            result = true;
        }
        return result;
    }

    public Song getSong(int id){
        for(Song song: getSongs()){
            if(song.getId() == id){
                return song;
            }
        }
        return null;
    }
    public boolean songExist(int id){
        boolean result = true;
        if(getSong(id)==null){
            result = false;
        }
        return result;
    }
}