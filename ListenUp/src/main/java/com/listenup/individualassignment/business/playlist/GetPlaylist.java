package com.listenup.individualassignment.business.playlist;

import com.listenup.individualassignment.repository.entity.Playlist;
import com.listenup.individualassignment.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPlaylist {
    private PlaylistRepository db;

    public Playlist getPlaylist(long id){
        return db.getById(id);
    }
}
