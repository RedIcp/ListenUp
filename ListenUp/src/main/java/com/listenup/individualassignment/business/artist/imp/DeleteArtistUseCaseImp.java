package com.listenup.individualassignment.business.artist.imp;

import com.listenup.individualassignment.business.artist.DeleteArtistUseCase;
import com.listenup.individualassignment.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class DeleteArtistUseCaseImp implements DeleteArtistUseCase {
    private final ArtistRepository db;

    @Override
    public boolean deleteArtist(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}
