package com.listenup.individualassignment.business.artist.imp;

import com.listenup.individualassignment.business.artist.GetArtistsUseCase;
import com.listenup.individualassignment.business.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
import com.listenup.individualassignment.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetArtistsUseCaseImp implements GetArtistsUseCase {
    private final ArtistRepository db;

    @Override
    public List<ArtistDTO> getArtists(){
        return ArtistDTOConverter.convertToDTOList(db.findAll());
    }
}
