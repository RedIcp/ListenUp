package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.business.SongService;
import com.listenup.individualassignment.repository.SongRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class SongServiceImp implements SongService {
    private final SongRepository db;

    public boolean addSong(Song song){
        boolean result = false;
        if(!songExist(song.getId())){
            getSongs().add(song);
            db.save(song);
            result = true;
        }
        return result;
    }

    public List<Song> getSongs(){
        return db.findAll();
    }

    public boolean editSong(Song song){
        boolean result = false;
        Song old = getSong(song.getId());
        if(old != null){
            old.setName(song.getName());
            old.setGenre(song.getGenre());
            db.save(song);
            result = true;
        }
        return result;
    }

    public boolean deleteSong(long id){
        boolean result = false;
        if(songExist(id)){
            getSongs().remove(getSong(id));
            db.delete(getSong(id));
            result = true;
        }
        return result;
    }

    public Song getSong(long id){
        for(Song song: getSongs()){
            if(song.getId() == id){
                return song;
            }
        }
        return null;
    }
    public boolean songExist(long id){
        boolean result = true;
        if(getSong(id)==null){
            result = false;
        }
        return result;
    }
}
