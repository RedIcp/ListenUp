package com.listenup.individualassignment.business.artist.imp;

import com.listenup.individualassignment.business.artist.UpdateArtistUseCase;
import com.listenup.individualassignment.business.exception.InvalidArtistException;
import com.listenup.individualassignment.business.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
import com.listenup.individualassignment.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class UpdateArtistUseCaseImp implements UpdateArtistUseCase {
    private final ArtistRepository db;

    @Override
    public ArtistDTO editArtist(ArtistDTO artist){
        if(!db.existsById(artist.getId())){
            throw new InvalidArtistException("INVALID_ARTIST");
        }
        db.save(ArtistDTOConverter.convertToModelForUpdate(artist));
        return artist;
    }
}
