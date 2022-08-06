package com.listenup.individualassignment.business.playlist.imp;

import com.listenup.individualassignment.business.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.playlist.GetPlaylistSongsUseCase;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetPlaylistSongsUseCaseImp implements GetPlaylistSongsUseCase {
    private final PlaylistRepository db;

    @Override
    public PlaylistSongListDTO getPlaylistSongs(long id){
        return PlaylistDTOConverter.convertToDTOForSong(db.getById(id));
    }
}
