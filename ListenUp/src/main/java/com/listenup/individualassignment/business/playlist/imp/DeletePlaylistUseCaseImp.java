package com.listenup.individualassignment.business.playlist.imp;

import com.listenup.individualassignment.business.playlist.DeletePlaylistUseCase;
import com.listenup.individualassignment.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class DeletePlaylistUseCaseImp implements DeletePlaylistUseCase {
    private final PlaylistRepository db;

    @Override
    public boolean deletePlaylist(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
