package com.listenup.individualassignment.business.playlist.imp;

import com.listenup.individualassignment.business.exception.InvalidPlaylistException;
import com.listenup.individualassignment.business.playlist.RemoveSongFromPlaylistUseCase;
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
public class RemoveSongFromPlaylistUseCaseImp implements RemoveSongFromPlaylistUseCase {
    private final PlaylistRepository db;
    private final IsAuthorised authorised;

    @Override
    public void removeSongFromPlaylist(AddRemoveSongToPlaylistDTO song){
        authorised.isAuthorised(song.getCustomerID());

        if(!db.existsById(song.getPlaylistID())){
            throw new InvalidPlaylistException("INVALID_PLAYLIST");
        }

        db.removePlaylistSong(song.getSongID(), song.getPlaylistID());
    }
}
