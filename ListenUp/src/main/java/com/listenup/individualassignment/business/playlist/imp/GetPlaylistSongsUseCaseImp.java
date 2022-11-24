package com.listenup.individualassignment.business.playlist.imp;

import com.listenup.individualassignment.business.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.dtoconverter.SongListDTOConverter;
import com.listenup.individualassignment.business.playlist.GetPlaylistSongsUseCase;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.repository.PlaylistRepository;
import com.listenup.individualassignment.repository.entity.Playlist;
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
    private final SongListDTOConverter songList;

    @Override
    public PlaylistSongListDTO getPlaylistSongs(long id){
        if (db.existsById(id)) {
            Playlist playlist = db.getById(id);

            PlaylistSongListDTO playlistDTO = PlaylistDTOConverter.convertToDTOForSong(playlist);
            playlistDTO.setSongs(songList.convertToSingleSongDTOList(playlist.getSongs()));

            return playlistDTO;
        }
        return null;
    }
}
