package com.listenup.individualassignment.business.song;

import com.listenup.individualassignment.dto.AccessTokenDTO;
import com.listenup.individualassignment.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SongIsLiked {
    private SongRepository db;
    private AccessTokenDTO requestAccessToken;

    public int isLiked(long songID){
        return db.songLiked(songID, requestAccessToken.getUserID());
    }
}
