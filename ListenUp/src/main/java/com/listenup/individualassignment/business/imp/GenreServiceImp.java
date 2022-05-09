package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.business.exception.InvalidGenreException;
import com.listenup.individualassignment.business.imp.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import com.listenup.individualassignment.business.GenreService;
import com.listenup.individualassignment.repository.GenreRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class GenreServiceImp implements GenreService {
    private final GenreRepository db;

    public CreateGenreDTO addGenre(CreateGenreDTO genre){
        db.save(GenreDTOConverter.convertToModelForCreate(genre));
        return genre;
    }

    public List<GenreDTO> getGenres(){
        return GenreDTOConverter.convertToDTOList(db.findAll());
    }
    public GenreSongListDTO getGenreSongs(long id){
        return GenreDTOConverter.convertToDTOForSong(db.getById(id));
    }

    public GenreDTO editGenre(GenreDTO genre){
        if(!db.existsById(genre.getId())){
            throw new InvalidGenreException("INVALID_ID");
        }
        db.save(GenreDTOConverter.convertToModelForUpdate(genre));
        return genre;
    }

    public boolean deleteGenre(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
