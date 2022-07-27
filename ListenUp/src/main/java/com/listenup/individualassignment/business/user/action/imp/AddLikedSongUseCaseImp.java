package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.user.action.AddLikedSongUseCase;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.entity.Customer;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class AddLikedSongUseCaseImp implements AddLikedSongUseCase {
    private final UserRepository db;
    private final IsAuthorised authorised;

    @Override
    public void addSongToCollection(AddRemoveSongToCollectionDTO song){
        Customer old = db.getUserById(song.getCustomerID());

        authorised.isAuthorised(song.getCustomerID());

        if(!db.existsById(song.getCustomerID())){
            throw new InvalidCustomerException("INVALID_ID");
        }

        old.getLikedSongs().add(SongDTOConverter.convertToSingleSongModelForUpdate(song.getSong()));

        db.save(old);
    }
}
