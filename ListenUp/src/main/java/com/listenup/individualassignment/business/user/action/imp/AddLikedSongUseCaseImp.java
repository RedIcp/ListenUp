package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.song.GetSong;
import com.listenup.individualassignment.business.user.action.AddLikedSongUseCase;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.repository.entity.Customer;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class AddLikedSongUseCaseImp implements AddLikedSongUseCase {
    private final UserRepository db;
    private final IsAuthorised authorised;
    private final GetSong getSong;

    @Override
    public void addSongToCollection(AddRemoveSongToCollectionDTO song){
        Customer old = db.getUserById(song.getCustomerID());

        authorised.isAuthorised(song.getCustomerID());

        if(!db.existsById(song.getCustomerID())){
            throw new InvalidCustomerException("INVALID_CUSTOMER");
        }

        old.getLikedSongs().add(getSong.getSong(song.getSongID()));

        db.save(old);
    }
}
