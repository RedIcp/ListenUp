package com.listenup.individualassignment.business.genre.imp;

import com.listenup.individualassignment.business.genre.GetGenreSongsUseCase;
import com.listenup.individualassignment.business.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetGenreSongsUseCaseImp implements GetGenreSongsUseCase {
    private final GenreRepository db;

    @Override
    public GenreSongListDTO getGenreSongs(long id){
        return GenreDTOConverter.convertToDTOForSong(db.getById(id));
    }
}
