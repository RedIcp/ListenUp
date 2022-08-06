package com.listenup.individualassignment.business.song;

import com.listenup.individualassignment.business.exception.InvalidSongException;
import com.listenup.individualassignment.repository.entity.Song;
import com.listenup.individualassignment.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetSong {
    private SongRepository db;

    public Song getSong(long id){
        if(!db.existsById(id)){
            throw new InvalidSongException("INVALID_SONG_ID");
        }
        return db.getById(id);
    }
}
