package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.business.user.action.RemoveLikedSongUseCase;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.entity.Customer;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class RemoveLikedSongUseCaseImp implements RemoveLikedSongUseCase {
    private final UserRepository db;
    private final IsAuthorised authorised;

    @Override
    public void removeSongFromCollection(AddRemoveSongToCollectionDTO song){
        Customer old = db.getUserById(song.getCustomerID());

        authorised.isAuthorised(song.getCustomerID());

        if(!db.existsById(song.getCustomerID())){
            throw new InvalidCustomerException("INVALID_ID");
        }

        old.getLikedSongs().remove(SongDTOConverter.convertToSingleSongModelForUpdate(song.getSong()));

        db.save(old);
    }
}
