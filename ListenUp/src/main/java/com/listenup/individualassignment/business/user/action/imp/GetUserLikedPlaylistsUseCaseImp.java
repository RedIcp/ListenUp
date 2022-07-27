package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.user.action.GetUserLikedPlaylistsUseCase;
import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class GetUserLikedPlaylistsUseCaseImp implements GetUserLikedPlaylistsUseCase {
    private final UserRepository db;

    @Override
    public CustomerLikedPlaylistListDTO getCustomerLikedPlaylists(long id){
        return CustomerDTOConverter.convertToDTOForLikedPlaylist(db.getUserById(id));
    }
}
