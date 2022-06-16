package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.business.exception.InvalidGenreException;
import com.listenup.individualassignment.business.imp.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import com.listenup.individualassignment.business.GenreService;
import com.listenup.individualassignment.entity.Genre;
import com.listenup.individualassignment.repository.GenreRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class GenreServiceImp implements GenreService {
    private final GenreRepository db;

    @Override
    public CreateGenreResponseDTO addGenre(CreateGenreRequestDTO genre){
        Genre savedGenre = db.save(GenreDTOConverter.convertToModelForCreate(genre));

        return CreateGenreResponseDTO.builder()
                .genreID(savedGenre.getId())
                .build();
    }

    @Override
    public List<GenreDTO> getGenres(){
        return GenreDTOConverter.convertToDTOList(db.findAll());
    }

    @Override
    public GenreSongListDTO getGenreSongs(long id){
        return GenreDTOConverter.convertToDTOForSong(db.getById(id));
    }

    @Override
    public GenreDTO editGenre(GenreDTO genre){
        if(!db.existsById(genre.getId())){
            throw new InvalidGenreException("INVALID_ID");
        }
        db.save(GenreDTOConverter.convertToModelForUpdate(genre));
        return genre;
    }

    @Override
    public boolean deleteGenre(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
