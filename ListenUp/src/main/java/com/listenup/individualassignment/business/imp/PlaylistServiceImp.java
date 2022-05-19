package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.business.exception.InvalidPlaylistException;
import com.listenup.individualassignment.business.imp.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;
import com.listenup.individualassignment.entity.Playlist;
import com.listenup.individualassignment.business.PlaylistService;
import com.listenup.individualassignment.repository.PlaylistRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class PlaylistServiceImp implements PlaylistService {
    private final PlaylistRepository db;

    public CreatePlaylistResponseDTO addPlaylist(CreatePlaylistRequestDTO playlist){
        Playlist savedPlaylist = db.save(PlaylistDTOConverter.convertToModelForCreate(playlist));

        return CreatePlaylistResponseDTO.builder()
                .playlistID(savedPlaylist.getId())
                .build();
    }

    public List<PlaylistDTO> getPlaylists(){
        return PlaylistDTOConverter.convertToDTOList(db.findAll());
    }
    public PlaylistSongListDTO getPlaylistSong(long id){
        return PlaylistDTOConverter.convertToDTOForSong(db.getById(id));
    }

    public PlaylistDTO editPlaylist(PlaylistDTO playlist){
        if(!db.existsById(playlist.getId())){
            throw new InvalidPlaylistException("INVALID_ID");
        }
        db.save(PlaylistDTOConverter.convertToModelForUpdate(playlist));
        return playlist;
    }
    public PlaylistSongListDTO editPlaylistSongs(PlaylistSongListDTO playlist){
        Playlist old = db.getById(playlist.getId());
        if(!db.existsById(playlist.getId())){
            throw new InvalidPlaylistException("INVALID_ID");
        }
        old.setSongs(SongDTOConverter.convertToSingleSongModelList(playlist.getSongs()));
        db.save(old);
        return playlist;
    }

    public boolean deletePlaylist(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
