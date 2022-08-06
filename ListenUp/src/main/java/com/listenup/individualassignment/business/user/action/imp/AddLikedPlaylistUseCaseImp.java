package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.playlist.GetPlaylist;
import com.listenup.individualassignment.business.user.action.AddLikedPlaylistUseCase;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
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
public class AddLikedPlaylistUseCaseImp implements AddLikedPlaylistUseCase {
    private final UserRepository db;
    private final IsAuthorised authorised;
    private final GetPlaylist getPlaylist;

    @Override
    public void addLikedPlaylist(AddRemoveLikedPlaylistDTO playlist){
        Customer old = db.getUserById(playlist.getCustomerID());

        authorised.isAuthorised(playlist.getCustomerID());

        if(!db.existsById(playlist.getCustomerID())){
            throw new InvalidCustomerException("INVALID_CUSTOMER");
        }

        old.getLikedPlaylists().add(getPlaylist.getPlaylist(playlist.getPlaylistID()));

        db.save(old);
    }
}
