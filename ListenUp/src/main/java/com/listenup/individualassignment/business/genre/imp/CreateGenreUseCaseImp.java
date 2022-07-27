package com.listenup.individualassignment.business.genre.imp;

import com.listenup.individualassignment.business.genre.CreateGenreUseCase;
import com.listenup.individualassignment.business.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.dto.createdto.CreateGenreRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreResponseDTO;
import com.listenup.individualassignment.entity.Genre;
import com.listenup.individualassignment.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class CreateGenreUseCaseImp implements CreateGenreUseCase {
    private final GenreRepository db;

    @Override
    public CreateGenreResponseDTO addGenre(CreateGenreRequestDTO genre){
        Genre savedGenre = db.save(GenreDTOConverter.convertToModelForCreate(genre));

        return CreateGenreResponseDTO.builder()
                .genreID(savedGenre.getId())
                .build();
    }
}
