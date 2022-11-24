package com.listenup.individualassignment.business.artist.imp;

import com.listenup.individualassignment.business.artist.GetArtistSongsUseCase;
import com.listenup.individualassignment.business.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.business.dtoconverter.SongListDTOConverter;
import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.repository.ArtistRepository;
import com.listenup.individualassignment.repository.entity.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetArtistSongsUseCaseImp implements GetArtistSongsUseCase {
    private final ArtistRepository db;
    private final SongListDTOConverter songList;

    @Override
    public ArtistSongListDTO getArtistSongs(long id){
        if(db.existsById(id)){
            Artist artist = db.getById(id);

            ArtistSongListDTO artistDTO = ArtistDTOConverter.convertToDTOForSong(db.getById(id));
            artistDTO.setSongs(songList.convertToSingleSongDTOList(artist.getSongs()));

            return artistDTO;
        }
        return null;
    }
}
