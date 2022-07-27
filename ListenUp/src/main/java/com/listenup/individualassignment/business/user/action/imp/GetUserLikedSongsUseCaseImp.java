package com.listenup.individualassignment.business.user.action.imp;

import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.user.action.GetUserLikedSongsUseCase;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;
import com.listenup.individualassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class GetUserLikedSongsUseCaseImp implements GetUserLikedSongsUseCase {
    private final UserRepository db;

    @Override
    public CustomerLikedSongListDTO getCustomerCollection(long id){
        return CustomerDTOConverter.convertToDTOForLikedSong(db.getUserById(id));
    }
}
