package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.business.exception.InvalidArtistException;
import com.listenup.individualassignment.business.exception.InvalidGenreException;
import com.listenup.individualassignment.business.exception.InvalidSongException;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongResponseDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.business.SongService;
import com.listenup.individualassignment.entity.SingleSong;
import com.listenup.individualassignment.entity.Song;
import com.listenup.individualassignment.repository.ArtistRepository;
import com.listenup.individualassignment.repository.GenreRepository;
import com.listenup.individualassignment.repository.SongRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class SongServiceImp implements SongService {
    private final SongRepository db;

    @Async
    @Override
    public CreateSingleSongResponseDTO addSingleSong(CreateSingleSongRequestDTO song){
        Song savedSong = db.save(SongDTOConverter.convertToSingleSongModelForCreate(song));

        return CreateSingleSongResponseDTO.builder()
                .singleSongID(savedSong.getId())
                .build();
    }

    @Async
    @Override
    public CreateAlbumSongResponseDTO addAlbumSong(CreateAlbumSongRequestDTO song){
        Song savedSong = db.save(SongDTOConverter.convertToAlbumSongModelForCreate(song));
        return CreateAlbumSongResponseDTO.builder()
                .albumSongID(savedSong.getId())
                .build();
    }

    @Async
    @Override
    public List<SingleSongDTO> getSongs(){
        return SongDTOConverter.convertToSingleSongDTOList(db.findAll());
    }

    @Async
    @Override
    public SingleSongDTO getSong(long id){
        return SongDTOConverter.convertToSingleSongDTO(db.getById(id));
    }

    @Async
    @Override
    public SingleSongDTO editSong(SingleSongDTO song){
        if(!db.existsById(song.getId())){
            throw new InvalidSongException("INVALID_ID");
        }
        db.save(SongDTOConverter.convertToSingleSongModelForUpdate(song));
        return song;
    }

    @Async
    @Override
    public boolean deleteSong(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
