package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.Playlist;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createupdate.PlaylistDTO;

public interface PlaylistService {
    //dto
    Playlist playlistDTOConvertor(PlaylistDTO dto);
    PlaylistSongListDTO playlistObjConvertor(Playlist playlist);
    List<PlaylistDTO> getPlaylistDTOs(List<Playlist> playlists);

    //crud
    boolean addPlaylist(Playlist playlist);
    List<Playlist> getPlaylists();
    Playlist getPlaylist(long id);
    boolean editPlaylist(Playlist playlist);
    boolean deletePlaylist(long id);
}
