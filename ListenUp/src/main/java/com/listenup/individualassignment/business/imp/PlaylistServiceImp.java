package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.business.exception.InvalidPlaylistException;
import com.listenup.individualassignment.business.exception.UnauthorizedDataAccessException;
import com.listenup.individualassignment.business.imp.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.AccessTokenDTO;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;
import com.listenup.individualassignment.entity.Playlist;
import com.listenup.individualassignment.business.PlaylistService;
import com.listenup.individualassignment.entity.RoleEnum;
import com.listenup.individualassignment.repository.PlaylistRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class PlaylistServiceImp implements PlaylistService {
    private final PlaylistRepository db;
    private final AccessTokenDTO requestAccessToken;

    final String error = "INVALID_ID";

    @Override
    public CreatePlaylistResponseDTO addPlaylist(CreatePlaylistRequestDTO playlist){
        Playlist savedPlaylist = db.save(PlaylistDTOConverter.convertToModelForCreate(playlist));

        return CreatePlaylistResponseDTO.builder()
                .playlistID(savedPlaylist.getId())
                .build();
    }

    @Override
    public List<PlaylistDTO> getPlaylists(){
        return PlaylistDTOConverter.convertToDTOList(db.findAll());
    }

    @Override
    public PlaylistSongListDTO getPlaylistSong(long id){
        return PlaylistDTOConverter.convertToDTOForSong(db.getById(id));
    }

    @Override
    public PlaylistDTO editPlaylist(PlaylistDTO playlist){
        isAuthorised(playlist.getCustomer().getId());
        if(!db.existsById(playlist.getId())){
            throw new InvalidPlaylistException(error);
        }
        db.save(PlaylistDTOConverter.convertToModelForUpdate(playlist));
        return playlist;
    }

    @Override
    public void addSongToPlaylist(AddRemoveSongToPlaylistDTO song){
        Playlist old = db.getById(song.getPlaylistID());
        isAuthorised(song.getCustomerID());
        if(!db.existsById(song.getPlaylistID())){
            throw new InvalidPlaylistException(error);
        }
        old.getSongs().add(SongDTOConverter.convertToSingleSongModelForUpdate(song.getSong()));

        db.save(old);
    }

    @Override
    public void removeSongFromPlaylist(AddRemoveSongToPlaylistDTO song){
        Playlist old = db.getById(song.getPlaylistID());
        isAuthorised(song.getCustomerID());
        if(!db.existsById(song.getPlaylistID())){
            throw new InvalidPlaylistException(error);
        }
        old.getSongs().remove(SongDTOConverter.convertToSingleSongModelForUpdate(song.getSong()));

        db.save(old);
    }

    private void isAuthorised(long id){
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name()) && requestAccessToken.getUserID() != id) {
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }
    }

    @Override
    public boolean deletePlaylist(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
