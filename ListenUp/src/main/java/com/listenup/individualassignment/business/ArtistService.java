package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.ArtistAlbumListDTO;
import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistDTO;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;

public interface ArtistService {
    CreateArtistDTO addArtist(CreateArtistDTO artist);
    List<ArtistDTO> getArtists();
    ArtistSongListDTO getArtistSongs(long id);
    ArtistAlbumListDTO getArtistAlbums(long id);
    ArtistDTO editArtist(ArtistDTO artist);
    boolean deleteArtist(long id);
}
