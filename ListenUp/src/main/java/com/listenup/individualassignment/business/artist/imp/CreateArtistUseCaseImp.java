package com.listenup.individualassignment.business.artist.imp;

import com.listenup.individualassignment.business.artist.CreateArtistUseCase;
import com.listenup.individualassignment.business.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.dto.createdto.CreateArtistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistResponseDTO;
import com.listenup.individualassignment.repository.entity.Artist;
import com.listenup.individualassignment.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class CreateArtistUseCaseImp implements CreateArtistUseCase {
    private final ArtistRepository db;

    @Override
    public CreateArtistResponseDTO addArtist(CreateArtistRequestDTO artist){
        Artist savedArtist = db.save(ArtistDTOConverter.convertToModelForCreate(artist));

        return CreateArtistResponseDTO.builder()
                .artistID(savedArtist.getId())
                .build();
    }
}
