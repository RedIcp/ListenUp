package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.user.action.AddLikedPlaylistUseCase;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
import com.listenup.individualassignment.entity.Customer;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class AddLikedPlaylistUseCaseImp implements AddLikedPlaylistUseCase {
    private final UserRepository db;
    private final IsAuthorised authorised;

    @Override
    public void addLikedPlaylist(AddRemoveLikedPlaylistDTO playlist){
        Customer old = db.getUserById(playlist.getCustomerID());

        authorised.isAuthorised(playlist.getCustomerID());

        if(!db.existsById(playlist.getCustomerID())){
            throw new InvalidCustomerException("INVALID_ID");
        }

        old.getLikedPlaylists().add(PlaylistDTOConverter.convertToModelForUpdate(playlist.getPlaylist()));

        db.save(old);
    }
}
