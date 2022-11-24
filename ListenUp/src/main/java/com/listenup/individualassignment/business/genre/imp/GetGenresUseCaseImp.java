package com.listenup.individualassignment.business.genre.imp;

import com.listenup.individualassignment.business.genre.GetGenresUseCase;
import com.listenup.individualassignment.business.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import com.listenup.individualassignment.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class GetGenresUseCaseImp implements GetGenresUseCase {
    private final GenreRepository db;

    @Override
    public List<GenreDTO> getGenres(){
        return GenreDTOConverter.convertToDTOList(db.findAll());
    }
}
