package com.listenup.individualassignment.business.genre.imp;

import com.listenup.individualassignment.business.genre.DeleteGenreUseCase;
import com.listenup.individualassignment.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class DeleteGenreUseCaseImp implements DeleteGenreUseCase {
    private final GenreRepository db;

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
