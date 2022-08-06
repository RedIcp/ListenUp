package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.business.user.action.RemoveLikedSongUseCase;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class RemoveLikedSongUseCaseImp implements RemoveLikedSongUseCase {
    private final UserRepository db;
    private final IsAuthorised authorised;

    @Override
    public void removeSongFromCollection(AddRemoveSongToCollectionDTO song){
        authorised.isAuthorised(song.getCustomerID());

        if(!db.existsById(song.getCustomerID())){
            throw new InvalidCustomerException("INVALID_CUSTOMER");
        }

        db.removeLikedSong(song.getSongID(), song.getCustomerID());
    }
}
