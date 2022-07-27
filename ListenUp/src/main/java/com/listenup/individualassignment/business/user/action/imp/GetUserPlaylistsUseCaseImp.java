package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.user.action.GetUserPlaylistsUseCase;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class GetUserPlaylistsUseCaseImp implements GetUserPlaylistsUseCase {
    private final UserRepository db;

    @Override
    public CustomerPlaylistListDTO getCustomerPlaylists(long id){
        return CustomerDTOConverter.convertToDTOForPlaylist( db.getUserById(id));
    }
}
