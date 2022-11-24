package com.listenup.individualassignment.business.album.imp;

import com.listenup.individualassignment.business.album.DeleteAlbumUseCase;
import com.listenup.individualassignment.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class DeleteAlbumUseCaseImp implements DeleteAlbumUseCase {
    private final AlbumRepository db;

    @Override
    public boolean deleteAlbum(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
