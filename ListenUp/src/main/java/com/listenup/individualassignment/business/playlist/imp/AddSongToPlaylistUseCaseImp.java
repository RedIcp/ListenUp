package com.listenup.individualassignment.business.playlist.imp;

import com.listenup.individualassignment.business.exception.InvalidPlaylistException;
import com.listenup.individualassignment.business.exception.SongAlreadyExistsInPlaylistException;
import com.listenup.individualassignment.business.playlist.AddSongToPlaylistUseCase;
import com.listenup.individualassignment.business.song.GetSong;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToPlaylistDTO;
import com.listenup.individualassignment.repository.entity.Playlist;
import com.listenup.individualassignment.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class AddSongToPlaylistUseCaseImp implements AddSongToPlaylistUseCase {
    private final PlaylistRepository db;
    private final IsAuthorised authorised;
    private final GetSong getSong;

    @Override
    public void addSongToPlaylist(AddRemoveSongToPlaylistDTO song){
        Playlist old = db.getById(song.getPlaylistID());

        authorised.isAuthorised(song.getCustomerID());

        if(!db.existsById(song.getPlaylistID())){
            throw new InvalidPlaylistException("INVALID_PLAYLIST");
        }

        if(db.songExistInPlaylist(song.getPlaylistID(), song.getSongID())==1){
            throw new SongAlreadyExistsInPlaylistException("SONG_ALREADY_IN_PLAYLIST");
        }

        old.getSongs().add(getSong.getSong(song.getSongID()));

        db.save(old);
    }
}
