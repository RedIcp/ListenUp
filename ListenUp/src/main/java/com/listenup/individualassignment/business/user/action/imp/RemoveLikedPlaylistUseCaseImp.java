package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.business.user.action.RemoveLikedPlaylistUseCase;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class RemoveLikedPlaylistUseCaseImp implements RemoveLikedPlaylistUseCase {
    private final UserRepository db;
    private final IsAuthorised authorised;

    @Override
    public void removeLikedPlaylist(AddRemoveLikedPlaylistDTO playlist){
        authorised.isAuthorised(playlist.getCustomerID());

        if(!db.existsById(playlist.getCustomerID())){
            throw new InvalidCustomerException("INVALID_CUSTOMER");
        }

        db.removeLikedPlaylist(playlist.getPlaylistID(), playlist.getCustomerID());
    }
}
