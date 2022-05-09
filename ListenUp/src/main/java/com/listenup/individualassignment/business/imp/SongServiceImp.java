package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.business.exception.InvalidSongException;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongDTO;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.business.SongService;
import com.listenup.individualassignment.repository.SongRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class SongServiceImp implements SongService {
    private final SongRepository db;

    public CreateSingleSongDTO addSingleSong(CreateSingleSongDTO song){
        db.save(SongDTOConverter.convertToSingleSongModelForCreate(song));
        return song;
    }

    public CreateAlbumSongDTO addAlbumSong(CreateAlbumSongDTO song){
        db.save(SongDTOConverter.convertToAlbumSongModelForCreate(song));
        return song;
    }

    public List<SingleSongDTO> getSongs(){
        return SongDTOConverter.convertToSingleSongDTOList(db.findAll());
    }
    public SingleSongDTO getSong(long id){
        return SongDTOConverter.convertToSingleSongDTO(db.getById(id));
    }

    public SingleSongDTO editSong(SingleSongDTO song){
        if(!db.existsById(song.getId())){
            throw new InvalidSongException("INVALID_ID");
        }
        db.save(SongDTOConverter.convertToSingleSongModelForUpdate(song));
        return song;
    }

    public boolean deleteSong(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
