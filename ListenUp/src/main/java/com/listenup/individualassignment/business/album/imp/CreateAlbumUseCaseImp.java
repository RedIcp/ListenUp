package com.listenup.individualassignment.business.album.imp;

import com.listenup.individualassignment.business.album.CreateAlbumUseCase;
import com.listenup.individualassignment.business.dtoconverter.AlbumDTOConverter;
import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumResponseDTO;
import com.listenup.individualassignment.entity.Album;
import com.listenup.individualassignment.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class CreateAlbumUseCaseImp implements CreateAlbumUseCase {
    private final AlbumRepository db;

    @Override
    public CreateAlbumResponseDTO addAlbum(CreateAlbumRequestDTO album){
        Album savedAlbum = db.save(AlbumDTOConverter.convertToModelForCreate(album));

        return CreateAlbumResponseDTO.builder()
                .albumID(savedAlbum.getId())
                .build();
    }
}
