package com.listenup.individualassignment.business.artist.imp;

import com.listenup.individualassignment.business.artist.GetArtistSongsUseCase;
import com.listenup.individualassignment.business.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class GetArtistSongsUseCaseImp implements GetArtistSongsUseCase {
    private final ArtistRepository db;

    @Override
    public ArtistSongListDTO getArtistSongs(long id){
        return ArtistDTOConverter.convertToDTOForSong(db.getById(id));
    }
}
