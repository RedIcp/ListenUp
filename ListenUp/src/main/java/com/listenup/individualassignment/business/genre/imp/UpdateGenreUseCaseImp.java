package com.listenup.individualassignment.business.genre.imp;

import com.listenup.individualassignment.business.exception.InvalidGenreException;
import com.listenup.individualassignment.business.genre.UpdateGenreUseCase;
import com.listenup.individualassignment.business.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import com.listenup.individualassignment.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class UpdateGenreUseCaseImp implements UpdateGenreUseCase {
    private final GenreRepository db;

    @Override
    public GenreDTO editGenre(GenreDTO genre){
        if(!db.existsById(genre.getId())){
            throw new InvalidGenreException("INVALID_ID");
        }
        db.save(GenreDTOConverter.convertToModelForUpdate(genre));
        return genre;
    }
}
