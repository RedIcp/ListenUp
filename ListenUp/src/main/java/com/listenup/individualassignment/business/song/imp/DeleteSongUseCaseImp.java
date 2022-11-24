package com.listenup.individualassignment.business.song.imp;

import com.listenup.individualassignment.business.song.DeleteSongUseCase;
import com.listenup.individualassignment.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class DeleteSongUseCaseImp implements DeleteSongUseCase {
    private final SongRepository db;

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
