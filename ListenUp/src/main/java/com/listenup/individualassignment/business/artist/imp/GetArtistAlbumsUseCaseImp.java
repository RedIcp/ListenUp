package com.listenup.individualassignment.business.artist.imp;

import com.listenup.individualassignment.business.artist.GetArtistAlbumsUseCase;
import com.listenup.individualassignment.business.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.dto.ArtistAlbumListDTO;
import com.listenup.individualassignment.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class GetArtistAlbumsUseCaseImp implements GetArtistAlbumsUseCase {
    private final ArtistRepository db;

    @Override
    public ArtistAlbumListDTO getArtistAlbums(long id){
        return ArtistDTOConverter.convertToDTOForAlbum(db.getById(id));
    }
}
