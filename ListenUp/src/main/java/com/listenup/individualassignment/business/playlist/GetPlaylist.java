package com.listenup.individualassignment.business.playlist;

import com.listenup.individualassignment.business.exception.InvalidPlaylistException;
import com.listenup.individualassignment.repository.entity.Playlist;
import com.listenup.individualassignment.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPlaylist {
    private PlaylistRepository db;

    public Playlist getPlaylist(long id){
        if(!db.existsById(id)){
            throw new InvalidPlaylistException("INVALID_PLAYLIST");
        }
        return db.getById(id);
    }
}
