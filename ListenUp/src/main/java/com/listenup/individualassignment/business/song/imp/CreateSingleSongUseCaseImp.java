package com.listenup.individualassignment.business.song.imp;

import com.listenup.individualassignment.business.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.business.song.CreateSingleSongUseCase;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongResponseDTO;
import com.listenup.individualassignment.entity.Song;
import com.listenup.individualassignment.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class CreateSingleSongUseCaseImp implements CreateSingleSongUseCase {
    private final SongRepository db;

    @Override
    public CreateSingleSongResponseDTO addSingleSong(CreateSingleSongRequestDTO song){
        Song savedSong = db.save(SongDTOConverter.convertToSingleSongModelForCreate(song));

        return CreateSingleSongResponseDTO.builder()
                .singleSongID(savedSong.getId())
                .build();
    }

}
