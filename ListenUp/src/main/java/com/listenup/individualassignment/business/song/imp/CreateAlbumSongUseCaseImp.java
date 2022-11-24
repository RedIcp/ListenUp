package com.listenup.individualassignment.business.song.imp;

import com.listenup.individualassignment.business.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.business.song.CreateAlbumSongUseCase;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongResponseDTO;
import com.listenup.individualassignment.repository.entity.Song;
import com.listenup.individualassignment.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class CreateAlbumSongUseCaseImp implements CreateAlbumSongUseCase {
    private final SongRepository db;

    @Override
    public CreateAlbumSongResponseDTO addAlbumSong(CreateAlbumSongRequestDTO song){
        Song savedSong = db.save(SongDTOConverter.convertToAlbumSongModelForCreate(song));
        return CreateAlbumSongResponseDTO.builder()
                .albumSongID(savedSong.getId())
                .build();
    }
}
